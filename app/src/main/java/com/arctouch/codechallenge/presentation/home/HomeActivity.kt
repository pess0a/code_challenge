package com.arctouch.codechallenge.presentation.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        presenter.getGenresAndUpcomingMovies()
    }

    override fun loadUpcomingMovies(listMoviesWithGenre: MutableList<Movie>) {
        if (recyclerViewMovies.adapter == null) {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setIconifiedByDefault(true)
        searchView.isIconified = false
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.i("logger",query)
                homeAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                homeAdapter.filter.filter(query)
                Log.i("logger",query)
                return false
            }
        })

        return true
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

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
