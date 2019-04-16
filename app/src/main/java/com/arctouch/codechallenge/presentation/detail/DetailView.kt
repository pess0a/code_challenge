package com.arctouch.codechallenge.presentation.home

import com.arctouch.codechallenge.base.View
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse


interface DetailView : View {
    fun loadMovieById(movie: Movie)
}