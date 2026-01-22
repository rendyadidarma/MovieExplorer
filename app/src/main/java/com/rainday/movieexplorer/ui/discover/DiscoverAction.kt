package com.rainday.movieexplorer.ui.discover


sealed interface DiscoverAction {
    data class SetupInit(val genreId: String, val page: Int) : DiscoverAction
}