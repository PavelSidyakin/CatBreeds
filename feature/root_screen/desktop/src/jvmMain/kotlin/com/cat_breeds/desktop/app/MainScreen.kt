package com.cat_breeds.desktop.app

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.CatBreedsRootParams
import com.cat_breeds.common.globalDI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.instance

@Composable
fun MainScreen(onClose: () -> Unit) {
    val windowState = rememberWindowState()

    val lifecycle = LifecycleRegistry()
    val root: CatBreedsRoot by globalDI.instance(arg = CatBreedsRootParams(DefaultComponentContext(lifecycle = lifecycle)))

    Window(
        onCloseRequest = { onClose() } ,
        state = windowState,
        title = "Cat Breeds"
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                DesktopTheme {
                    RootContent(root)
                    //TodoRootContent(root)
                }
            }
        }
    }
}

//private fun todoRoot(componentContext: ComponentContext): TodoRoot =
//    TodoRootComponent(
//        componentContext = componentContext,
//        storeFactory = DefaultStoreFactory(),
//        database = DefaultTodoSharedDatabase(TodoDatabaseDriver())
//    )
