package com.example.todo.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.todo.data.local.EventsDataBase
import com.example.todo.data.local.EventsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: EventsDataBase): EventsDao {
        return appDatabase.eventsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): EventsDataBase {
        return Room.databaseBuilder(
            appContext,
            EventsDataBase::class.java,
            "events_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}