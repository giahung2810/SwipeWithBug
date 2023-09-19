package com.example.swipeandcalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipeandcalendar.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var eventsAdapter: ProgramsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViews()
        return binding.root
    }

    private fun initViews() {
        eventsAdapter = ProgramsAdapter(
            requireContext(),
        )

        val eventsRecyclerView = binding.rvProgramList
        eventsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventsRecyclerView.adapter = eventsAdapter

        binding.svContainer.viewTreeObserver.addOnScrollChangedListener {
            if (isVisible) {
                // Scroll view position 0 means it is on top of screen now
                val enableSwipeToRefresh = binding.svContainer.scrollY != 0
                MainActivity.stateFlowSwipeEnabled.value = !enableSwipeToRefresh
            }
        }
    }
}