package ru.mole.weatherforecast.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.data.database.dao.DataForecastDAO
import ru.mole.weatherforecast.data.database.dao.DataForecastDetailDAO
import ru.mole.weatherforecast.data.database.model.DataForecastByCity
import ru.mole.weatherforecast.data.database.model.DataForecastDetail
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily

class DetailTransactionDBUseCase(private val detailForecastDao: DataForecastDetailDAO) {

    fun isExistDetailCity(city: String): Maybe<Boolean> {
        return detailForecastDao.isExistsDetailCity(city)
    }

    fun deleteDetailCity(city: String): Completable {
        return detailForecastDao.deleteDetailCity(city)
    }

    fun insertDetailCity(daily: DataForecastDetail): Completable {
        return detailForecastDao.insertData(daily)
    }

    fun getDetailCities(city: String): Maybe<List<DataForecastDetail>> {
        return detailForecastDao.getData(city)
    }


}