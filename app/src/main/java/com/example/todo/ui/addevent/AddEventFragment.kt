package com.example.todo.ui.addevent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddEventBinding
import com.smartapphouse.shops.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEventFragment : Fragment(R.layout.fragment_add_event) {
    private var binding: FragmentAddEventBinding? = null
    private fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    var currentTime: String? = ""
    private val viewModel: AddEventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return setupViewBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickTime()
        insertAnEvent()
    }

    private fun onClickTime() {

        val timePicker = binding?.timePicker1
        timePicker?.setOnTimeChangedListener { _, hour, minute ->
            var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {
                hour == 0 -> {
                    hour += 12
                    am_pm = "AM"
                }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> {
                    hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            if (currentTime != null) {
                val hour = if (hour < 10) "0$hour" else hour
                val min = if (minute < 10) "0$minute" else minute
                // display format of time
                currentTime = "$hour : $min $am_pm"
//                textView.visibility = ViewGroup.VISIBLE
                Log.d("Keefa", "onClickTime: $currentTime")
            }
        }
    }

    private fun insertAnEvent() {
        binding?.done?.setOnClickListener {
//            viewModel.time = currentTime.toString()
            viewModel.title = binding?.addTitleEt?.text.toString()
            viewModel.body = binding?.addBodyEt?.text.toString()
            viewModel.insertEvent()
            observeEvent()
        }
    }

    private fun observeEvent() {
        viewModel.event.observe(viewLifecycleOwner) { resources ->
            when (resources.state) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    resources.data?.let {
                        Toast.makeText(
                            context,
                            getResources().getString(R.string.added_successfully),
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().navigateUp()
                    }
                }
                ResourceState.ERROR -> {
                    resources.message.let {
                    }
                }

            }
        }
    }
}
