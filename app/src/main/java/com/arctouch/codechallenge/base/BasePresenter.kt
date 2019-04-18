package com.arctouch.codechallenge.base

import com.arctouch.codechallenge.infrastructure.DisposableManager


open class BasePresenter<V: View> : Presenter<V> {

    val disposableManager = DisposableManager()

    var view: V? = null

    override fun bind(view: V) {
        this.view = view
    }

    override fun unBind() {
        this.view = null
    }
}