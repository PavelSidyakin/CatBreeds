package com.cat_breeds.common.breed_list.breed_list_ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListComponent
import com.cat_breeds.common.breed_list.breed_list_ui.BreedListUiItem
import com.cat_breeds.resources.SharedRes
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
actual fun BreedListScreen(component: BreedListComponent) {
    val model by component.models.subscribeAsState()
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val errorMessage = StringDesc.Resource(SharedRes.strings.error_message)
        .toString(LocalContext.current)

    component.setOnEventListener { event ->
        when (event) {
            BreedListComponent.Event.Error -> scope.launch {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

//        when (model.isLoading) {
//            true -> Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = Color.Gray),
//                contentAlignment = Alignment.Center,
//            ) {
//                CircularProgressIndicator(color = Color.Blue)
//            }
//            false -> {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val columnCount = max((maxWidth.value / 180).toInt(), 1)

            SwipeRefresh(
                state = rememberSwipeRefreshState(model.isLoading),
                onRefresh = { component.onRefresh() }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState(),
                ) {
                    items(items = model.breeds.chunked(columnCount)) { breedListRowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            breedListRowItems.forEach { breedListItem: BreedListUiItem ->
                                Card(
                                    modifier = Modifier
                                        .height(300.dp)
                                        .width(160.dp)
                                        .padding(all = 10.dp)
                                        .clickable {
                                            component.onBreedClicked(breedListItem.id)
                                        },
                                    elevation = 4.dp,
                                ) {
                                    Column(
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        when (breedListItem.imageUrl) {
                                            null -> Text(
                                                modifier = Modifier
                                                    .padding(top = 10.dp)
                                                    .width(200.dp),
                                                text = StringDesc.Resource(SharedRes.strings.no_image)
                                                    .toString(LocalContext.current),
                                                textAlign = TextAlign.Center,
                                            )
                                            else -> GlideImage(
                                                modifier = Modifier.size(width = 200.dp, height = 200.dp),
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
                                            modifier = Modifier.width(200.dp),
                                            text = breedListItem.name,
                                            textAlign = TextAlign.Center,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //}
        }
        SnackbarHost(hostState = snackbarHostState)
        //}
    }
}