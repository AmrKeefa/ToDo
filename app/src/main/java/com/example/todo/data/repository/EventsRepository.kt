package com.example.todo.data.repository

import com.example.todo.data.entity.Event
import com.example.todo.data.local.EventsDao

class EventsRepository(private val dao: EventsDao) {

    suspend fun insertEvent(event : Event) = dao.insertEvent(event)
    suspend fun getEvents() = dao.getEventsLists()
}