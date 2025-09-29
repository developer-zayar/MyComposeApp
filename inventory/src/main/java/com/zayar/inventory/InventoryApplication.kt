package com.zayar.inventory

import android.app.Application
import com.zayar.inventory.data.AppContainer
import com.zayar.inventory.data.AppDataContainer

class InventoryApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}