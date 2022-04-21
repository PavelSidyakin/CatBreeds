package com.cat_breeds.common.breed_list.breed_list_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
actual fun BreedListScreen(component: BreedList) {
    val model by component.models.subscribeAsState()

    val listState = rememberLazyListState()

    val cellWidthDp = (LocalConfiguration.current.smallestScreenWidthDp / 2 - 10).dp

    LazyStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        cells = StaggeredCells.Adaptive(minSize = cellWidthDp),
        state = listState,
    ) {
        items(model.breeds) { breedListItem ->
            Card(
                modifier = Modifier
                    .width(cellWidthDp)
                    .padding(all = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    when (breedListItem.imageUrl) {
                        null -> Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "No Image",
                        )
                        else -> GlideImage(
                            imageModel = breedListItem.imageUrl,
                            contentScale = ContentScale.FillWidth,
                            shimmerParams = ShimmerParams(
                                baseColor = MaterialTheme.colors.background,
                                highlightColor = Color.DarkGray,
                                durationMillis = 350,
                                dropOff = 0.65f,
                                tilt = 20f
                            )
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