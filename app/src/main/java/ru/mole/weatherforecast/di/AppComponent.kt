package ru.mole.weatherforecast.di

import dagger.Component
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.ui.detailScreen.DetailHostDIComponent
import ru.mole.weatherforecast.ui.detailScreen.DetailHostDIModule
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIComponent
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIModule
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppContextModule::class, WeatherAPINetworkModule::class, AppDatabaseModule::class]
)
interface AppComponent {

    fun createMainActivityComponent(module: MainDIModule): MainDIComponent

    fun createDetailHostActivityComponent(module: DetailHostDIModule): DetailHostDIComponent

    fun createCurrentDayFragmentComponent(module: CurrentDayDIModule): CurrentDayDIComponent

}
