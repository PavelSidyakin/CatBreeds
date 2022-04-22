package com.cat_breeds.common.breed_list.breed_list_ui.mvi.store

sealed interface BreedListLabel {
    data class NavigateToBreedInfo(val breedId: String): BreedListLabel
}
