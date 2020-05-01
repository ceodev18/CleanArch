package com.example.cleanarchme.views.main

import android.view.View
import android.widget.AdapterView
import com.example.cleanarchme.R

class ManagerSpinnerMovies(private val presenter: MainContract.MainPresenter) : AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.adapter?.getItem(position) as String) {
            parent.context.getString(R.string.all) -> {
                presenter.onLoadMovies()
            }

            parent.context.getString(R.string.favorite) -> {
                presenter.onLoadFavoritesMovies()
            }
        }
    }
}