package ru.mole.weatherforecast.data.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.mole.weatherforecast.data.database.model.DataForecastByCity
import ru.mole.weatherforecast.data.database.model.DataForecastDetail
import ru.mole.weatherforecast.domain.model.Daily

@Dao
interface DataForecastDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(daily: DataForecastDetail): Completable

    @Query("SELECT EXISTS(SELECT * FROM DetailForecast WHERE city = :city)")
    fun isExistsDetailCity(city: String): Maybe<Boolean>

    @Query("DELETE FROM DetailForecast WHERE city = :city")
    fun deleteDetailCity(city: String): Completable

    @Query("SELECT * FROM DetailForecast WHERE city = :city")
    fun getData(city: String): Maybe<List<DataForecastDetail>>
}