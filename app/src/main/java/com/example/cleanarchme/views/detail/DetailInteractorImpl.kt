package com.example.cleanarchme.views.detail

import com.example.domain.Movie
import com.example.usecases.GetMovieById
import com.example.usecases.ToggleMovieFavorite

class DetailInteractorImpl(
    private val getMovieById: GetMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite
) :
    DetailContract.DetailInteractor {

    override suspend fun findMovieById(id: Int): Movie {
        return getMovieById.invoke(id)
    }

    override suspend fun favoriteMovie(movie: Movie) : Movie {
        return toggleMovieFavorite.invoke(movie)
    }
}