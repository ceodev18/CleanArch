package com.example.cleanarchme.views.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cleanarchme.R
import com.example.cleanarchme.data.AndroidPermissionChecker
import com.example.cleanarchme.data.PlayServicesLocationDataSource
import com.example.cleanarchme.data.database.LocalDataSourceImpl
import com.example.cleanarchme.data.database.MovieDataBase
import com.example.cleanarchme.data.server.RemoteDataSourceImpl1
import com.example.cleanarchme.data.server.Retrofit
import com.example.data.repository.MoviesRepository
import com.example.data.repository.RegionRepository
import com.example.domain.Movie
import com.example.usecases.GetMovieById
import kotlinx.coroutines.Dispatchers

class DetailActivity : AppCompatActivity(), DetailContract.DetailView {

    companion object {
        const val MOVIE_ID = "DetailActivity:MOVIE_ID"
    }

    private lateinit var presenter: DetailContract.DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setUp()
        presenter.onLoadInfo()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun setUp() {
        val id = intent.getIntExtra(MOVIE_ID, 0)
        presenter = DetailPresenterImpl(
            this, DetailInteractorImpl(
                GetMovieById(
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
            id,
            Dispatchers.Main

        )
    }

    override fun setMovie(movie: Movie) {
        Log.e("asd", "was founded $movie")
    }
}
