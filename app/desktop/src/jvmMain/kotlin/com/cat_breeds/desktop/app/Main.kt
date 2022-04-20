package com.cat_breeds.desktop.app

//import com.arkivanov.decompose.ComponentContext
//import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
//import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
//import com.badoo.reaktive.coroutinesinterop.asScheduler
//import com.badoo.reaktive.scheduler.overrideSchedulers
//import example.todo.common.database.DefaultTodoSharedDatabase
//import example.todo.common.database.TodoDatabaseDriver
//import example.todo.common.root.TodoRoot
//import example.todo.common.root.integration.TodoRootComponent
//import example.todo.common.ui.TodoRootContent
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.cat_breeds.common.CommonApp
import com.cat_breeds.common.globalDI
import org.kodein.di.DI

private val commonApp by lazy { CommonApp() }

fun main() {
    globalDI.baseDI = DI {
        commonApp.buildCommonDI(this)
    }

    //overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    //val root = todoRoot(DefaultComponentContext(lifecycle = lifecycle))

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        MainScreen(onClose = { exitApplication() })

//        Window(
//            onCloseRequest = ::exitApplication,
//            state = windowState,
//            title = "Cat Breeds"
//        ) {
//            Surface(modifier = Modifier.fillMaxSize()) {
//                MaterialTheme {
//                    DesktopTheme {
//                        //TodoRootContent(root)
//                    }
//                }
//            }
//        }
    }
}

//private fun todoRoot(componentContext: ComponentContext): TodoRoot =
//    TodoRootComponent(
//        componentContext = componentContext,
//        storeFactory = DefaultStoreFactory(),
//        database = DefaultTodoSharedDatabase(TodoDatabaseDriver())
//    )
