package ru.mole.weatherforecast.ui.detailScreen.currentDay

import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily

interface CurrentDayContract {

    interface View {
        fun onShowDetailForecast(dataSelectedCity: CurrentDayForecast)
    }

    interface Presenter {
        fun onStart(dataSelectedCity: CurrentDayForecast)
        fun detachView()
    }

}