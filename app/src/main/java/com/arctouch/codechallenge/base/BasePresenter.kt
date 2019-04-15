package com.arctouch.codechallenge.base


open class BasePresenter<V: View> : Presenter<V> {

    var view: V? = null

    override fun bind(view: V) {
        this.view = view
    }

    override fun unBind() {
        this.view = null
    }
}