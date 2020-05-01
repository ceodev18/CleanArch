package com.example.cleanarchme.views.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.cleanarchme.R
import com.example.cleanarchme.data.AndroidPermissionChecker
import com.example.cleanarchme.data.PlayServicesLocationDataSource
import com.example.cleanarchme.data.database.LocalDataSourceImpl
import com.example.cleanarchme.data.database.MovieDataBase
import com.example.cleanarchme.data.server.RemoteDataSourceImpl1
import com.example.cleanarchme.data.server.Retrofit
import com.example.cleanarchme.views.common.loadUrl
import com.example.data.repository.MoviesRepository
import com.example.data.repository.RegionRepository
import com.example.domain.Movie
import com.example.usecases.GetMovieById
import com.example.usecases.ToggleMovieFavorite
import kotlinx.android.synthetic.main.activity_detail.*
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

        movieDetailFavorite.setOnClickListener {
            presenter.onFavoriteMovieClick()
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun setUp() {
        val id = intent.getIntExtra(MOVIE_ID, 0)
        val movieRepository = MoviesRepository(
            LocalDataSourceImpl(MovieDataBase.build(this).movieDao()),
            RemoteDataSourceImpl1(Retrofit("https://api.themoviedb.org/3/").service),
            getString(R.string.api_key),
            RegionRepository(
                PlayServicesLocationDataSource(application),
                AndroidPermissionChecker(application)
            )
        )
        presenter = DetailPresenterImpl(
            this,
            DetailInteractorImpl(
                GetMovieById(movieRepository),
                ToggleMovieFavorite(movieRepository)
            ), id, Dispatchers.Main

        )
    }

    override fun setMovie(movie: Movie) {
        movieDetailSummary.text = movie.overview
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailInfo.setMovie(movie)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setFavorite(boolean: Boolean) {
        val image = getDrawable(if(boolean) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
        movieDetailFavorite.setImageDrawable(image)
    }
}
