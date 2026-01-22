package com.rainday.movieexplorer.data.mapper

import com.rainday.movieexplorer.data.remote.dto.GenreDto
import com.rainday.movieexplorer.data.remote.dto.MovieDetailDto
import com.rainday.movieexplorer.data.remote.dto.MovieDto
import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterPath,
        backdropUrl = backdropPath,
        releaseDate = releaseDate,
        rating = voteAverage,
        voteCount = voteCount,
        genreIds = genreIds,
        language = originalLanguage,
        popularity = popularity,
        isAdult = adult,
        hasVideo = video
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        tagline = tagline,
        overview = overview,
        posterUrl = posterPath,
        backdropUrl = backdropPath,
        releaseDate = releaseDate,
        runtimeMinutes = runtime,
        rating = voteAverage,
        voteCount = voteCount,
        genres = genres.map { it.toDomain() },
        language = originalLanguage,
        popularity = popularity,
        isAdult = adult,
        imdbId = imdbId,
        homepage = homepage
    )
}