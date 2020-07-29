package ru.mole.weatherforecast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mole.weatherforecast.data.database.dao.DataForecastDAO
import ru.mole.weatherforecast.data.database.dao.DataForecastDetailDAO
import ru.mole.weatherforecast.data.database.model.DataForecastByCity
import ru.mole.weatherforecast.data.database.model.DataForecastDetail

@Database(entities = [DataForecastByCity::class, DataForecastDetail::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dbDataForecast(): DataForecastDAO

    abstract fun dbDataDetailForecast(): DataForecastDetailDAO

}