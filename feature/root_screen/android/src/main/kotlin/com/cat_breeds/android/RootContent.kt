package com.cat_breeds.android

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListScreen

@Composable
fun RootContent(component: CatBreedsRoot) {
    Children(routerState = component.routerState) {
        when (val child = it.instance) {
            is CatBreedsRoot.ChildComponent.BreedListChild -> BreedListScreen()
        }
    }
}