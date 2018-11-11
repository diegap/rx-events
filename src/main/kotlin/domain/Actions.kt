package domain

import io.reactivex.rxkotlin.toObservable
import loggerFor
import java.time.LocalDateTime

interface EventDataFetcher {
    fun fetchData(): List<Event>
}

class SendEventsAsColdObservableUseCase(private val eventDataFetcher: EventDataFetcher) {

    private val logger = loggerFor<SendEventsAsColdObservableUseCase>()

    fun execute() = eventDataFetcher.fetchData()
            .toObservable()
            .map {
                val ack = EventAck(event = it, date = LocalDateTime.now())
                logger.debug("Event ackwnowledged at ${ack.date}")
                ack
            }.toList()
            .blockingGet()
            .toList()

}
