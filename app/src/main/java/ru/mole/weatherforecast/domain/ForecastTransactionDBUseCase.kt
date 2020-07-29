package ru.mole.weatherforecast.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.data.database.dao.DataForecastDAO
import ru.mole.weatherforecast.data.database.model.DataForecastByCity
import ru.mole.weatherforecast.domain.model.CurrentDayForecast

class ForecastTransactionDBUseCase(private val cityForecastDao: DataForecastDAO) {

    fun insertAllData(params: List<CurrentDayForecast>): Completable {
        return Observable.fromIterable(params).map {
            DataForecastByCity(id = 0,
                city = it.name ?: "",
                date = it.dt ?: 1,
                tempMax = it.main?.temp_max ?: 1.0,
                tempMin = it.main?.temp_min ?: 1.0,
                tempFeeling = it.main?.feels_like ?: 1.0,
                windSpeed = it.wind?.speed ?: 1.0,
                lat = it.coord?.lat ?: 1.0,
                lon = it.coord?.lon ?: 1.0)
        }.map {
            cityForecastDao.isExistsCity(it.city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isExistCity ->
                    if (isExistCity) {
                        cityForecastDao.updateDataByCity(city = it.city, date = it.date, tempMax = it.tempMax, tempMin = it.tempMin,
                            tempFeeling = it.tempFeeling, windSpeed = it.windSpeed, lat = it.lat, lon = it.lon).subscribeOn(Schedulers.io()).subscribe({}, {})
                    } else {
                        cityForecastDao.insertData(it).subscribeOn(Schedulers.io()).subscribe({}, {})
                    }

                }, {})
        }.flatMapCompletable {
            Completable.complete()
        }
    }

    fun insertDataByCity(params: CurrentDayForecast): Completable {
        return Observable.fromCallable {
            DataForecastByCity(id = 0,
                city = params.name ?: "",
                date = params.dt ?: 1,
                tempMax = params.main?.temp_max ?: 1.0,
                tempMin = params.main?.temp_min ?: 1.0,
                tempFeeling = params.main?.feels_like ?: 1.0,
                windSpeed = params.wind?.speed ?: 1.0,
                lat = params.coord?.lat ?: 1.0,
                lon = params.coord?.lon ?: 1.0)
        }.flatMapCompletable {
            cityForecastDao.insertDataByCity(it).subscribeOn(Schedulers.io()).subscribe({}, {})
            Completable.complete()
        }
    }

    fun getNameCities(): Single<List<String>> {
        return cityForecastDao.getNameCities()
    }

    fun getForecast(): Maybe<List<DataForecastByCity>> {
        return cityForecastDao.getAllDataForecast()
    }

    fun isExistDB(): Maybe<Boolean> {
        return cityForecastDao.isExistsDB()
    }


}