package ru.mole.weatherforecast.ui.mainScreen

import ru.mole.weatherforecast.domain.model.CurrentForecast

interface MainContract {

    interface View {
        fun onShowListCityForecast(listCities: List<CurrentForecast>)
        fun onUpdateCityForecast(city: CurrentForecast)
    }

    interface Presenter {
        fun requestListCityForecast(cities: List<String>)
        fun requestCityForecast(city: String)
        fun detachView()
    }

}