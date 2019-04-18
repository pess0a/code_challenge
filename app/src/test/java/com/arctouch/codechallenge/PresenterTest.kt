package com.arctouch.codechallenge

import com.arctouch.codechallenge.presentation.home.HomePresenter
import com.arctouch.codechallenge.presentation.home.HomeView
import com.arctouch.codechallenge.repository.TmdbRepository
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PresenterTest : TestSetupPlugin() {

    @Mock
    lateinit var homePresenter: HomePresenter

    @Mock lateinit var homeView : HomeView

    @Mock
    lateinit var tmdbRepository: TmdbRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testHomePresenter() {
        homePresenter.bind(homeView)
        homePresenter.getUpcomingMovies()
        then(homeView).should().loadUpcomingMovies(mutableListOf())
    }

}