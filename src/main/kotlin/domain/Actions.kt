package domain

import io.reactivex.rxkotlin.toObservable
import mu.KotlinLogging
import java.time.LocalDateTime

interface EventDataFetcher {
    fun fetchData(): List<Event>
}

class SendEventsAsColdObservableUseCase(private val eventDataFetcher: EventDataFetcher) {

    private val logger = KotlinLogging.logger {}

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

class SendEventsAsHotObservableUseCase(private val eventDataFetcher: EventDataFetcher) {

    private val logger = KotlinLogging.logger {}

    fun execute(): List<EventAck> {

        val receivedEvents = mutableListOf<EventAck>()
        val hotObservable = eventDataFetcher.fetchData()
                .toObservable().publish()

        hotObservable.subscribe {
            val ack = EventAck(event = it, date = LocalDateTime.now())
            logger.debug("Event ackwnowledged at ${ack.date}")
            receivedEvents.add(ack)
        }

        hotObservable.connect()

        return receivedEvents
    }

}