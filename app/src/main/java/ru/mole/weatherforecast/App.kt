package ru.mole.weatherforecast

import android.app.Application
import ru.mole.weatherforecast.broadcastReceiver.ConnectivityReceiver
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.di.AppComponent
import ru.mole.weatherforecast.di.AppContextModule
import ru.mole.weatherforecast.di.AppDatabaseModule
import ru.mole.weatherforecast.di.DaggerAppComponent
import ru.mole.weatherforecast.ui.detailScreen.DetailHostActivity
import ru.mole.weatherforecast.ui.detailScreen.DetailHostDIComponent
import ru.mole.weatherforecast.ui.detailScreen.DetailHostDIModule
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIComponent
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIModule
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayFragment
import ru.mole.weatherforecast.ui.mainScreen.MainActivity
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .appContextModule(AppContextModule(this))
            .weatherAPINetworkModule(WeatherAPINetworkModule())
            .appDatabaseModule(AppDatabaseModule())
            .build()
    }

    fun createMainActivityComponent(activity: MainActivity): MainDIComponent {
        return appComponent.createMainActivityComponent(MainDIModule(activity))
    }

    fun createDetailHostActivityComponent(activity: DetailHostActivity): DetailHostDIComponent {
        return appComponent.createDetailHostActivityComponent(DetailHostDIModule(activity))
    }

    fun createCurrentDayFragmentComponent(fragment: CurrentDayFragment): CurrentDayDIComponent {
        return appComponent.createCurrentDayFragmentComponent(CurrentDayDIModule(fragment))
    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }

    fun removeConnectivityListener() {
        ConnectivityReceiver.connectivityReceiverListener = null
    }

    companion object {
        const val KELVIN = 273.15
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }

    }
}