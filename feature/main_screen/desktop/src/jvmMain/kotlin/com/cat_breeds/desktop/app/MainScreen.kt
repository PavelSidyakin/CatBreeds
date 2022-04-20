package com.cat_breeds.desktop.app

import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState

@Composable
fun MainScreen(onClose: () -> Unit) {
    val windowState = rememberWindowState()

    Window(
        onCloseRequest = { onClose() } ,
        state = windowState,
        title = "Cat Breeds"
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                DesktopTheme {
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
