package ru.mole.weatherforecast.di

import dagger.Component
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WeatherAPINetworkModule::class]
)
interface AppComponent {

}
