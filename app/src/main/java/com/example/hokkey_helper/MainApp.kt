package com.example.hokkey_helper

import android.app.Application
import android.content.Context
import com.example.hokkey_helper.di.AppComponent
import com.example.hokkey_helper.di.DaggerAppComponent

class MainApp : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun initializeComponent(): AppComponent{
        return DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var instance: MainApp
        fun get(): MainApp {
            return instance
        }
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }