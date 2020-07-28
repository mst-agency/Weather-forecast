package ru.mole.weatherforecast.ui.detailScreen.threeDays

import dagger.Subcomponent

@ThreeDaysDIScreen
@Subcomponent(modules = [ThreeDaysDIModule::class])

interface ThreeDaysDIComponent {
    fun inject(sevenDaysFragment: ThreeDaysFragment)
}