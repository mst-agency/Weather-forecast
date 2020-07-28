package ru.mole.weatherforecast.di

import dagger.Component
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WeatherAPINetworkModule::class]
)
interface AppComponent {

    fun createMainActivityComponent(module: MainDIModule): MainDIComponent

}
