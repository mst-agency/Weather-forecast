package ru.mole.weatherforecast.ui.mainScreen

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.ui.mainScreen.MainContract

class MainPresenter(private val view: MainContract.View, private val getCurrentForecast: GetCurrentForecast) : MainContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun requestListCityForecast(cities: List<String>) {
        val listCityForecast = Observable.fromIterable(cities).flatMapSingle {
            getCurrentForecast.execute(it)
        }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onShowListCityForecast(it)
            }, { error ->

            })
        disposables.add(listCityForecast)
    }

    override fun requestCityForecast(city: String) {
        val cityForecast = getCurrentForecast.execute(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onUpdateCityForecast(it)
            }, { error ->

            })
        disposables.add(cityForecast)
    }

    override fun detachView() {
        disposables.dispose()
    }

}