package ru.mole.weatherforecast.ui

interface MainContract {

    interface View {
        fun onShowListCityForecast()
    }

    interface Presenter {
        fun requestListCityForecast()
    }

}