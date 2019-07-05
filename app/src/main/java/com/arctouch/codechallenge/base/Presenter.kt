package com.arctouch.codechallenge.base

interface Presenter<V : View> {
    fun bind(view: V)
    fun unBind()
}