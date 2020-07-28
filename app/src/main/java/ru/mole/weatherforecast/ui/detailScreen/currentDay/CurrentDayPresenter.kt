package ru.mole.weatherforecast.ui.detailScreen.currentDay

import ru.mole.weatherforecast.domain.model.CurrentDayForecast

class CurrentDayPresenter(private var view: CurrentDayContract.View?) : CurrentDayContract.Presenter {

    override fun onStart(dataSelectedCity: CurrentDayForecast) {
        view?.onShowDetailForecast(dataSelectedCity)
    }

    override fun detachView() {
        view = null
    }

}