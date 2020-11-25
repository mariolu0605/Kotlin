package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

class LessonPresenter(val activity: LessonActivity) {

    companion object {
        private val LESSON_PATH = "lessons"
    }

    private var lessons = ArrayList<Lesson>()

    private val type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entry: List<Lesson>) {
                this@LessonPresenter.lessons = entry as ArrayList<Lesson>
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread {
                    Utils.toast(message)
                }
            }

        })
    }

    fun showPlayback() {
        val playbackLessons = ArrayList<Lesson>()
        lessons.forEach {
            if (it.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(it)
            }
        }
        activity.showResult(playbackLessons)
    }
}