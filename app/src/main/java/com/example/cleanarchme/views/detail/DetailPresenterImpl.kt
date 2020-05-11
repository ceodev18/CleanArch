package com.example.cleanarchme.views.detail

import com.example.cleanarchme.views.common.Scope
import com.example.usecases.GetMovieById
import com.example.usecases.ToggleMovieFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import okhttp3.internal.waitMillis

class DetailPresenterImpl(
    private val getMovieById: GetMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite,
    private val movieId: Int,
    uiDispatcher: CoroutineDispatcher
) :
    DetailContract.DetailPresenter, Scope by Scope.Impl(uiDispatcher){

    private var view: DetailContract.DetailView? = null

    init {
        initScope()
    }

    override fun detach() {
        this.view = null
        destroyScope()
    }

    override fun attach(view: DetailContract.DetailView) {
        this.view = view
    }

    override fun onLoadInfo() {
        launch {
            val movie = getMovieById.invoke(movieId)
            view?.setMovie(movie)
            view?.setFavorite(movie.favorite)
        }
    }

    override fun onFavoriteMovieClick() {
        launch {
            val movie = getMovieById.invoke(movieId)
            val movieUpdate = toggleMovieFavorite.invoke(movie)
            view?.setFavorite(movieUpdate.favorite)
        }
    }
}