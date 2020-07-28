package ru.mole.weatherforecast.ui.detailScreen.sevenDays

import dagger.Subcomponent

@SevenDaysDIScreen
@Subcomponent(modules = [SevenDaysDIModule::class])

interface SevenDaysDIComponent {
    fun inject(sevenDaysFragment: SevenDaysFragment)
}