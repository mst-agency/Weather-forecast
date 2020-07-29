package ru.mole.weatherforecast.ui.detailScreen

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.data.database.model.DataForecastDetail
import ru.mole.weatherforecast.domain.DetailTransactionDBUseCase
import ru.mole.weatherforecast.domain.GetSeveralForecast
import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.Daily
import ru.mole.weatherforecast.domain.model.Temp

class DetailHostPresenter(private var view: DetailHostContract.View?, private val getSeveralForecast: GetSeveralForecast,
                          private val transactionDB: DetailTransactionDBUseCase) : DetailHostContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun refreshForecast(city: String, coord: Coordinate?, isConnected: Boolean) {
        if (isConnected) {
            val isExistDetailCity = transactionDB.isExistDetailCity(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isExist ->
                    coord?.let {
                        val cityForecast = getSeveralForecast.execute(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ daily ->
                                view?.onSetData(daily.daily)
                                if (isExist) {
                                    transactionDB.deleteDetailCity(city).doOnComplete {
                                        Observable.fromIterable(daily.daily).map {
                                            transactionDB.insertDetailCity(DataForecastDetail(id = 0, city = city, date = it.dt?:1, lat = coord.lat,
                                                lon = coord.lon, tempMax = it.temp?.max?:1.0, tempMin = it.temp?.min?:1.0, windSpeed = it.wind_speed?:1.0))
                                                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                                .subscribe({ }, { error ->
                                                    view?.onShowError(error.localizedMessage)
                                                })
                                        }
                                    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({ }, { error ->
                                            view?.onShowError(error.localizedMessage)
                                        })
                                } else {
                                    Observable.fromIterable(daily.daily).map {
                                        transactionDB.insertDetailCity(DataForecastDetail(id = 0, city = city, date = it.dt?:1, lat = coord.lat,
                                            lon = coord.lon, tempMax = it.temp?.max?:1.0, tempMin = it.temp?.min?:1.0, windSpeed = it.wind_speed?:1.0))
                                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                            .subscribe({ }, {})
                                    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({ }, { error ->
                                            view?.onShowError(error.localizedMessage)
                                        })
                                }
                            }, { error ->
                                view?.onShowError(error.localizedMessage)
                            })
                        disposables.add(cityForecast)
                    }
                }, { error ->
                    view?.onShowError(error.localizedMessage)
                })
            disposables.add(isExistDetailCity)
        } else {
            val isExistDetailCity = transactionDB.isExistDetailCity(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isExist ->
                    coord?.let {
                        if (isExist) {
                            transactionDB.getDetailCities(city).toObservable().flatMapIterable {
                                it
                            }.map {
                                Daily(dt = it.date,
                                    sunrise = null,
                                    sunset = null,
                                    temp = Temp(day = null, min = it.tempMin, max = it.tempMax, night = null, eve = null, morn = null),
                                    feels_like = null,
                                    pressure = null,
                                    humidity = null,
                                    dew_point = null,
                                    wind_speed = it.windSpeed,
                                    wind_deg = null,
                                    weather = null,
                                    clouds = null,
                                    pop = null,
                                    rain = null,
                                    uvi = null)
                            }.toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    view?.onSetData(it)
                                }, { error ->
                                    view?.onShowError(error.localizedMessage)
                                })
                        } else {
                            view?.onSetData(ArrayList())
                        }
                    }
                }, { error ->
                    view?.onShowError(error.localizedMessage)
                })
            disposables.add(isExistDetailCity)
        }
    }

    override fun onClickCurrentForecast() {
        view?.onShowDetailCurrent()
    }

    override fun onClickThreeDaysForecast() {
        view?.onShowForecastThreeDay()
    }

    override fun onClickSevenDaysForecast() {
        view?.onShowForecastSevenDay()
    }

    override fun onBackPressed() {
        view?.onShowMainScreen()
    }


    override fun detachView() {
        view = null
    }
}