package ru.mole.weatherforecast.ui.detailScreen

interface DetailHostContract {

    interface View {
        fun onShowDetailCurrent()
        fun onShowForecastThreeDay()
        fun onShowForecastSevenDay()
        fun onShowMainScreen()
    }

    interface Presenter {
        fun attachView(view: DetailHostContract.View)
        fun onClickCurrentForecast()
        fun onClickThreeDaysForecast()
        fun onClickSevenDaysForecast()
        fun onBackPressed()
        fun detachView()
    }

}