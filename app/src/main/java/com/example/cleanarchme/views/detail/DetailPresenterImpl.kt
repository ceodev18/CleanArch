package com.example.cleanarchme.views.detail

import com.example.cleanarchme.views.common.Scope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailPresenterImpl(
    private var view: DetailContract.DetailView?,
    private val interactor: DetailContract.DetailInteractor,
    private val movieId: Int,
    uiDispatcher: CoroutineDispatcher
) :
    DetailContract.DetailPresenter, Scope by Scope.Impl(uiDispatcher){

    init {
        initScope()
    }

    override fun detach() {
        this.view = null
        destroyScope()
    }

    override fun onLoadInfo() {
        launch {
            val movie = interactor.findMovieById(movieId)
            view?.setMovie(movie)
        }
    }
}