package com.cat_breeds.utils

import kotlinx.coroutines.CoroutineDispatcher

internal expect class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineDispatcher
    override val default: CoroutineDispatcher
    override val io: CoroutineDispatcher
}