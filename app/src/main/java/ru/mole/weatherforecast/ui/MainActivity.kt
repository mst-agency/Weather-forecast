package ru.mole.weatherforecast.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentForecast
import ru.mole.weatherforecast.ui.recycler.WeatherForecastAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    private var recyclerView: RecyclerView? = null
    private var adapter: WeatherForecastAdapter? = null
    private var cityList: List<CurrentForecast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getInstance().createMainActivityComponent(this).inject(this)

        initRecyclerView()
        presenter.requestListCityForecast(listOf("London", "Moscow"))

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rvListCityForecast)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onShowListCityForecast(listCities: List<CurrentForecast>) {
        recyclerView!!.adapter = WeatherForecastAdapter(listCities)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}