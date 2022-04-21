package com.cat_breeds.common.breed_list.breed_list_domain.di

import com.cat_breeds.common.breed_list.breed_list_domain.BreedListInteractor
import com.cat_breeds.common.breed_list.breed_list_domain.BreedListInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedListDomainModule = DI.Module("breedListDomainModule") {
    bind<BreedListInteractor> { singleton { BreedListInteractorImpl(instance()) } }
}
