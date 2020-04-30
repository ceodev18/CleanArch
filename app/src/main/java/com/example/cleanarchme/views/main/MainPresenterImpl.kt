package com.example.cleanarchme.views.main

import com.example.cleanarchme.views.common.Scope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainPresenterImpl(
    private var view: MainContract.MainView?,
    private val interactor: MainContract.MainInteractor,
    uiDispatcher: CoroutineDispatcher
) : MainContract.MainPresenter, Scope by Scope.Impl(uiDispatcher) {

    init {
        initScope()
    }

    override fun attach(view: MainContract.MainView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        destroyScope()
    }

    override fun onLoadMovies() {
        launch {
            val movies = interactor.retrieveMovies()
            view?.setupMovies(movies)
        }
    }
}