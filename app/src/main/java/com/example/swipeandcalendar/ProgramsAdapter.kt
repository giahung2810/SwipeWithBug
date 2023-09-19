package com.example.swipeandcalendar

import com.example.swipeandcalendar.databinding.ProgramListitemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProgramsAdapter(
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<String> = ProgramEvent().loadProgramEvent()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            ProgramListitemBinding.inflate(inflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewHolder = holder as ViewHolder

        val programEvent = list[position]

        viewHolder.binding.tvProgramName.text = programEvent

        val eventsAdapter = EventsAdapter(
            context,
        )

        val eventsRecyclerView = viewHolder.binding.rvEventList

        eventsRecyclerView.layoutManager =
            LinearLayoutManager(eventsRecyclerView.context, LinearLayoutManager.VERTICAL, false)
        eventsRecyclerView.adapter = eventsAdapter
    }

    class ViewHolder(val binding: ProgramListitemBinding) :
        RecyclerView.ViewHolder(binding.root)
}