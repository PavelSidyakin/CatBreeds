package com.cat_breeds.android

import android.app.Application
import com.cat_breeds.common.CommonApp
import com.cat_breeds.common.globalDI
import com.cat_breeds.db.DbDriverFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

class TheApplication : Application() {
    private val commonApp: CommonApp by lazy { CommonApp() }

    override fun onCreate() {
        globalDI.baseDI = DI {
            commonApp.buildCommonDI(this)
            bind { singleton { DbDriverFactory(this@TheApplication) } }
        }

        commonApp.onCreate()

        super.onCreate()
    }
}