package com.arctouch.codechallenge.presentation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.presentation.home.DetailPresenter
import com.arctouch.codechallenge.presentation.home.DetailView
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), DetailView {

    private val presenter: DetailPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onResume() {
        presenter.bind(this)
        super.onResume()
    }

    override fun onStop() {
        presenter.unBind()
        super.onStop()
    }
}
