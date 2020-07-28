package ru.mole.weatherforecast.ui.mainScreen.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast

class WeatherForecastAdapter(private var callback: OnForecastCityInteraction?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnForecastCityInteraction {
        fun onClickCity(selectedCity: CurrentDayForecast)
    }

    private var cityList: ArrayList<CurrentDayForecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_forecast, parent, false)
        return CityForecastHolder(view, callback)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityForecastHolder).bind(cityList[position])
    }

    fun dataSet(data: List<CurrentDayForecast>) {
        cityList.clear()
        cityList.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(data: CurrentDayForecast) {
        cityList.add(data)
        notifyDataSetChanged()
    }
}