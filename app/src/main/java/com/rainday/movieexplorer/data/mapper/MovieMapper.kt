package com.rainday.movieexplorer.data.mapper

import com.rainday.movieexplorer.data.remote.dto.GenreDto
import com.rainday.movieexplorer.data.remote.dto.MovieDetailDto
import com.rainday.movieexplorer.data.remote.dto.MovieDto
import com.rainday.movieexplorer.data.remote.dto.ReviewDto
import com.rainday.movieexplorer.data.remote.dto.VideoDto
import com.rainday.movieexplorer.domain.model.Genre
import com.rainday.movieexplorer.domain.model.Movie
import com.rainday.movieexplorer.domain.model.MovieDetail
import com.rainday.movieexplorer.domain.model.MovieTrailer
import com.rainday.movieexplorer.domain.model.Review

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

fun ReviewDto.toDomain(): Review {
    return Review(
        author = author,
        content = content,
        dateText = createdAt.take(10), // simple YYYY-MM-DD
        ratingText = authorDetails.rating?.let { "Rating: $it/10" }
    )
}

fun List<VideoDto>.toDomainTrailer(): MovieTrailer? {
    val youtubeVideos = filter { it.site == "YouTube" }

    val trailer = youtubeVideos.firstOrNull {
        it.type == "Trailer" && it.official
    }

    val teaserFallback = youtubeVideos.firstOrNull {
        it.type == "Teaser" && it.official
    }

    return (trailer ?: teaserFallback)?.let {
        MovieTrailer(youtubeKey = it.key)
    }
}