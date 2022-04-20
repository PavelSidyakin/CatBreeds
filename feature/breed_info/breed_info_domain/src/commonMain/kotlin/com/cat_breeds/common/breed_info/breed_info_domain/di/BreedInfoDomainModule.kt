package com.cat_breeds.common.breed_info.breed_info_domain.di

import com.cat_breeds.common.breed_info.breed_info_domain.BreedInfoInteractor
import com.cat_breeds.common.breed_info.breed_info_domain.BreedInfoInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val breedInfoDomainModule = DI.Module("breedInfoDomainModule") {
    bind<BreedInfoInteractor> { singleton { BreedInfoInteractorImpl(instance()) } }
}
