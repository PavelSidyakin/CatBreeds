package com.cat_breeds.desktop.app

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.CatBreedsRootParams
import com.cat_breeds.common.globalDI
import com.cat_breeds.resources.SharedRes
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import org.kodein.di.instance

@Composable
fun MainScreen(onClose: () -> Unit) {
    val windowState = rememberWindowState()

    val lifecycle = LifecycleRegistry()
    val root: CatBreedsRoot by globalDI.instance(arg = CatBreedsRootParams(DefaultComponentContext(lifecycle = lifecycle)))

    Window(
        onCloseRequest = { onClose() },
        state = windowState,
        title = StringDesc.Resource(SharedRes.strings.app_name).localized(),
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                CompositionLocalProvider(
                    LocalScrollbarStyle provides ScrollbarStyle(
                        minimalHeight = 16.dp,
                        thickness = 8.dp,
                        shape = MaterialTheme.shapes.small,
                        hoverDurationMillis = 300,
                        unhoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                        hoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.50f)
                    )
                ) {
                    RootContent(root)
                }
            }
        }
    }
}
