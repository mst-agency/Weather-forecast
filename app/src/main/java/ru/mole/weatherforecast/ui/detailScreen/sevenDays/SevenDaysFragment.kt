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
import ru.mole.weatherforecast.ui.detailScreen.DetailHostActivity.Companion.DATA_CITY_FORECAST
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.recycler.SevenForecastAdapter
import ru.mole.weatherforecast.ui.mainScreen.recycler.WeatherForecastAdapter
import javax.inject.Inject

class SevenDaysFragment : Fragment(), SevenDaysContract.View {

    @Inject
    lateinit var presenter: SevenDaysPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SevenForecastAdapter
    private var dataSelectedCity: CurrentDayForecast? = null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        App.getInstance().createSevenDaysFragmentComponent(this).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seven_days, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataSelectedCity = requireArguments().getParcelable(DATA_CITY_FORECAST)

        dataSelectedCity?.let {
            presenter.onStart(it.coord)
        }

    }

    private fun initRecyclerView(view: View) {
        adapter = SevenForecastAdapter()
        recyclerView = view.rvListSevenForecast
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SevenDaysFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun inShowDailyForecast(data: List<Daily>) {
        adapter?.dataSet(data)
    }
}