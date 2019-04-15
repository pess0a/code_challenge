package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.presentation.home.DetailPresenter
import com.arctouch.codechallenge.infrastructure.BackendIntegrator
import com.arctouch.codechallenge.repository.TmdbRepository
import org.koin.dsl.module.module

private val tmdbApi : TmdbApi = BackendIntegrator.getTmdbApi()

val homeModule = module {
    factory { DetailPresenter(get()) }
}

val networkModule = module {
    factory { TmdbRepository(get()) }
    single { tmdbApi }
}