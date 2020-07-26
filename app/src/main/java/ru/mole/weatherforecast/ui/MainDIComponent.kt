package ru.mole.weatherforecast.ui

import dagger.Subcomponent

@MainDIScreen
@Subcomponent(modules = [MainDIModule::class])

interface MainDIComponent {
    fun inject(mainActivity: MainActivity)
}