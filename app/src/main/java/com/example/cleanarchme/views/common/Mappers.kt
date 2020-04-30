package com.example.cleanarchme.views.common

import com.example.domain.Movie as MovieDomain
import com.example.cleanarchme.data.database.Movie as MovieDb
import com.example.cleanarchme.data.server.Movie as MovieServer

fun MovieDb.toDomain() =
    MovieDomain(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite
    )


fun MovieDomain.toMovieDb() =
    MovieDb(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite

    )

fun MovieServer.toDomainMovie() =
    MovieDomain(
        0,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath ?: posterPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        false
    )