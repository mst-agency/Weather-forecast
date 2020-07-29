package ru.mole.weatherforecast.ui.detailScreen.threeDays

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.mole.weatherforecast.domain.GetSeveralForecast
import ru.mole.weatherforecast.domain.model.Coordinate

class ThreeDaysPresenter(private var view: ThreeDaysContract.View?, private val getSeveralForecast: GetSeveralForecast) : ThreeDaysContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onStart(coord: Coordinate?) {
        coord?.let {
            val cityForecast = getSeveralForecast.execute(coord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.inShowDailyForecast(it.daily)
                }, { error ->

                })
            disposables.add(cityForecast)
        }
    }

    override fun detachView() {
        disposables.dispose()
        view = null
    }

}