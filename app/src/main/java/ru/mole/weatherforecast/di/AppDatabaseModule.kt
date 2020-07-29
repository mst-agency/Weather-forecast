package ru.mole.weatherforecast.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mole.weatherforecast.data.database.AppDatabase
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    private val DATABASE_NAME = "forecast"

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}