package ru.mole.weatherforecast.ui.detailScreen.currentDay

import dagger.Subcomponent

@CurrentDayDIScreen
@Subcomponent(modules = [CurrentDayDIModule::class])

interface CurrentDayDIComponent {
    fun inject(sevenDaysFragment: CurrentDayFragment)
}