package ru.mole.weatherforecast.ui.detailScreen.sevenDays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_seven_days.view.*
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentDayForecast
import ru.mole.weatherforecast.domain.model.Daily
import ru.mole.weatherforecast.ui.detailScreen.DetailHostActivity
import ru.mole.weatherforecast.ui.detailScreen.DetailHostActivity.Companion.DATA_CITY_FORECAST
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.recycler.SeveralForecastAdapter
import javax.inject.Inject

class SevenDaysFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SeveralForecastAdapter
    private var dataSelectedCity: List<Daily>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seven_days, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataSelectedCity = requireArguments().getParcelableArrayList(DetailHostActivity.DATA_DETAIL_FORECAST)

        dataSelectedCity?.let {
            inShowDailyForecast(it)
        }
    }

    private fun initRecyclerView(view: View) {
        adapter = SeveralForecastAdapter()
        recyclerView = view.rvListSevenForecast
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun inShowDailyForecast(data: List<Daily>) {
        adapter.dataSet(data, 7)
    }

}