package ru.mole.weatherforecast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mole.weatherforecast.data.database.dao.DataForecastDAO
import ru.mole.weatherforecast.data.database.model.DataForecastByCity

@Database(entities = [DataForecastByCity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dbDataForecast(): DataForecastDAO

}