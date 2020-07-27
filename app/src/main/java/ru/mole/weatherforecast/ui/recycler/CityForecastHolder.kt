package ru.mole.weatherforecast.ui.recycler

import android.os.Build
import android.text.Html
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city_forecast.view.*
import ru.mole.weatherforecast.domain.model.CurrentForecast

class CityForecastHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: CurrentForecast, position: Int) {
        view.cityName.text = item.name
        /*view.historyBody.text = model.body

        itemView.historyLevel.text = model.level
        when (model.level) {
            "Запущена" -> itemView.historyItem.setBackgroundColor(itemView.resources.getColor(R.color.transparent))
            "Завершена успешно" -> itemView.historyItem.setBackgroundColor(itemView.resources.getColor(R.color.lightGreen))
            "Завершена с ошибкой" -> itemView.historyItem.setBackgroundColor(itemView.resources.getColor(R.color.lightRed))
        }

        itemView.imageView_arrow.visibility = View.GONE
        if (model.additionalInfo != "") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                itemView.historyFullInfo.text = Html.fromHtml(model.additionalInfo, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                itemView.historyFullInfo.text = Html.fromHtml(model.additionalInfo)
            }

            itemView.imageView_arrow.visibility = View.VISIBLE
            if (model.isChecked) {
                itemView.hidePart.visibility = View.VISIBLE
                itemView.imageView_arrow.setBackgroundResource(R.drawable.ic_arrow_drop_up)
            } else {
                itemView.hidePart.visibility = View.GONE
                itemView.imageView_arrow.setBackgroundResource(R.drawable.ic_arrow_drop_down)
            }
        } else {
            itemView.hidePart.visibility = View.GONE
            itemView.imageView_arrow.visibility = View.GONE
        }*/
    }

}