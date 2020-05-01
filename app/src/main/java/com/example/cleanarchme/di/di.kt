package com.antonioleiva.mymovies

import android.app.Application
import com.example.cleanarchme.R
import com.example.cleanarchme.data.AndroidPermissionChecker
import com.example.cleanarchme.data.PlayServicesLocationDataSource
import com.example.cleanarchme.data.database.LocalDataSourceImpl
import com.example.cleanarchme.data.database.MovieDataBase
import com.example.cleanarchme.data.server.RemoteDataSourceImpl1
import com.example.cleanarchme.data.server.Retrofit
import com.example.cleanarchme.views.main.MainActivity
import com.example.cleanarchme.views.main.MainContract
import com.example.cleanarchme.views.main.MainInteractorImpl
import com.example.cleanarchme.views.main.MainPresenterImpl
import com.example.data.PermissionChecker
import com.example.data.repository.MoviesRepository
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.usecases.GetFavoritesMovies
import com.example.usecases.GetPopularMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }
    single { MovieDataBase.build(get()).movieDao() }
    factory<LocalDataSource> { LocalDataSourceImpl(get()) }
    factory<RemoteDataSource> { RemoteDataSourceImpl1(Retrofit(get(named("baseUrl"))).service) }
    factory<LocationDataSource> { PlayServicesLocationDataSource(androidApplication()) }
    factory<PermissionChecker> { AndroidPermissionChecker(androidApplication()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory { MoviesRepository(get(), get(),  get(named("apiKey")),get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {

        scoped<MainContract.MainInteractor> {
            MainInteractorImpl(GetPopularMovies(get()), GetFavoritesMovies(get()))
        }

        scoped<MainContract.MainPresenter> {
            MainPresenterImpl(get(),get())
        }
    }


}