package ru.mole.weatherforecast.ui.detailScreen.threeDays

import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily

interface ThreeDaysContract {

    interface View {
        fun inShowDailyForecast(data: List<Daily>)
    }

    interface Presenter {
        fun onStart(coord: Coordinate)
        fun detachView()
    }

}