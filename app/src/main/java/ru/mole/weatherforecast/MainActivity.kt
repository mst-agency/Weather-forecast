package ru.mole.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mole.weatherforecast.data.network.WeatherAPI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {

        }

    }
}