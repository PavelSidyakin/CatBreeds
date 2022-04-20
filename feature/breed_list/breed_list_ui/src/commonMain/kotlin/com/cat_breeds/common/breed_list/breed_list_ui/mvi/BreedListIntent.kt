package com.cat_breeds.common.breed_list.breed_list_ui.mvi

sealed interface BreedListIntent {
    data class Selected(val id: String): BreedListIntent
}