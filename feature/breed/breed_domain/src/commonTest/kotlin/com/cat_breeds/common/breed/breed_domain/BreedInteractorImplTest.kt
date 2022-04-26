package com.cat_breeds.common.breed.breed_domain

import com.cat_breeds.common.breed.breed_domain.data.BreedLocalRepository
import com.cat_breeds.common.breed.breed_domain.data.BreedRemoteRepository
import com.cat_breeds.common.breed.breed_domain.model.Breed
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk

class BreedInteractorImplTest : BehaviorSpec() {

    init {
        isolationMode = IsolationMode.InstancePerTest

        val remoteRepository: BreedRemoteRepository = mockk(relaxed = true)
        val localRepository: BreedLocalRepository = mockk(relaxed = true)

        val interactor = BreedInteractorImpl(remoteRepository, localRepository)

        Given("The remote repository calls throw an exception") {
            afterEach {
                // Here should be verifyAll, but it doesn't work due to unknown reasons
                coVerify(exactly = 0) { localRepository.addBreeds(any()) }
                coVerify(exactly = 0) { localRepository.clearBreeds() }
                coVerify(exactly = 0) { localRepository.clearAndAddBreads(any()) }
            }

            val errorMessage = "ceciwemciew"
            // Mock
            coEvery { remoteRepository.requestBreeds() } throws RuntimeException(errorMessage)
            coEvery { remoteRepository.requestBreed(any()) } throws RuntimeException(errorMessage)

            When("forceUpdateBreeds() is called") {
                Then("Should throw the exception") {
                    // Action & verify
                    shouldThrowWithMessage<RuntimeException>(errorMessage) {
                        interactor.forceUpdateBreeds()
                    }
                }
            }

            When("initBreeds() is called") {
                And("has no local data") {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns false
                    Then("Should throw the exception") {
                        // Action & verify
                        shouldThrowWithMessage<RuntimeException>(errorMessage) {
                            interactor.initBreeds()
                        }
                    }
                }
                And("has local data") {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns true
                    Then("Shouldn't throw an exception") {
                        // Action & verify
                        shouldNotThrowAny {
                            interactor.initBreeds()
                        }
                    }
                }
            }

            When("requestBreed() is called") {
                And("Has breed in the local repository") {
                    val breedId = "dwdewdew"
                    val breed: Breed = mockk(relaxed = true)
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns breed

                    Then("Should return the local breed") {
                        // Action & verify
                        interactor.requestBreed(breedId) shouldBe breed
                    }
                }
                And("Has no breed in the local repository") {
                    val breedId = "dwdewdew"
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns null

                    Then("Should throw the exception") {
                        // Action & verify
                        shouldThrowWithMessage<RuntimeException>(errorMessage) {
                            interactor.requestBreed(breedId)
                        }
                    }
                }
            }
        }

        Given("The remote repository calls return valid data") {
            val remoteBreedList = listOf(createBreed(id = "1"), createBreed(id = "2"), createBreed(id = "3"))
            // Mock
            coEvery { remoteRepository.requestBreeds() } returns remoteBreedList
            coEvery { remoteRepository.requestBreed(any()) } answers {
                val breedId = firstArg<String>()
                createBreed(id = breedId)
            }

            When("forceUpdateBreeds() is called") {
                // Action
                interactor.forceUpdateBreeds()
                Then("Should put the data to the local repository") {
                    // verify
                    coVerify { localRepository.clearAndAddBreads(remoteBreedList) }
                }
            }

            When("initBreeds() is called") {
                And("has no local data") {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns false

                    // Action
                    interactor.initBreeds()
                    Then("Should put the data to the local repository") {
                        // Verify
                        coVerify { localRepository.addBreeds(remoteBreedList) }
                    }
                }
                And("has local data") {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns true

                    // Action
                    interactor.initBreeds()
                    Then("Shouldn't alter local repository") {
                        // Verify
                        coVerify { localRepository.hasBreeds() }
                        confirmVerified(localRepository)
                    }
                }
            }

            When("requestBreed() is called") {
                And("Has breed in the local repository") {
                    val breedId = "dwdewdew"
                    val breed: Breed = mockk(relaxed = true)
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns breed

                    Then("Should return the local breed") {
                        // Action & verify
                        interactor.requestBreed(breedId) shouldBe breed
                    }
                }
                And("Has no breed in the local repository") {
                    val breedId = "ncjdncke"
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns null

                    val expectedBreed = createBreed(breedId)
                    var returnedBreed: Breed? = null

                    // mock
                    coEvery { remoteRepository.requestBreed(breedId) } returns expectedBreed

                    // Action
                    returnedBreed = interactor.requestBreed(breedId)

                    Then("Should request breed in the remote repository") {
                        // verify
                        coVerify { remoteRepository.requestBreed(breedId) }
                        coVerify { localRepository.addBreeds(listOf(expectedBreed)) }
                        returnedBreed shouldBe expectedBreed
                    }
                    Then("Put the breed in the local repository") {
                        // verify
                        coVerify { localRepository.addBreeds(listOf(expectedBreed)) }
                    }
                    Then("Returned breed should be the remote breed") {
                        // verify
                        returnedBreed shouldBe expectedBreed
                    }
                }
            }
        }
    }

    private fun createBreed(
        id: String = "",
        name: String = "",
        imageUrl: String? = null,
        origin: String? = null,
        description: String? = null,
        temperament: String? = null,
    ): Breed {
        return Breed(id, name, imageUrl, origin, description, temperament)
    }
}