package infrastructure.dataprovider

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import domain.Event
import domain.EventDataFetcher
import loggerFor

class FileDataFetcher(private val inputFile: String) : EventDataFetcher {

    companion object {
        private val logger = loggerFor<FileDataFetcher>()
    }

    private val objectMapper = jacksonObjectMapper()

    override fun fetchData(): List<Event> = try {
        logger.debug("Retrieving data from $inputFile")
        val content = this::class.java.classLoader.getResource(inputFile).readText()
        objectMapper.readValue(content)
    } catch (e: Exception) {
        logger.error("Cannot read file $inputFile")
        listOf()
    }

}