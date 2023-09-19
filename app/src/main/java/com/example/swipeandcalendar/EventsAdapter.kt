package com.example.swipeandcalendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipeandcalendar.databinding.EventItemBinding

class EventsAdapter(
    private val context: Context,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val events: List<String> = ProgramEvent().loadEvent()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return ViewHolder(
            EventItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewHolder = holder as ViewHolder

        val event = events[position]

        viewHolder.binding.tvStationName.text = event
    }

    class ViewHolder(val binding: EventItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}