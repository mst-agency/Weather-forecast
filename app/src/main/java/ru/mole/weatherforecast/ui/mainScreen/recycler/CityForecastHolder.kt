package ru.mole.weatherforecast.ui.mainScreen.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city_forecast.view.*
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast

class CityForecastHolder(private val view: View, private var callback: WeatherForecastAdapter.OnForecastCityInteraction?) : RecyclerView.ViewHolder(view) {

    fun bind(item: CurrentDayForecast) {
        view.cityName.text = item.name
        view.tempMaxMin.text = view.resources.getString(R.string.temp_range, kelvinToCelsius(item.main.temp_max),
            kelvinToCelsius(item.main.temp_min))
        view.windSpeed.text = view.resources.getString(R.string.wind_speed, item.wind.speed)

        view.itemForecastCity.setOnClickListener {
            callback?.onClickCity(item)
        }

    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return (kelvin - 273.15)
    }

}