package ru.mole.weatherforecast.ui.detailScreen

class DetailHostPresenter() : DetailHostContract.Presenter {

    private var view: DetailHostContract.View? = null

    override fun attachView(view: DetailHostContract.View) {
        this.view = view
    }

    override fun onClickCurrentForecast() {
        view?.onShowDetailCurrent()
    }

    override fun onClickThreeDaysForecast() {
        view?.onShowForecastThreeDay()
    }

    override fun onClickSevenDaysForecast() {
        view?.onShowForecastSevenDay()
    }

    override fun onBackPressed() {
        view?.onShowMainScreen()
    }


    override fun detachView() {
        view = null
    }
}