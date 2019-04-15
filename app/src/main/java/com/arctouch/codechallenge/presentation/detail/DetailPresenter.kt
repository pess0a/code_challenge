package com.arctouch.codechallenge.presentation.home

import com.arctouch.codechallenge.base.BasePresenter
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.repository.TmdbRepository

class DetailPresenter(private var tmdbRepository: TmdbRepository) : BasePresenter<DetailView>() {

    private var page : Long = 1

    fun getMovieById(id: Long) {
        tmdbRepository.getMovieById(id)
                .subscribe({
                    response ->

                }, {
                    error ->
                })
    }
}