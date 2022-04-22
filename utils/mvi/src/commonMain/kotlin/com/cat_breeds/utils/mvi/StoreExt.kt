package com.cat_breeds.utils.mvi

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <S : Any> Store<*, S, *>.stateAsValue(scope: CoroutineScope): Value<S> =
    object : Value<S>() {
        private val jobs = mutableMapOf<ValueObserver<S>, Job>()

        override val value: S = state
        override fun subscribe(observer: ValueObserver<S>) {
            jobs += observer to states
                .onEach { observer(it) }
                .launchIn(scope)
        }

        override fun unsubscribe(observer: ValueObserver<S>) {
            jobs[observer]?.cancel()
            jobs -= observer
        }
    }

fun <L : Any> Store<*, *, L>.labelAsObservableValue(scope: CoroutineScope): ObservableValue<L> =
    object : ObservableValue<L>() {
        private val jobs = mutableMapOf<ValueObserver<L>, Job>()

        override fun subscribe(observer: ValueObserver<L>) {
            jobs += observer to labels
                .onEach { observer(it) }
                .launchIn(scope)
        }

        override fun unsubscribe(observer: ValueObserver<L>) {
            jobs[observer]?.cancel()
            jobs -= observer
        }
    }