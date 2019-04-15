package com.arctouch.codechallenge.presentation.home

import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.repository.TmdbRepository

class DetailPresenter(private var tmdbRepository: TmdbRepository) : BasePresenter<DetailView>() {

    private var page : Long = 1

    fun getGenresAndUpcomingMovies() {
        tmdbRepository.getGenres()
                .doOnComplete {
                    getUpcomingMovies()
                }
                .subscribe({ response ->
                    Cache.cacheGenres(response.genres)
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun getUpcomingMovies() {
        tmdbRepository.getUpcomingMovie(page).subscribe(
                { response ->
                    val moviesWithGenres = response.results.map { movie ->
                        movie.copy(genres = Cache.genres.filter {
                            movie.genreIds?.contains(it.id) == true
                        })
                    }
                    view?.loadUpcomingMovies(moviesWithGenres.toMutableList())
                    view?.hideLoading()
                    page += 1

                }, { error ->
                    error.printStackTrace()
                })
    }
}