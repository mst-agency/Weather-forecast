package ru.mole.weatherforecast.ui.detailScreen

import dagger.Subcomponent

@DetailHostDIScreen
@Subcomponent(modules = [DetailHostDIModule::class])

interface DetailHostDIComponent {
    fun inject(mainActivity: DetailHostActivity)
}