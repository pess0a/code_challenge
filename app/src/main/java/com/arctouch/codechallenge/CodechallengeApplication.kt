package com.arctouch.codechallenge

import android.app.Application
import com.arctouch.codechallenge.di.homeModule
import com.arctouch.codechallenge.di.networkModule
import org.koin.android.ext.android.startKoin

class CodechallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(homeModule,networkModule))
    }
}