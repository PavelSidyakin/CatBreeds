package com.cat_breeds.desktop.app

import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.cat_breeds.common.CommonApp
import com.cat_breeds.common.globalDI
import com.cat_breeds.db.DbDriverFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

private val commonApp by lazy { CommonApp() }

fun main() {

    globalDI.baseDI = DI {
        commonApp.buildCommonDI(this)
        bind { singleton { DbDriverFactory() } }
    }

    commonApp.onCreate()

    val lifecycle = LifecycleRegistry()

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        MainScreen(onClose = { exitApplication() })
    }
}
