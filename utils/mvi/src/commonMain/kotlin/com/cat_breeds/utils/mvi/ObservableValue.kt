package com.cat_breeds.utils.mvi

import com.arkivanov.decompose.value.ValueObserver

abstract class ObservableValue<out T : Any> {
    abstract fun subscribe(observer: ValueObserver<T>)

    abstract fun unsubscribe(observer: ValueObserver<T>)
}
