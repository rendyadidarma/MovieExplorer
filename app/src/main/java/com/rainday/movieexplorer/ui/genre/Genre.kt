package com.rainday.movieexplorer.ui.genre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rainday.movieexplorer.data.repository.RepositoryProvider.movieRepository
import com.rainday.movieexplorer.ui.ViewModelFactory
import com.rainday.movieexplorer.ui.model.GenreUiModel
import com.rainday.movieexplorer.ui.theme.MovieExplorerTheme
import kotlin.random.Random

@Composable
fun GenreRoot(
    viewModel: GenreViewModel = viewModel(
        factory = ViewModelFactory {
            GenreViewModel(movieRepository)
        }
    ),
    navigateToDiscover: (GenreUiModel) -> Unit = { _ -> }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GenreScreen(
        state = state,
        navigateToDiscover = navigateToDiscover
    )
}

@Composable
fun GenreScreen(
    state: GenreState,
    navigateToDiscover: (GenreUiModel) -> Unit,
) {
    val listState = rememberSaveable(
        saver = LazyGridState.Saver
    ) {
        LazyGridState()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = state.error.uppercase(), modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                state = listState
            ) {
                items(state.genres, key = { it.id }) { genre ->
                    val backgroundColor = remember(genre.id) {
                        colorFromId(genre.id.toInt())
                    }

                    val textColor = contentColorFor(backgroundColor)

                    Card(
                        onClick = {
                            navigateToDiscover.invoke(genre)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = backgroundColor,
                            contentColor = textColor
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = genre.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

fun contentColorFor(background: Color): Color {
    return if (background.luminance() > 0.5f) {
        Color.Black
    } else {
        Color.White
    }
}

fun colorFromId(id: Int): Color {
    val random = Random(id)
    return Color(
        red = random.nextFloat(),
        green = random.nextFloat(),
        blue = random.nextFloat(),
        alpha = 1f
    )
}

@Preview
@Composable
private fun Preview() {
    MovieExplorerTheme {
        GenreScreen(
            state = GenreState(),
        ) {}
    }
}