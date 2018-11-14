import domain.SendEventsAsColdObservableUseCase
import domain.SendEventsAsHotObservableUseCase
import infrastructure.dataprovider.FileDataFetcher
import mu.KotlinLogging

class App {

    companion object {

        private val logger = KotlinLogging.logger {}
        private const val INPUT_EVENTS = "events.json"

        @JvmStatic
        fun main(args: Array<String>) {

            logger.info { "Starting application ..." }

            SendEventsAsColdObservableUseCase(FileDataFetcher(INPUT_EVENTS)).execute()
            SendEventsAsHotObservableUseCase(FileDataFetcher(INPUT_EVENTS)).execute()

            logger.info { "Ending application ..." }
        }

    }

}