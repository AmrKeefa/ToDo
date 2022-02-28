package com.example.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.entity.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventsDataBase : RoomDatabase(){

//    companion object {
//        @JvmStatic lateinit var instance: DataBase
//    }
//    init {
//        instance = this
//    }

    abstract fun eventsDao(): EventsDao


}