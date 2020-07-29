package ru.mole.weatherforecast.ui.detailScreen

import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.Daily

interface DetailHostContract {

    interface View {
        fun onShowDetailCurrent()
        fun onShowForecastThreeDay()
        fun onShowForecastSevenDay()
        fun onShowMainScreen()
        fun onSetData(data: List<Daily>)
        fun onShowError(message: String)
    }

    interface Presenter {
        fun refreshForecast(city: String, coord: Coordinate?, isConnected: Boolean)
        fun onClickCurrentForecast()
        fun onClickThreeDaysForecast()
        fun onClickSevenDaysForecast()
        fun onBackPressed()
        fun detachView()
    }

}