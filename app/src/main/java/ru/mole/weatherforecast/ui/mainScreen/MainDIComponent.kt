package ru.mole.weatherforecast.ui.mainScreen

import dagger.Subcomponent

@MainDIScreen
@Subcomponent(modules = [MainDIModule::class])

interface MainDIComponent {
    fun inject(mainActivity: MainActivity)
}