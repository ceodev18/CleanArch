package com.example.cleanarchme.views.detail

import com.example.domain.Movie

interface DetailContract {

    interface DetailView {
        fun setMovie(movie: Movie)
        fun setFavorite(boolean: Boolean)
    }

    interface DetailPresenter {
        fun detach()
        fun attach(view: DetailView)
        fun onLoadInfo()
        fun onFavoriteMovieClick()
    }
}