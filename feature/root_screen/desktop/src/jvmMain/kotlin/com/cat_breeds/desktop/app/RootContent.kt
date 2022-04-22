package com.cat_breeds.desktop.app

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.cat_breeds.common.CatBreedsRoot
import com.cat_breeds.common.breed_info.breed_info_ui.compose.BreedInfoScreen
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import com.cat_breeds.common.breed_list.breed_list_ui.compose.BreedListScreen

@Composable
fun RootContent(component: CatBreedsRoot) {
    Children(routerState = component.routerState) {
        when (val child = it.instance) {
            is CatBreedsRoot.ChildComponent.BreedListChild -> BreedListScreen(child.component)
            is CatBreedsRoot.ChildComponent.BreedInfoChild -> BreedInfoScreen(child.component)
        }
    }
}