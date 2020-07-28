package ru.mole.weatherforecast.ui.detailScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_detail_host.*
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.ui.mainScreen.MainActivity

class DetailHostActivity : AppCompatActivity(), DetailHostContract.View {

    private var presenter: DetailHostContract.Presenter? = null
    private var navController: NavController? = null
    private var dataSelectedCity: CurrentDayForecast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_host)

        if (intent.hasExtra(DATA_CITY_FORECAST)) {
            dataSelectedCity = intent.getParcelableExtra(DATA_CITY_FORECAST)
        }

        presenter = DetailHostPresenter()
        presenter?.attachView(this)
        initNavigation()

        toolbar.setNavigationOnClickListener {
            presenter?.onBackPressed()
        }
    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this, R.id.mainHostFragment)
        bnvNavigationMenu.selectedItemId = R.id.actionCurrentForecast
        bnvNavigationMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionCurrentForecast -> {
                    presenter?.onClickCurrentForecast()
                    true
                }
                R.id.actionThreeDaysForecast -> {
                    presenter?.onClickThreeDaysForecast()
                    true
                }
                R.id.actionSevenDaysForecast -> {
                    presenter?.onClickSevenDaysForecast()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onShowDetailCurrent() {
        bnvNavigationMenu.menu.getItem(0).isChecked = true
        navController?.navigate(R.id.currentDayFragment)
    }

    override fun onShowForecastThreeDay() {
        bnvNavigationMenu.menu.getItem(0).isChecked = true
        navController?.navigate(R.id.threeDaysFragment)
    }

    override fun onShowForecastSevenDay() {
        bnvNavigationMenu.menu.getItem(0).isChecked = true
        navController?.navigate(R.id.sevenDaysFragment)
    }

    override fun onShowMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val DATA_CITY_FORECAST = "data_city_forecast"
    }
}