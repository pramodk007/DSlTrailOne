package com.example.movieexp.common.utils.extension

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.vertical(): RecyclerView {
    layoutManager = LinearLayoutManager(this.context)
    return this
}

fun RecyclerView.horizontal(): RecyclerView {
    layoutManager = LinearLayoutManager(this.context).apply {
        orientation = RecyclerView.HORIZONTAL
    }
    return this
}

fun RecyclerView.grid(count: Int): RecyclerView {
    layoutManager = GridLayoutManager(this.context, count)
    return this
}

fun RecyclerView.ViewHolder.getString(@StringRes string: Int): String {
    return itemView.context.getString(string)
}

fun RecyclerView.ViewHolder.getString(@StringRes string: Int, vararg arg: String): String {
    return itemView.context.getString(string, *arg)
}

fun RecyclerView.ViewHolder.color(@ColorRes resource: Int): Int {
    return itemView.context.color(resource)
}
