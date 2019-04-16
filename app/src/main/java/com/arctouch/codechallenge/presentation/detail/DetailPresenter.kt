package com.arctouch.codechallenge.presentation.home

import android.util.Log
import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.repository.TmdbRepository

class DetailPresenter(private var tmdbRepository: TmdbRepository) : BasePresenter<DetailView>() {

    private var page : Long = 1

    fun getMovieById(id: Int) {
        view?.showLoading()
        tmdbRepository.getMovieById(id)
                .subscribe({
                    response -> Log.i("logger",response.toString())
                    view?.loadMovieById(response)
                    view?.hideLoading()

                }, {
                    error -> error.printStackTrace()
                })
    }
}