package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list = ArrayList<Lesson>()

    fun updateAndNotify(list: ArrayList<Lesson>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder = LessonViewHolder.onCreate(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }


    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout
                        .item_lesson, parent, false))
            }
        }

        internal fun onBind(lesson: Lesson) {
            var date = lesson.date
            if (date == null) {
                date = "日期待定"
            }
            setText(R.id.tv_date, date)

            setText(R.id.tv_content, lesson.content)
            val state = lesson.state
            if (state != null) {
                setText(R.id.tv_state, state.stateName())
                val colorRes = when (state) {
                    Lesson.State.PLAYBACK -> {
                        // 即使在 {} 中也是需要 break 的。
                        R.color.playback
                    }
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state)!!.setBackgroundColor(backgroundColor)
            }
        }
    }
}