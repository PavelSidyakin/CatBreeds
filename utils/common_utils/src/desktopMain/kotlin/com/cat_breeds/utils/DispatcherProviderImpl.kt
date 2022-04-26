package com.cat_breeds.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual class DispatcherProviderImpl : DispatcherProvider {
    actual override val main: CoroutineDispatcher = Dispatchers.Main
    actual override val default: CoroutineDispatcher = Dispatchers.Default
    actual override val io: CoroutineDispatcher = Dispatchers.IO
}