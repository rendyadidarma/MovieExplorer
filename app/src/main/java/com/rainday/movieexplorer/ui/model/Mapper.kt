package com.rainday.movieexplorer.ui.model

import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail
import com.rainday.movieexplorer.domain.model.MovieTrailer
import com.rainday.movieexplorer.domain.model.Review
import java.util.Locale

fun Genre.toUiModel(): GenreUiModel {
    return GenreUiModel(
        id = this.id,
        name = this.name
    )
}

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        title = title,
        posterUrl = posterUrl?.let {
            "https://image.tmdb.org/t/p/w500$it"
        },
        ratingText = String.format(Locale.ROOT, "%.1f", rating),
        releaseYear = releaseDate?.take(4)
    )
}

fun MovieDetail.toUiModel(): MovieDetailUiModel {
    return MovieDetailUiModel(
        title = title,
        tagline = tagline,
        overview = overview,
        posterUrl = posterUrl?.let {
            "https://image.tmdb.org/t/p/w500$it"
        },
        backdropUrl = backdropUrl?.let {
            "https://image.tmdb.org/t/p/w780$it"
        },
        releaseDateText = releaseDate ?: "Unknown",
        runtimeText = runtimeMinutes?.let { "$it min" } ?: "—",
        ratingText = String.format(Locale.ROOT, "%.1f (%d votes)", rating, voteCount),
        genresText = genres.joinToString(", ") { it.name },
        homepage = homepage
    )
}

@JvmName("movieListMapper")
fun List<Movie>.toUiModel(): List<MovieUiModel> =
    map { it.toUiModel() }

@JvmName("genreListMapper")
fun List<Genre>.toUiModel(): List<GenreUiModel> =
    map { it.toUiModel() }

fun MovieTrailer.toUiModel(): MovieTrailerUiModel {
    return MovieTrailerUiModel(
        youtubeId = youtubeKey
    )
}

fun Review.toUiModel(): ReviewUiModel {
    return ReviewUiModel(
        authorText = "By $author",
        contentPreview = content,
        dateText = dateText.substringBefore("T")
    )
}

@JvmName("reviewListMapper")
fun List<Review>.toUiModels(): List<ReviewUiModel> =
    map { it.toUiModel() }