package com.rainday.movieexplorer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.rainday.movieexplorer.ui.detail.DetailRoot
import com.rainday.movieexplorer.ui.discover.DiscoverRoot
import com.rainday.movieexplorer.ui.genre.GenreRoot

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Screen.Genre)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Screen.Genre> {
                GenreRoot(
                    navigateToDiscover = {
                        backStack.add(Screen.Discover(it))
                    }
                )
            }

            entry<Screen.Discover> { param ->
                DiscoverRoot(
                    genre = param.genre,
                    navigateToDetail = { movieId ->
                        backStack.add(Screen.Detail(movieId))
                    }
                )
            }

            entry<Screen.Detail> { param ->
                DetailRoot(
                    movieId = param.movieId,
                    navigateBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}