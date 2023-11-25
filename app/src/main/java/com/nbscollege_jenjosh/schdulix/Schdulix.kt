package com.nbscollege_jenjosh.schdulix

import android.app.Application
import com.nbscollege_jenjosh.schdulix.data.AppContainer

class Schdulix : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer.AppDataContainer(this)
    }
}