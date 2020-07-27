package ru.mole.weatherforecast.ui

import ru.mole.weatherforecast.domain.model.CurrentForecast

interface MainContract {

    interface View {
        fun onShowListCityForecast(listCities: List<CurrentForecast>)
    }

    interface Presenter {
        fun requestListCityForecast(cities: List<String>)
        fun detachView()
    }

}