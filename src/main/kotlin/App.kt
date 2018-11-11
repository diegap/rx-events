import domain.SendEventsAsColdObservableUseCase
import infrastructure.dataprovider.FileDataFetcher

class App {

    private val logger = loggerFor<App>()

    fun main(args: Array<String>) {

        logger.info("Starting application ...")

        val useCase = SendEventsAsColdObservableUseCase(FileDataFetcher("events.json"))

        useCase.execute()

        logger.info("Ending application")

    }

}