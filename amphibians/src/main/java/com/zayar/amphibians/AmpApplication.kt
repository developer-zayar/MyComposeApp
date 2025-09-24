package com.zayar.amphibians

import android.app.Application
import com.zayar.amphibians.data.AppContainer
import com.zayar.amphibians.data.DefaultAppContainer

class AmpApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()

    }
}