package com.arctouch.codechallenge.presentation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.presentation.home.DetailPresenter
import com.arctouch.codechallenge.util.ViewUtil
import com.arctouch.codechallenge.util.TextUtils
import kotlinx.android.synthetic.main.default_activity.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity(), DetailView {

    private val presenter: DetailPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.default_activity)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        if (intent.extras != null) {
            presenter.getMovieById(intent.getIntExtra("movieId",0))
        }
    }

    override fun loadMovieById(movie: Movie) {

        textViewMovieName.text = movie.title
        textViewMovieGenres.text = TextUtils.organizeGenres(movie.genres)
        textViewOverview.text = movie.overview
        textViewMovieDate.text = movie.releaseDate
        ViewUtil.showBackdrop(imageViewPoster,movie.posterPath,imageViewPoster)
        ViewUtil.showPoster(imageViewPoster,movie.backdropPath,imageViewBackdrop)

        scrollViewContent.visibility = View.VISIBLE
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
