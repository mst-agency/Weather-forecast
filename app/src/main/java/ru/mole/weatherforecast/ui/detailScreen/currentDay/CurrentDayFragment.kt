package ru.mole.weatherforecast.ui.detailScreen.currentDay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_current_day.view.*
import kotlinx.android.synthetic.main.fragment_current_day.view.tempMaxMin
import kotlinx.android.synthetic.main.fragment_current_day.view.windSpeed
import kotlinx.android.synthetic.main.item_city_forecast.view.*
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily
import ru.mole.weatherforecast.ui.detailScreen.DetailHostActivity
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysContract
import ru.mole.weatherforecast.ui.detailScreen.threeDays.ThreeDaysPresenter
import javax.inject.Inject

class CurrentDayFragment : Fragment(), CurrentDayContract.View {

    @Inject
    lateinit var presenter: CurrentDayPresenter

    private lateinit var date: TextView
    private lateinit var tempMaxMin: TextView
    private lateinit var tempFeeling: TextView
    private lateinit var windSpeed: TextView

    private var dataSelectedCity: CurrentDayForecast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getInstance().createCurrentDayFragmentComponent(this).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_current_day, container, false)
        date = view.date
        tempMaxMin = view.tempMaxMin
        tempFeeling = view.feelingTemp
        windSpeed = view.windSpeed
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSelectedCity = requireArguments().getParcelable(DetailHostActivity.DATA_CITY_FORECAST)

        dataSelectedCity?.let {
            presenter.onStart(it)
        }
    }

    override fun onShowDetailForecast(dataSelectedCity: CurrentDayForecast) {
        date.text = unixTimeToDate(dataSelectedCity.dt)
        tempMaxMin.text = view?.resources?.getString(R.string.temp_range, kelvinToCelsius(dataSelectedCity.main.temp_max),
            kelvinToCelsius(dataSelectedCity.main.temp_min))
        tempFeeling.text = view?.resources?.getString(R.string.temp_feeling, kelvinToCelsius(dataSelectedCity.main.feels_like))
        windSpeed.text = view?.resources?.getString(R.string.wind_speed, dataSelectedCity.wind.speed)
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return (kelvin - App.KELVIN)
    }

    private fun unixTimeToDate(time: Long) : String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd")
        val date = java.util.Date(time * 1000)
        return sdf.format(date)
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }
}