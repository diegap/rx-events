package domain

import infrastructure.dataprovider.FileDataFetcher
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UseCaseTest {

    @Test
    fun `Send events as a cold observable`() {

        // given
        val useCase = SendEventsAsColdObservableUseCase(FileDataFetcher("events.json"))

        // when
        val eventsAck = useCase.execute()

        // then
        assertThat(eventsAck).isNotEmpty
        assertThat(eventsAck.size).isEqualTo(7)
    }

    @Test
    fun `Send events as a hot observable`() {

        // given
        val useCase = SendEventsAsHotObservableUseCase(FileDataFetcher("events.json"))

        // when
        val eventsAck = useCase.execute()

        // then
        assertThat(eventsAck).isNotEmpty
        assertThat(eventsAck.size).isEqualTo(7)

    }

}