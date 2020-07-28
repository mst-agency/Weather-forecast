package ru.mole.weatherforecast.di

import dagger.Component
import ru.mole.weatherforecast.data.network.WeatherAPINetworkModule
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIComponent
import ru.mole.weatherforecast.ui.detailScreen.currentDay.CurrentDayDIModule
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysDIComponent
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysDIModule
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysDIComponent
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysDIModule
import ru.mole.weatherforecast.ui.mainScreen.MainDIComponent
import ru.mole.weatherforecast.ui.mainScreen.MainDIModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [WeatherAPINetworkModule::class]
)
interface AppComponent {

    fun createMainActivityComponent(module: MainDIModule): MainDIComponent

    fun createCurrentDayFragmentComponent(module: CurrentDayDIModule): CurrentDayDIComponent

    fun createThreeDaysFragmentComponent(module: ThreeDaysDIModule): ThreeDaysDIComponent

    fun createSevenDaysFragmentComponent(module: SevenDaysDIModule): SevenDaysDIComponent

}
