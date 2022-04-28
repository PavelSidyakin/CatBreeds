package com.cat_breeds.common.breed.breed_domain

import com.cat_breeds.common.breed.breed_domain.data.BreedLocalRepository
import com.cat_breeds.common.breed.breed_domain.data.BreedRemoteRepository
import com.cat_breeds.common.breed.breed_domain.model.Breed
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk

class BreedInteractorImplTestFreeSpec : FreeSpec() {

    init {
        isolationMode = IsolationMode.InstancePerTest

        val remoteRepository: BreedRemoteRepository = mockk(relaxed = true)
        val localRepository: BreedLocalRepository = mockk(relaxed = true)

        val interactor = BreedInteractorImpl(remoteRepository, localRepository)

        "The remote repository calls throw an exception" - {

            suspend fun FreeSpecContainerScope.verifyNoWriteInLocalRepository() {
                "shouldn't write to the local repository" - {
                    "shouldn't add" {
                        coVerify(exactly = 0) { localRepository.addBreeds(any()) }
                    }
                    "shouldn't clear" {
                        coVerify(exactly = 0) { localRepository.clearBreeds() }
                    }
                    "shouldn't clear and add" {
                        coVerify(exactly = 0) { localRepository.clearAndAddBreads(any()) }
                    }
                }
            }

            val errorMessage = "ceciwemciew"
            // Mock
            coEvery { remoteRepository.requestBreeds() } throws RuntimeException(errorMessage)
            coEvery { remoteRepository.requestBreed(any()) } throws RuntimeException(errorMessage)

            "forceUpdateBreeds() is called" - {
                // Action
                val result = kotlin.runCatching { interactor.forceUpdateBreeds() }
                "Should throw the exception" {
                    // verify
                    assertSoftly {
                        result.isFailure shouldBe true
                        result.exceptionOrNull() shouldBe RuntimeException(errorMessage)
                    }
                }
                verifyNoWriteInLocalRepository()
            }

            "initBreeds() is called" - {
                "has no local data" - {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns false

                    // Action
                    val result = kotlin.runCatching { interactor.initBreeds() }

                    "Should throw the exception" {
                        // verify
                        assertSoftly {
                            result.isFailure shouldBe true
                            result.exceptionOrNull() shouldBe RuntimeException(errorMessage)
                        }
                    }
                    verifyNoWriteInLocalRepository()
                }
                "has local data" - {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns true

                    // Action
                    val result = kotlin.runCatching { interactor.initBreeds() }

                    "Shouldn't throw an exception" {
                        // verify
                        assertSoftly {
                            result.isFailure shouldBe false
                        }
                    }
                    verifyNoWriteInLocalRepository()
                }
            }

            "requestBreed() is called" - {
                "Has breed in the local repository" - {
                    val breedId = "dwdewdew"
                    val breed: Breed = mockk(relaxed = true)
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns breed

                    // Action
                    val result = interactor.requestBreed(breedId)

                    "Should return the local breed" {
                        // Action & verify
                        result shouldBe breed
                    }
                    verifyNoWriteInLocalRepository()
                }
                "Has no breed in the local repository" - {
                    val breedId = "dwdewdew"
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns null

                    // Action
                    val result = kotlin.runCatching { interactor.requestBreed(breedId) }

                    "Should throw the exception" {
                        // verify
                        assertSoftly {
                            result.isFailure shouldBe true
                            result.exceptionOrNull() shouldBe RuntimeException(errorMessage)
                        }
                    }
                    verifyNoWriteInLocalRepository()
                }
            }
        }

        "The remote repository calls return valid data" - {
            val remoteBreedList = listOf(createBreed(id = "1"), createBreed(id = "2"), createBreed(id = "3"))
            // Mock
            coEvery { remoteRepository.requestBreeds() } returns remoteBreedList
            coEvery { remoteRepository.requestBreed(any()) } answers {
                val breedId = firstArg<String>()
                createBreed(id = breedId)
            }

            "forceUpdateBreeds() is called" - {
                // Action
                interactor.forceUpdateBreeds()
                "Should put the data to the local repository" {
                    // verify
                    coVerify { localRepository.clearAndAddBreads(remoteBreedList) }
                }
            }

            "initBreeds() is called" - {
                "has no local data" - {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns false

                    // Action
                    interactor.initBreeds()
                    "Should put the data to the local repository" {
                        // Verify
                        coVerify { localRepository.addBreeds(remoteBreedList) }
                    }
                }
                "has local data" - {
                    // Mock
                    coEvery { localRepository.hasBreeds() } returns true

                    // Action
                    interactor.initBreeds()
                    "Shouldn't alter local repository" {
                        // Verify
                        coVerify { localRepository.hasBreeds() }
                        confirmVerified(localRepository)
                    }
                }
            }

            "requestBreed() is called" - {
                "Has breed in the local repository" - {
                    val breedId = "dwdewdew"
                    val breed: Breed = mockk(relaxed = true)
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns breed

                    "Should return the local breed" {
                        // Action & verify
                        interactor.requestBreed(breedId) shouldBe breed
                    }
                }
                "Has no breed in the local repository" - {
                    val breedId = "ncjdncke"
                    // Mock
                    coEvery { localRepository.selectBreed(breedId) } returns null

                    val expectedBreed = createBreed(breedId)
                    var returnedBreed: Breed? = null

                    // mock
                    coEvery { remoteRepository.requestBreed(breedId) } returns expectedBreed

                    // Action
                    returnedBreed = interactor.requestBreed(breedId)

                    "Should request breed in the remote repository" {
                        // verify
                        coVerify { remoteRepository.requestBreed(breedId) }
                    }
                    "Should put the breed in the local repository" {
                        // verify
                        coVerify { localRepository.addBreeds(listOf(expectedBreed)) }
                    }
                    "Returned breed should be the remote breed" {
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