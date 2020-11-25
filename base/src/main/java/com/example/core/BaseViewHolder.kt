package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val viewHashMap: MutableMap<Int, View> = HashMap()


    fun <T : View?> getView(@IdRes id: Int): T? {

        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }


        return view as T?
    }


    fun setText(id: Int, text: String) {
        (getView<View>(id) as TextView).text = text
    }
}