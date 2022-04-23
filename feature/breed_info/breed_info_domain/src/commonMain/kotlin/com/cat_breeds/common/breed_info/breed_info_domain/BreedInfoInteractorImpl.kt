package com.cat_breeds.common.breed_info.breed_info_domain

import com.cat_breeds.common.breed.breed_domain.BreedInteractor
import com.cat_breeds.common.breed_info.breed_info_domain.model.BreedInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class BreedInfoInteractorImpl(
    private val breedInteractor: BreedInteractor,
) : BreedInfoInteractor {
    override fun observeBreedInfo(id: String): Flow<BreedInfo?> {
        return breedInteractor.observeBreed(id).map { breed ->
            BreedInfo(
                id = breed.id,
                name = breed.name,
                imageUrl = breed.imageUrl,
                origin = breed.origin,
                description = breed.description,
                temperament = breed.temperament,
            )
        }
    }
}