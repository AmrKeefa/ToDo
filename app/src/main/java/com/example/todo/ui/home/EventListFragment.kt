package com.example.todo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentEventsListBinding
import com.example.todo.ui.addevent.AddEventViewModel
import com.smartapphouse.shops.utils.ResourceState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : Fragment(R.layout.fragment_events_list) {

    private var binding: FragmentEventsListBinding? = null
    private fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentEventsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }
    private val viewModel: EventListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return setupViewBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFabButton()
        getEventList()
    }
    private fun setUpFabButton(){
        binding?.fab?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_eventListFragment_to_addEventFragment)
        }
    }

    private fun getEventList() {
        viewModel.getEventList().observe(viewLifecycleOwner) { resources ->
            when (resources.state) {
                ResourceState.LOADING -> {

                }
                ResourceState.SUCCESS -> {
                    resources.data?.let {
//                        setCorporatesListAdapter(it.corporates)
//                        if (it.corporates.isNullOrEmpty()){
//                            binding?.noData?.visible()
//                        }else{
//                            binding?.noData?.gone()
//                        }
                        Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
                    }
                }
                ResourceState.ERROR -> {
                    resources.message.let {
                        Log.d("Keefa", "getCategories: $it")
                    }
                }
            }
        }
    }
}