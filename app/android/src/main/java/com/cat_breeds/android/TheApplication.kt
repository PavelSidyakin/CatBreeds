package com.cat_breeds.android

import android.app.Application
import com.cat_breeds.common.CommonApp
import com.cat_breeds.common.globalDI
import org.kodein.di.DI

class TheApplication: Application() {
    private val commonApp: CommonApp by lazy { CommonApp() }

    override fun onCreate() {
        globalDI.baseDI = DI {
            commonApp.buildCommonDI(this)
        }

        super.onCreate()
    }
}