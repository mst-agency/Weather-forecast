package ru.mole.weatherforecast.ui.addCityScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ru.mole.weatherforecast.R
import ru.mole.weatherforecast.ui.mainScreen.MainActivity.Companion.NAME_CITY

class AddCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        val nameCity = findViewById<EditText>(R.id.etAddCity)
        val addButton = findViewById<Button>(R.id.btnAddCity)

        nameCity.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addButton.isEnabled = p3 != 0
            }
        })

        addButton.setOnClickListener {
                val name: String = nameCity.text.toString()
                setResult(Activity.RESULT_OK, Intent().putExtra(NAME_CITY, name))
                finish()
        }
    }

}