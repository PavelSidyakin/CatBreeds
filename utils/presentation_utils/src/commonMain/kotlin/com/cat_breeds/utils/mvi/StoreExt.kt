package com.cat_breeds.utils.mvi

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <S : Any> Store<*, S, *>.stateAsValue(scope: CoroutineScope): Value<S> {

    val value = MutableValue(state)

    states.onEach { value.value = it }
        .launchIn(scope)

    return value
}
