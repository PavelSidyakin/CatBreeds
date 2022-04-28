package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.cat_breeds.common.breed_list.breed_list_domain.BreedListInteractor
import com.cat_breeds.common.breed_list.breed_list_domain.model.BreedListItem
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListIntent
import com.cat_breeds.common.breed_list.breed_list_ui.mvi.BreedListState
import com.cat_breeds.utils.DispatcherProvider
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class BreedListStoreTest : FreeSpec() {

    private val interactor: BreedListInteractor = mockk(relaxed = true)

    private val dispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher = Dispatchers.Unconfined
        override val default: CoroutineDispatcher = Dispatchers.Unconfined
        override val io: CoroutineDispatcher = Dispatchers.Unconfined
    }

    init {
        "When store is initialized" - {
            // Action
            createStore()

            "should call initBreads() in the interactor" {
                // Verify
                coVerify { interactor.initBreeds() }
            }

            "should start observing breeds" {
                // Verify
                coVerify { interactor.observeBreeds() }
            }
        }

        "When initBreeds() fails" - {
            // Mock
            coEvery { interactor.initBreeds() } throws RuntimeException("ncjdkncdj")

            "and store is initialized" - {

                val store = createStore(autoInit = false)

                val labels = mutableListOf<BreedListLabel>()

                collectLabels(store, labels).run {
                    // Action
                    store.init()

                    cancelAndJoin()
                }

                "should display error" {
                    // Verify
                    labels.shouldContain(BreedListLabel.ShowErrorMessage)
                }
            }
        }

        "When a breed is clicked" - {
            val breedId: String = "xwxxewed"
            val store = createStore()

            val labels = mutableListOf<BreedListLabel>()

            collectLabels(store, labels).run {
                // Action
                store.accept(BreedListIntent.OnBreedClicked(breedId))

                cancelAndJoin()
            }

            "should navigate to breed info screen" {
                // Verify
                labels.shouldContain(BreedListLabel.NavigateToBreedInfo(breedId))
            }
        }

        "When interactor.forceUpdateBreeds() succeeds" - {
            // Mock
            coEvery { interactor.forceUpdateBreeds() } just runs

            "and refresh is performed" - {
                val store = createStore()

                // Action
                store.accept(BreedListIntent.OnRefresh)

                "should display loading" {
                    // Verify
                    store.state.isLoading shouldBe true
                }
            }
        }

        "When interactor.forceUpdateBreeds() fails" - {
            // Mock
            coEvery { interactor.forceUpdateBreeds() } throws RuntimeException("")

            "and refresh is performed" - {
                val store = createStore()

                val labels = mutableListOf<BreedListLabel>()

                collectLabels(store, labels).run {
                    // Action
                    store.accept(BreedListIntent.OnRefresh)

                    cancelAndJoin()
                }

                "shouldn't display loading" {
                    // Verify
                    store.state.isLoading shouldBe false
                }
                "should display error" {
                    labels.shouldContain(BreedListLabel.ShowErrorMessage)
                }
            }
        }

        "When refresh is performed" - {
            val store = createStore()

            // Action
            store.accept(BreedListIntent.OnRefresh)

            "should call interactor.forceUpdateBreeds()" {
                // Verify
                coVerify { interactor.forceUpdateBreeds() }
            }
        }

        "When interactor.observeBreeds() emits breed list" - {
            val breedIds = listOf("1", "2", "3")
            every { interactor.observeBreeds() } returns flowOf(
                breedIds.map { BreedListItem(it, "name_$it", null) }
            )

            "when store is initialized" - {
                val store = createStore(autoInit = false)
                val states = mutableListOf<BreedListState>()

                collectStates(store, states)
                    .run {
                        // Action
                        store.init()

                        cancelAndJoin()
                    }

                "should display the list" {
                    store.state.breeds.map { it.id } shouldBe breedIds
                }

                "should display and hide loading" {
                    states.map { it.isLoading }.run {
                        assertSoftly {
                            shouldContain(true)
                            last() shouldBe false
                        }
                    }
                }
            }
        }

        "When interactor.observeBreeds() emits error" - {
            // Mock
            every { interactor.observeBreeds() } returns flow {
                throw RuntimeException()
            }

            "when store is initialized (states check)" - {
                val store = createStore(autoInit = false)
                val states = mutableListOf<BreedListState>()

                collectStates(store, states)
                    .run {
                        // Action
                        store.init()

                        cancelAndJoin()
                    }

                "should display and hide loading" {
                    states.map { it.isLoading }.run {
                        assertSoftly {
                            shouldContain(true)
                            last() shouldBe false
                        }
                    }
                }
            }
            "when store is initialized (labels check)" - {
                val store = createStore(autoInit = false)

                val labels = mutableListOf<BreedListLabel>()

                collectLabels(store, labels).run {
                    // Action
                    store.init()

                    cancelAndJoin()
                }

                "should display error" {
                    labels.shouldContain(BreedListLabel.ShowErrorMessage)
                }
            }
        }
    }

    private fun createStore(autoInit: Boolean = true): BreedListStore {
        return object : BreedListStore,
            Store<BreedListIntent, BreedListState, BreedListLabel> by DefaultStoreFactory().create(
                initialState = BreedListState(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = { BreedListIntentExecutorImpl(interactor, dispatcherProvider) },
                reducer = BreedListReducer(),
                autoInit = autoInit,
            ) {}
    }

    private fun CoroutineScope.collectStates(store: BreedListStore, states: MutableList<BreedListState>): Job {
        return launch(Job() + Dispatchers.Unconfined) {
            store.states.collect { states.add(it) }
        }
    }

    private fun CoroutineScope.collectLabels(store: BreedListStore, labels: MutableList<BreedListLabel>): Job {
        return launch(Job() + Dispatchers.Unconfined) {
            store.labels.collect { labels.add(it) }
        }
    }

}
