package com.cat_breeds.common.breed.breed_domain.di

import com.cat_breeds.common.breed.breed_domain.BreedInteractor
import com.cat_breeds.common.breed.breed_domain.BreedInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedDomainModule = DI.Module("breedDomainModule") {
    bind<BreedInteractor> { singleton { BreedInteractorImpl(instance(), instance()) } }
}
