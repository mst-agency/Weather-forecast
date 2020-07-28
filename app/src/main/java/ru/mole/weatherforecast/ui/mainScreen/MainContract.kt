package ru.mole.weatherforecast.ui.mainScreen

import ru.mole.weatherforecast.domain.model.CurrentDayForecast

interface MainContract {

    interface View {
        fun onShowListCityForecast(listCities: List<CurrentDayForecast>)
        fun onUpdateCityForecast(city: CurrentDayForecast)
    }

    interface Presenter {
        fun requestListCityForecast(cities: List<String>)
        fun requestCityForecast(city: String)
        fun detachView()
    }

}