package com.arctouch.codechallenge.presentation.home

import com.arctouch.codechallenge.base.View
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse


interface HomeView : View {
    fun loadUpcomingMovies (listMoviesWithGenre : MutableList<Movie>)
    fun errorOnLoadUpcomingList()
}