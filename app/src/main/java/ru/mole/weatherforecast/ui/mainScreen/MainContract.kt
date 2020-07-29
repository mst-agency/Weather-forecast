package ru.mole.weatherforecast.ui.mainScreen

import ru.mole.weatherforecast.domain.model.CurrentDayForecast

interface MainContract {

    interface View {
        fun onShowListCityForecast(listCities: List<CurrentDayForecast>)
        fun onUpdateCityForecast(city: CurrentDayForecast)
        fun onShowError(message: String)
    }

    interface Presenter {
        fun refreshForecast(cities: List<String>, isConnected: Boolean)
        fun requestCityForecast(city: String)
        fun detachView()
    }

}