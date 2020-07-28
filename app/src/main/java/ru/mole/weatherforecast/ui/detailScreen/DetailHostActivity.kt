package ru.mole.weatherforecast.ui.detailScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_detail_host.*
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentForecast

class DetailHostActivity : AppCompatActivity(), DetailHostContract.View {

    private var presenter: DetailHostContract.Presenter? = null
    private var navController: NavController? = null
    private var dataSelectedCity: CurrentForecast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_host)

        if (intent.hasExtra(DATA_CITY_FORECAST)) {
            dataSelectedCity = intent.getParcelableExtra(DATA_CITY_FORECAST)
        }

        navController = Navigation.findNavController(this, R.id.mainHostFragment)
        presenter = DetailHostPresenter(this)

        Toast.makeText(this, dataSelectedCity!!.name, Toast.LENGTH_LONG).show()
    }

    private fun initNavigation() {
        bnvNavigationMenu.selectedItemId = R.id.actionCurrentForecast
        bnvNavigationMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionCurrentForecast -> {
//                    presenter.receptionGoods()
                    true
                }
                R.id.actionThreeDaysForecast -> {
//                    presenter.changeBalance()
                    true
                }
                R.id.actionSevenDaysForecast -> {
//                    presenter.menu()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onShowDetailCurrent() {
        TODO("Not yet implemented")
    }

    override fun onShowForecastThreeDay() {
        TODO("Not yet implemented")
    }

    override fun onShowForecastSevenDay() {
        TODO("Not yet implemented")
    }

    companion object {
        const val DATA_CITY_FORECAST = "data_city_forecast"
    }
}