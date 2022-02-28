package com.example.todo.di


import androidx.room.Dao
import com.example.todo.data.local.EventsDao
import com.example.todo.data.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideEventsRepository(eventsDao: EventsDao): EventsRepository {
        return EventsRepository(eventsDao)
    }


}