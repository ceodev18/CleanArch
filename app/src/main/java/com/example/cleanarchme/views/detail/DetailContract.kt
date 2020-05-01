package com.example.cleanarchme.views.detail

import com.example.domain.Movie

interface DetailContract {

    interface DetailView {
        fun setMovie(movie: Movie)
        fun setFavorite(boolean: Boolean)
    }

    interface DetailPresenter {
        fun detach()
        fun onLoadInfo()
        fun onFavoriteMovieClick()
    }

    interface DetailInteractor {
        suspend fun findMovieById(id: Int) : Movie
        suspend fun favoriteMovie(movie: Movie) : Movie
    }

}