package com.arctouch.codechallenge

import com.arctouch.codechallenge.presentation.home.HomePresenter
import com.arctouch.codechallenge.repository.TmdbRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresenterTest : TestSetupPlugin() {

    @Mock
    lateinit var homePresenter: HomePresenter

    @Mock
    lateinit var tmdbRepository: TmdbRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    fun testHomePresenter() {

    }

}