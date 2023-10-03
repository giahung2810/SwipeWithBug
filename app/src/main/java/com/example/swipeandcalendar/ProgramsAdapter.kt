package com.example.swipeandcalendar

import com.example.swipeandcalendar.databinding.ProgramListitemBinding
import android.content.Context
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProgramsAdapter(
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<String> = ProgramEvent().loadProgramEvent()

    private val cvStartSet = ConstraintSet()
    private val cvTransition = ChangeBounds()

    var isShow = false

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

        viewHolder.binding.tv1.text = programEvent
        viewHolder.binding.tv2.text = programEvent
        viewHolder.binding.tv3.text = programEvent
//        viewHolder.binding.info2.visibility = if (isShow) View.GONE else View.VISIBLE

        viewHolder.binding.tv3.setOnClickListener {
            viewHolder.binding.apply {
                cvStartSet.clone(layout)
                if (isShow) {
                    cvStartSet.clear(info1.id, ConstraintSet.BOTTOM)
                    cvStartSet.clear(info2.id, ConstraintSet.TOP)
                    cvStartSet.clear(info2.id, ConstraintSet.BOTTOM)
                    cvStartSet.clear(info3.id, ConstraintSet.TOP)

                    cvStartSet.connect(
                        info1.id,
                        ConstraintSet.BOTTOM,
                        info2.id,
                        ConstraintSet.TOP
                    )
                    cvStartSet.connect(
                        info2.id,
                        ConstraintSet.TOP,
                        info1.id,
                        ConstraintSet.BOTTOM
                    )
                    cvStartSet.connect(
                        info2.id,
                        ConstraintSet.BOTTOM,
                        info3.id,
                        ConstraintSet.TOP
                    )
                    cvStartSet.connect(
                        info3.id,
                        ConstraintSet.TOP,
                        info2.id,
                        ConstraintSet.BOTTOM
                    )

                } else {
                    cvStartSet.clear(info1.id, ConstraintSet.BOTTOM)
                    cvStartSet.clear(info2.id, ConstraintSet.TOP)
                    cvStartSet.clear(info2.id, ConstraintSet.BOTTOM)
                    cvStartSet.clear(info3.id, ConstraintSet.TOP)

                    cvStartSet.connect(
                        info1.id,
                        ConstraintSet.BOTTOM,
                        info3.id,
                        ConstraintSet.TOP
                    )

                    cvStartSet.connect(
                        info3.id,
                        ConstraintSet.TOP,
                        info1.id,
                        ConstraintSet.BOTTOM
                    )

                }


                cvTransition.interpolator = DecelerateInterpolator()
                cvTransition.duration = 1000

                // Apply the new constraints with the animation
                TransitionManager.beginDelayedTransition(layout, cvTransition)
                cvStartSet.applyTo(layout)

                isShow = !isShow
            }
        }
    }

    class ViewHolder(val binding: ProgramListitemBinding) :
        RecyclerView.ViewHolder(binding.root)
}