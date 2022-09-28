package com.example.movieexp.core.Initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.movieexp.BuildConfig
import timber.log.Timber

//class TimberInitializer: Initializer<Timber> {
//
//    override fun create(context: Context) {
//        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree())
//    }
//
//    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
//        return emptyList()
//    }
//
//}