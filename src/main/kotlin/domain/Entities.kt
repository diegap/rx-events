package domain

import java.time.LocalDateTime

data class Event(val id: Int, val description: String)

data class EventAck(val event: Event, val date: LocalDateTime)
