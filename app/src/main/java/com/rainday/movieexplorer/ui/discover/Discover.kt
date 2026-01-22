package com.rainday.movieexplorer.ui.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rainday.movieexplorer.data.repository.RepositoryProvider.movieRepository
import com.rainday.movieexplorer.ui.ViewModelFactory
import com.rainday.movieexplorer.ui.discover.component.MovieCard
import com.rainday.movieexplorer.ui.genre.colorFromId
import com.rainday.movieexplorer.ui.genre.contentColorFor
import com.rainday.movieexplorer.ui.model.GenreUiModel
import com.rainday.movieexplorer.ui.theme.MovieExplorerTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun DiscoverRoot(
    viewModel: DiscoverViewModel = viewModel(
        factory = ViewModelFactory {
            DiscoverViewModel(movieRepository)
        }
    ),
    genre: GenreUiModel,
    navigateToDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(DiscoverAction.SetupInit(genreId = genre.id, page = 1))
    }

    DiscoverScreen(
        state = state,
        genre = genre,
        navigateToDetail = navigateToDetail,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    state: DiscoverState,
    onAction: (DiscoverAction) -> Unit,
    genre: GenreUiModel,
    navigateToDetail: (Int) -> Unit
) {
    val appBarBackground = remember {
        colorFromId(genre.id.toInt())
    }

    val listState = rememberSaveable(
        saver = LazyListState.Saver
    ) {
        LazyListState()
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(genre.name, style = MaterialTheme.typography.titleMedium) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appBarBackground,
                    titleContentColor = contentColorFor(appBarBackground)
                )
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.error.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error.uppercase(), modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        } else {
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize(),
                state = listState
            ) {
                items(state.movies, key = { it.id }) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = navigateToDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }

                if (state.isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            val layout = listState.layoutInfo
            val lastVisible = layout.visibleItemsInfo.lastOrNull()?.index ?: 0
            val total = layout.totalItemsCount
            lastVisible >= total - 3
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onAction(DiscoverAction.LoadNext(genreId = genre.id))
            }
    }
}

@Preview
@Composable
private fun Preview() {
    MovieExplorerTheme {
        DiscoverScreen(
            state = DiscoverState(),
            genre = GenreUiModel("", ""),
            navigateToDetail = {},
            onAction = {

            }
        )
    }
}