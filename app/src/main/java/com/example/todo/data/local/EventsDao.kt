package com.example.todo.data.local

import androidx.room.*
import com.example.todo.data.entity.Event

@Dao
interface EventsDao {

    @Insert
    suspend fun insertEvent(vararg events: Event): Event

    @Query("SELECT * FROM events_table")
    suspend fun getEventsLists(): List<Event>

    @Update
    suspend fun updateEventsList(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

}