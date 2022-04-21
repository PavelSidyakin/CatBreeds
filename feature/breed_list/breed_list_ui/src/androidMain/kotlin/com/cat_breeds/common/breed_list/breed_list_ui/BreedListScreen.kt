package com.cat_breeds.common.breed_list.breed_list_ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
actual fun BreedListScreen(component: BreedList) {
    val model by component.models.subscribeAsState()

    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(model.breeds) {
            Text(text = it.name)
        }
    }
}