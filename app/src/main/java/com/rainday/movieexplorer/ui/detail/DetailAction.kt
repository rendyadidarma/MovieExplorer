package com.rainday.movieexplorer.ui.detail

sealed interface DetailAction {
    data class SetupInit(val movieId: Int) : DetailAction
}