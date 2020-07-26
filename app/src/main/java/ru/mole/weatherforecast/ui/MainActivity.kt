package ru.mole.weatherforecast.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getInstance().createMainActivityComponent(this).inject(this)

        presenter.requestListCityForecast()

    }

    override fun onShowListCityForecast() {

    }
}