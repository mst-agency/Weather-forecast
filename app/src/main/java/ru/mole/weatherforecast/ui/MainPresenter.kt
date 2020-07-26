package ru.mole.weatherforecast.ui

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.domain.GetCurrentForecast

class MainPresenter(private val view: MainContract.View, private val getCurrentForecast: GetCurrentForecast) : MainContract.Presenter {

    override fun requestListCityForecast() {
        val executePullMethods = getCurrentForecast.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                Log.d("111", "111")
            }, { error ->

            })
    }

}