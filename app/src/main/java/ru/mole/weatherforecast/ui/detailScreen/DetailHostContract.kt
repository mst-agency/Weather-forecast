package ru.mole.weatherforecast.ui.detailScreen

interface DetailHostContract {

    interface View {
        fun onShowDetailCurrent()
        fun onShowForecastThreeDay()
        fun onShowForecastSevenDay()
    }

    interface Presenter {
        fun start()
        fun detachView()
    }

}