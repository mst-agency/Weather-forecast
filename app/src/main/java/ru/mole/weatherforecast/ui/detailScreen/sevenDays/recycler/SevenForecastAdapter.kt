package ru.mole.weatherforecast.ui.detailScreen.sevenDays.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily

class SevenForecastAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cityList: ArrayList<Daily> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_forecast, parent, false)
        return SevenForecastHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size - 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SevenForecastHolder).bind(cityList[position + 1])
    }

    fun dataSet(data: List<Daily>) {
        cityList.clear()
        cityList.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(data: Daily) {
        cityList.add(data)
        notifyDataSetChanged()
    }
}