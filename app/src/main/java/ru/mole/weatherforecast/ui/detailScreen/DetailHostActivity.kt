package ru.mole.weatherforecast.ui.detailScreen

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_detail_host.*
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.broadcastReceiver.ConnectivityReceiver
import ru.mole.weatherforecast.domain.model.Coordinate
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily
import ru.mole.weatherforecast.ui.mainScreen.MainActivity
import java.util.ArrayList
import javax.inject.Inject

class DetailHostActivity : AppCompatActivity(), DetailHostContract.View, ConnectivityReceiver.ConnectivityReceiverListener {

    @Inject
    lateinit var presenter: DetailHostPresenter

    private var connectivityReceiver: ConnectivityReceiver? = null
    private var navController: NavController? = null
    private var dataSelectedCity: CurrentDayForecast? = null
    private var listDaily: List<Daily>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_host)

        App.getInstance().createDetailHostActivityComponent(this).inject(this)

        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        connectivityReceiver = ConnectivityReceiver()
        registerReceiver(connectivityReceiver, intentFilter)
        App.getInstance().setConnectivityListener(this)

        if (intent.hasExtra(DATA_CITY_FORECAST)) {
            dataSelectedCity = intent.getParcelableExtra(DATA_CITY_FORECAST)
        }

        initNavigation()

        toolbar.title = resources.getString(R.string.current_forecast)
        presenter.refreshForecast(
            dataSelectedCity?.name?:"", Coordinate(lat = dataSelectedCity?.coord?.lat?:1.0, lon = dataSelectedCity?.coord?.lon?:1.0), false)

        toolbar.setNavigationOnClickListener {
            presenter?.onBackPressed()
        }
    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this, R.id.mainHostFragment)
        onShowDetailCurrent()
        bnvNavigationMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionCurrentForecast -> {
                    toolbar.title = resources.getString(R.string.current_forecast)
                    presenter.onClickCurrentForecast()
                    true
                }
                R.id.actionThreeDaysForecast -> {
                    toolbar.title = resources.getString(R.string.three_forecast)
                    presenter.onClickThreeDaysForecast()
                    true
                }
                R.id.actionSevenDaysForecast -> {
                    toolbar.title = resources.getString(R.string.seven_forecast)
                    presenter.onClickSevenDaysForecast()
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
        val args = Bundle()
        args.putParcelable(DATA_CITY_FORECAST, dataSelectedCity)
        navController?.navigate(R.id.currentDayFragment, args)
    }

    override fun onShowForecastThreeDay() {
        bnvNavigationMenu.menu.getItem(0).isChecked = true
        val args = Bundle()
        args.putParcelableArrayList(DATA_DETAIL_FORECAST, listDaily as ArrayList<out Parcelable>)
        navController?.navigate(R.id.threeDaysFragment, args)
    }

    override fun onShowForecastSevenDay() {
        bnvNavigationMenu.menu.getItem(0).isChecked = true
        val args = Bundle()
        args.putParcelableArrayList(DATA_DETAIL_FORECAST, listDaily as ArrayList<out Parcelable>)
        navController?.navigate(R.id.sevenDaysFragment, args)
    }

    override fun onShowMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSetData(data: List<Daily>) {
        listDaily = data
        bnvNavigationMenu.menu.getItem(0).isEnabled = true
        bnvNavigationMenu.menu.getItem(1).isEnabled = true
        bnvNavigationMenu.menu.getItem(2).isEnabled = true
    }

    override fun onShowError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.detachView()
        unregisterReceiver(connectivityReceiver)
        App.getInstance().removeConnectivityListener()
        super.onDestroy()
    }

    companion object {
        const val DATA_CITY_FORECAST = "data_city_forecast"
        const val DATA_DETAIL_FORECAST = "data_detail_forecast"
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        presenter.refreshForecast(
            dataSelectedCity?.name?:"", Coordinate(lat = dataSelectedCity?.coord?.lat?:1.0, lon = dataSelectedCity?.coord?.lon?:1.0), isConnected)
    }
}