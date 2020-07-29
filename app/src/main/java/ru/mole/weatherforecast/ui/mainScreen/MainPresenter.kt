package ru.mole.weatherforecast.ui.mainScreen

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.domain.ForecastTransactionDBUseCase
import ru.mole.weatherforecast.domain.GetCurrentForecast
import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.MainData
import ru.mole.weatherforecast.domain.model.Wind

class MainPresenter(private val view: MainContract.View, private val getCurrentForecast: GetCurrentForecast,
                    private val transactionDB: ForecastTransactionDBUseCase) : MainContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun refreshForecast(cities: List<String>, isConnected: Boolean) {
        if (isConnected) {
            val transactionDB = transactionDB.isExistDB().map {
                if (it) {
                    transactionDB.getNameCities().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ listCities ->
                            requestListForecast(listCities)
                        },
                            { error ->
                                view.onShowError(error.localizedMessage)
                            })
                } else {
                    requestListForecast(cities)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({},
                    { error ->
                        view.onShowError(error.localizedMessage)
                    })
            disposables.add(transactionDB)
        } else {
            val transactionDB = transactionDB.isExistDB().map { it ->
                if (it) {
                    val getForecast = transactionDB.getForecast().toObservable()
                        .flatMapIterable {
                            it
                        }
                        .map { data ->
                            CurrentDayForecast(
                                coord = Coordinate(lat = data.lat, lon = data.lon),
                                weather = null,
                                base = null,
                                main = MainData(temp = null,
                                    feels_like = data.tempFeeling,
                                    temp_max = data.tempMax,
                                    temp_min = data.tempMin,
                                    humidity = null,
                                    pressure = null),
                                visibility = null,
                                wind = Wind(speed = data.windSpeed, deg = null),
                                clouds = null,
                                dt = data.date,
                                sys = null,
                                timezone = null,
                                id = null,
                                name = data.city,
                                cod = null
                            )
                        }
                        .toList().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            view.onShowListCityForecast(it)
                        },
                            { error ->
                                view.onShowError(error.localizedMessage)
                            })
                    disposables.add(getForecast)
                } else {
                    view.onShowListCityForecast(ArrayList<CurrentDayForecast>())
                }
            }.subscribeOn(Schedulers.io()).subscribe({}, {})
            disposables.add(transactionDB)
        }
    }

    private fun requestListForecast(cities: List<String>) {
        val listCityForecast = Observable.fromIterable(cities).flatMapSingle {
            getCurrentForecast.execute(it)
        }.toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onShowListCityForecast(it)
                saveListDataToDB(it)
            }, { error ->
                view.onShowError(error.localizedMessage)
            })
        disposables.add(listCityForecast)
    }

    override fun requestCityForecast(city: String) {
        val cityForecast = getCurrentForecast.execute(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onUpdateCityForecast(it)
                saveDataToDB(it)
            }, { error ->
                view.onShowError(error.localizedMessage)
            })
        disposables.add(cityForecast)
    }

    private fun saveListDataToDB(listCities: List<CurrentDayForecast>) {
        val transactionDB = transactionDB.insertAllData(listCities)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error ->
                view.onShowError(error.localizedMessage)
            })
        disposables.add(transactionDB)
    }

    private fun saveDataToDB(data: CurrentDayForecast) {
        val transactionDB = transactionDB.insertDataByCity(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { error ->
                view.onShowError(error.localizedMessage)
            })
        disposables.add(transactionDB)
    }

    override fun detachView() {
        disposables.dispose()
    }

}