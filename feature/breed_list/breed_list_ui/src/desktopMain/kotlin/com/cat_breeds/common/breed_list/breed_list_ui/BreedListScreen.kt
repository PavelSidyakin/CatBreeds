package com.cat_breeds.common.breed_list.breed_list_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
actual fun BreedListScreen(component: BreedList) {
    val model by component.models.subscribeAsState()

    val listState = rememberLazyListState()
    LazyStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        cells = StaggeredCells.Adaptive(minSize = 200.dp),
        state = listState,
    ) {
        items(model.breeds) { breedListItem ->
            Card(modifier = Modifier
                .width(200.dp)
                .padding(all = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    when (breedListItem.imageUrl) {
                        null -> Text(text = "No Image")
                        else -> AsyncImage(
                            load = { loadImageBitmap(breedListItem.imageUrl) },
                            painterFor = { remember { BitmapPainter(it) } },
                        )
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = breedListItem.name,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}