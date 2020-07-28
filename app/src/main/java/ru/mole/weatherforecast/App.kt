package ru.mole.weatherforecast

import android.app.Application
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.di.AppComponent
import ru.mole.weatherforecast.di.DaggerAppComponent
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIComponent
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIModule
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayFragment
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysDIComponent
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysDIModule
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysFragment
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysDIComponent
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysDIModule
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysFragment
import ru.mole.weatherforecast.ui.mainScreen.MainActivity
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .weatherAPINetworkModule(WeatherAPINetworkModule())
            .build()
    }

    fun createMainActivityComponent(activity: MainActivity): MainDIComponent {
        return appComponent.createMainActivityComponent(MainDIModule(activity))
    }

    fun createCurrentDayFragmentComponent(fragment: CurrentDayFragment): CurrentDayDIComponent {
        return appComponent.createCurrentDayFragmentComponent(CurrentDayDIModule(fragment))
    }

    fun createThreeDaysFragmentComponent(fragment: ThreeDaysFragment): ThreeDaysDIComponent {
        return appComponent.createThreeDaysFragmentComponent(ThreeDaysDIModule(fragment))
    }

    fun createSevenDaysFragmentComponent(fragment: SevenDaysFragment): SevenDaysDIComponent {
        return appComponent.createSevenDaysFragmentComponent(SevenDaysDIModule(fragment))
    }

    companion object {
        const val KELVIN = 273.15
        private lateinit var instance: App
        fun getInstance(): App {
            return instance
        }

    }
}