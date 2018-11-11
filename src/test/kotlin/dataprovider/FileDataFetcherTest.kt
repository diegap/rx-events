package dataprovider

import infrastructure.dataprovider.FileDataFetcher
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FileDataFetcherTest {

    @Test
    fun `Read content from existing file`() {

        // given
        val dataFetcher = FileDataFetcher("events.json")

        // when
        val events = dataFetcher.fetchData()

        // then
        assertThat(events).isNotEmpty

    }

    @Test
    fun `Try to read content from missing file`() {

        // given
        val dataFetcher = FileDataFetcher("non-existing-file.json")

        // when
        val events = dataFetcher.fetchData()

        // then
        assertThat(events).isEmpty()

    }

}