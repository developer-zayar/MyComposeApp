package com.zayar.bookshelf

import android.app.Application
import com.zayar.bookshelf.data.AppContainer
import com.zayar.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}