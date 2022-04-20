package com.cat_breeds.common

import com.cat_breeds.common.breed_info.breed_info_data.di.breedInfoDataModule
import com.cat_breeds.common.breed_info.breed_info_domain.di.breedInfoDomainModule
import org.kodein.di.DI

class CommonApp {

    fun buildCommonDI(diBuilder: DI.Builder) {
        diBuilder.run {
            importOnce(breedInfoDataModule)
            importOnce(breedInfoDomainModule)
        }
    }
}
