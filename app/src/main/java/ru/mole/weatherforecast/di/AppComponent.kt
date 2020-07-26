package ru.mole.weatherforecast.di

import dagger.Component
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.ui.MainDIComponent
import ru.mole.weatherforecast.ui.MainDIModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WeatherAPINetworkModule::class]
)
interface AppComponent {

    fun createMainActivityComponent(module: MainDIModule): MainDIComponent

}
