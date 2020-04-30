package com.example.cleanarchme.views.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cleanarchme.R
import com.example.cleanarchme.data.AndroidPermissionChecker
import com.example.cleanarchme.data.PlayServicesLocationDataSource
import com.example.cleanarchme.views.common.PermissionRequester
import com.example.cleanarchme.data.database.LocalDataSourceImpl
import com.example.cleanarchme.data.database.MovieDataBase
import com.example.cleanarchme.data.server.RemoteDataSourceImpl1
import com.example.cleanarchme.data.server.Retrofit
import com.example.cleanarchme.views.detail.DetailActivity
import com.example.data.repository.MoviesRepository
import com.example.data.repository.RegionRepository
import com.example.domain.Movie
import com.example.usecases.GetPopularMovies
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    private lateinit var presenter: MainContract.MainPresenter
    private val permissionRequester = PermissionRequester(this, ACCESS_COARSE_LOCATION)
    private val moviesAdapter by lazy {
        MoviesAdapter {
            presenter.onMovieClick(it.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()
    }

    private fun setUp(){
        presenter = MainPresenterImpl(
            this,
            MainInteractorImpl(
                GetPopularMovies(
                    MoviesRepository(
                        LocalDataSourceImpl(MovieDataBase.build(this).movieDao()),
                        RemoteDataSourceImpl1(Retrofit("https://api.themoviedb.org/3/").service),
                        getString(R.string.api_key),
                        RegionRepository(
                            PlayServicesLocationDataSource(application),
                            AndroidPermissionChecker(application)
                        )
                    )
                )
            ),
            Dispatchers.Main
        )

        recyclerMovies.apply {
            adapter = moviesAdapter
        }
        presenter.onLoadMovies()

        permissionRequester.request {
            Log.e("asd", "permission was: $it")
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun setupMovies(movies: List<Movie>) {
        moviesAdapter.movies = movies
    }

    override fun navigateToDetail(id: Int) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE_ID,id)
        startActivity(intent)
    }
}
