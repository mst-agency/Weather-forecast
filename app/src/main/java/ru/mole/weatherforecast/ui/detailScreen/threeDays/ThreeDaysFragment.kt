package ru.mole.weatherforecast.ui.detailScreen.threeDays

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
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.SevenDaysPresenter
import ru.mole.weatherforecast.ui.detailScreen.sevenDays.recycler.SeveralForecastAdapter
import javax.inject.Inject

class ThreeDaysFragment : Fragment(), ThreeDaysContract.View  {

    @Inject
    lateinit var presenter: ThreeDaysPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SeveralForecastAdapter
    private var dataSelectedCity: CurrentDayForecast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getInstance().createThreeDaysFragmentComponent(this).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seven_days, container, false)
        initRecyclerView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSelectedCity = requireArguments().getParcelable(DetailHostActivity.DATA_CITY_FORECAST)

        dataSelectedCity?.let {
            presenter.onStart(it.coord)
        }
    }

    private fun initRecyclerView(view: View) {
        adapter = SeveralForecastAdapter()
        recyclerView = view.rvListSevenForecast
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun inShowDailyForecast(data: List<Daily>) {
        adapter.dataSet(data, 3)
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }

}