package com.arctouch.codechallenge.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.presentation.detail.DetailActivity
import com.arctouch.codechallenge.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), HomeView {

    private val presenter: HomePresenter by inject()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        presenter.getGenresAndUpcomingMovies()
    }

    override fun loadUpcomingMovies (listMoviesWithGenre : MutableList<Movie>) {
        if (recyclerViewMovies.adapter==null) {
            recyclerViewMovies.adapter = HomeAdapter(listMoviesWithGenre).apply { homeAdapter = this }
            homeAdapter.setMovieClickItemListener {
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra("movieId", it)
                })
            }

            val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recyclerViewMovies.layoutManager = layoutManager

            recyclerViewMovies.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    presenter.getUpcomingMovies()
                }
            })
        } else {
            (recyclerViewMovies.adapter as HomeAdapter).updateList(listMoviesWithGenre)
        }


    }

    override fun errorOnLoadUpcomingList() {

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
