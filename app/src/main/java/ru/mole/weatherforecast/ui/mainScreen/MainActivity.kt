package ru.mole.weatherforecast.ui.mainScreen

import android.R.attr
import android.app.Activity
import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mole.weatherforecast.App
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.domain.model.CurrentForecast
import ru.mole.weatherforecast.ui.addCityScreen.AddCityActivity
import ru.mole.weatherforecast.ui.mainScreen.recycler.WeatherForecastAdapter
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var recyclerView: RecyclerView
    private var adapter: WeatherForecastAdapter? = null
    private var cityList: ArrayList<CurrentForecast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getInstance().createMainActivityComponent(this).inject(this)

        initToolbar()
        initRecyclerView()

        presenter.requestListCityForecast(listOf("London", "Moscow"))

    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.inflateMenu(R.menu.menu_add_city)

        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.addCity) {
                startActivityForResult(Intent(this, AddCityActivity::class.java), REQUEST_CODE)
                true
            } else {
                false
            }
        }
    }

    private fun initRecyclerView() {
        adapter = WeatherForecastAdapter()
        recyclerView = findViewById(R.id.rvListCityForecast)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onShowListCityForecast(listCities: List<CurrentForecast>) {
        adapter?.dataSet(listCities)
    }

    override fun onUpdateCityForecast(city: CurrentForecast) {
        adapter?.updateData(city)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                presenter.requestCityForecast(it.getStringExtra(NAME_CITY))
            }
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    companion object {
        const val REQUEST_CODE = 100
        const val NAME_CITY = "name_city"
    }
}