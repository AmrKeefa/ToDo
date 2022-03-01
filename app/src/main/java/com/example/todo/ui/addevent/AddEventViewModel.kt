package com.example.todo.ui.addevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.entity.Event
import com.example.todo.data.repository.EventsRepository
import com.smartapphouse.shops.utils.Resource
import com.smartapphouse.shops.utils.extensions.setError
import com.smartapphouse.shops.utils.extensions.setLoading
import com.smartapphouse.shops.utils.extensions.setSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {
    var body = ""
    var title = ""
    var time = ""

    private val _event = MutableLiveData<Resource<Unit>>()

    val event: LiveData<Resource<Unit>>
        get() = _event

    fun insertEvent() = viewModelScope.launch(Dispatchers.IO) {
        _event.setLoading()
        try {
            val insertEventResult = eventsRepository.insertEvent(
                Event(
                    body = body,
                    title = title,
                    time = time
                )
            )
            _event.setSuccess(insertEventResult)
        } catch (e: Exception) {
            _event.setError(message = e.message ?: "Unknown Error")
        } catch (e: IOException) {
            _event.setError(message = e.message ?: "Unknown Error")
        }
    }
}