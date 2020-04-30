package com.example.cleanarchme.views.detail

import com.example.domain.Movie

interface DetailContract {

    interface DetailView {
        fun setMovie(movie: Movie)
    }

    interface DetailPresenter {
        fun detach()
        fun onLoadInfo()
    }

    interface DetailInteractor {
        suspend fun findMovieById(id: Int) : Movie
    }

}