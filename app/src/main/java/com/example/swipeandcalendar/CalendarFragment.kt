package com.example.swipeandcalendar

import android.animation.LayoutTransition
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipeandcalendar.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var eventsAdapter: ProgramsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
    }
}