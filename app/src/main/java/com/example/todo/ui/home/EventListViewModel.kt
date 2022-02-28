package com.example.todo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.todo.data.repository.EventsRepository
import com.smartapphouse.shops.utils.Resource
import com.smartapphouse.shops.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EventListViewModel
@Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {
    fun getEventList() = liveData(Dispatchers.IO) {
        emit(Resource(state = ResourceState.LOADING, data = null))
        try {
            val results = eventsRepository.getEvents()
            emit(Resource(ResourceState.SUCCESS, data = results))
        } catch (e: Exception) {
            emit(
                Resource(
                    state = ResourceState.ERROR,
                    data = null,
                    message = e.message ?: "unknown error"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource(
                    state = ResourceState.ERROR,
                    data = null,
                    message = e.message ?: "unknown error"
                )
            )

        }
    }
}