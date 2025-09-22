package com.zayar.marsphotos

import android.app.Application
import com.zayar.marsphotos.data.AppContainer
import com.zayar.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}