package ru.mole.weatherforecast.ui.detailScreen.sevenDays.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city_forecast.view.*
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily

class SevenForecastHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Daily) {
        view.cityName.text = unixTimeToDate(item.dt)
        view.tempMaxMin.text = view.resources.getString(R.string.temp_range, kelvinToCelsius(item.temp.max), kelvinToCelsius(item.temp.min))
        view.windSpeed.text = view.resources.getString(R.string.wind_speed, item.wind_speed)
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return (kelvin - 273.15)
    }

    private fun unixTimeToDate(time: Long) : String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd")
        val date = java.util.Date(time * 1000)
        return sdf.format(date)
    }

}