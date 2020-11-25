package com.example.core.http


interface EntityCallback<T> {
    fun onSuccess(entry: T)

    fun onFailure(message:String)
}