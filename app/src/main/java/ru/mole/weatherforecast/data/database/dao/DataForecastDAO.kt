package ru.mole.weatherforecast.data.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.mole.weatherforecast.data.database.model.DataForecastByCity

@Dao
interface DataForecastDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(dataAllForecast: DataForecastByCity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataByCity(dataForecastByCity: DataForecastByCity): Completable

    @Query("UPDATE Forecast SET date = :date AND temp_max = :tempMax AND temp_min = :tempMin AND temp_feeling = :tempFeeling AND wind_speed = :windSpeed AND lat = :lat AND lon = :lon WHERE city = :city")
    fun updateDataByCity(city: String, date: Long, tempMax: Double, tempMin: Double, tempFeeling: Double, windSpeed: Double, lat: Double, lon: Double): Completable

    @Query("SELECT * FROM Forecast")
    fun getAllDataForecast(): Maybe<List<DataForecastByCity>>

    @Query("SELECT city FROM Forecast")
    fun getNameCities(): Single<List<String>>

    @Query("SELECT EXISTS(SELECT * FROM Forecast WHERE city = :city)")
    fun isExistsCity(city: String): Maybe<Boolean>

    @Query("SELECT EXISTS(SELECT * FROM Forecast)")
    fun isExistsDB(): Maybe<Boolean>
}