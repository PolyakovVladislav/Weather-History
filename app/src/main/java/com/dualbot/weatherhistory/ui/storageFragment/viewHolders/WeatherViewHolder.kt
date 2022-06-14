package com.dualbot.weatherhistory.ui.storageFragment.viewHolders

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.dualbot.weatherhistory.R
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.ui.storageFragment.DrawableMapper

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var dropImageView: ImageView = itemView.findViewById(R.id.imageView_drop)
    var dateTextView: TextView = itemView.findViewById(R.id.textView_date)
    var weatherCharacterImageView: ImageView = itemView.findViewById(R.id.imageView_weatherCharacter)
    var temperatureTextView: TextView = itemView.findViewById(R.id.textView_temperature)
    var comfortTemperatureTextView: TextView = itemView.findViewById(R.id.textView_comfortTemperature)

    lateinit var weatherCharacterTextView: TextView
    lateinit var windSpeedTextView: TextView
    lateinit var windDirectionTextView: TextView
    lateinit var pressureCountTextView: TextView
    lateinit var wetnessCountTextView: TextView
    lateinit var gmActivityTextView: TextView
    lateinit var waterTemperatureTextView: TextView
    lateinit var sunSummaryTextView: TextView
    lateinit var sunRiseTextView: TextView
    lateinit var sunFallTextView: TextView
    lateinit var moonImageView: ImageView
    lateinit var moonSummaryTextView: TextView
    lateinit var moonRiseTextView: TextView
    lateinit var moonFallTextView: TextView

    fun expandView(data: ExpandableTreeWeatherData.Child, weather: Weather) {
        val rootView = itemView.findViewById<FrameLayout>(R.id.recycler_view_rootscene)
        val scene = Scene.getSceneForLayout(rootView as ViewGroup, R.layout.recycler_view_expanded_scene, itemView.context)
        val transition = TransitionInflater.from(itemView.context).inflateTransition(R.transition.recycler_view_child_expand_transition)

        transition.addListener(object: Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                dateTextView = itemView.findViewById(R.id.textView_date)
                dateTextView.text = weather.date
                dropImageView = itemView.findViewById(R.id.imageView_drop)
                setDropImageViewOnClickListener(data, weather)

                weatherCharacterImageView = itemView.findViewById(R.id.imageView_weatherCharacter)
                temperatureTextView = itemView.findViewById(R.id.textView_temperature)
                comfortTemperatureTextView = itemView.findViewById(R.id.textView_comfortTemperature)
                weatherCharacterImageView.setImageDrawable(AppCompatResources.getDrawable(itemView.context, DrawableMapper.map(weather.weatherCharacterSvgCode)))
                temperatureTextView.text = weather.temperature
                comfortTemperatureTextView.text = weather.comfortTemperature

                weatherCharacterTextView = itemView.findViewById(R.id.textView_weatherCharacter)
                windSpeedTextView = itemView.findViewById(R.id.textView_wind_speed)
                windDirectionTextView = itemView.findViewById(R.id.textView_windDirection)
                pressureCountTextView = itemView.findViewById(R.id.textView_pressure_count)
                wetnessCountTextView = itemView.findViewById(R.id.textView_wetness_count)
                gmActivityTextView = itemView.findViewById(R.id.textView_gm_activity)
                waterTemperatureTextView = itemView.findViewById(R.id.textView_water_temperature)
                sunSummaryTextView = itemView.findViewById(R.id.textView_sunSummary)
                sunRiseTextView = itemView.findViewById(R.id.textView_sunRise)
                sunFallTextView = itemView.findViewById(R.id.textView_sunFall)
                moonImageView = itemView.findViewById(R.id.imageView_moon)
                moonSummaryTextView = itemView.findViewById(R.id.textView_moonSummary)
                moonRiseTextView = itemView.findViewById(R.id.textView_moonRise)
                moonFallTextView = itemView.findViewById(R.id.textView_moonFall)
                weatherCharacterTextView.text = weather.weatherCharacter
                windSpeedTextView.text = weather.windSpeed
                windDirectionTextView.text = weather.windDirection
                pressureCountTextView.text = weather.pressure
                wetnessCountTextView.text = weather.wetness
                gmActivityTextView.text = weather.gmActivity
                waterTemperatureTextView.text = weather.waterTemperature
                sunSummaryTextView.text = weather.sunSummery
                sunRiseTextView.text = weather.sunRise
                sunFallTextView.text = weather.sunFall
                moonImageView.setImageDrawable(AppCompatResources.getDrawable(itemView.context, DrawableMapper.map(weather.moonSvgCode)))
                moonSummaryTextView.text = weather.moonSummery
                moonRiseTextView.text = weather.moonRise
                moonFallTextView.text = weather.moonFall
            }


            override fun onTransitionEnd(transition: Transition) {
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {
            }

        })
        TransitionManager.go(scene, transition)
    }


    fun collapseView(data: ExpandableTreeWeatherData.Child, weather: Weather) {
        val rootView = itemView.findViewById<FrameLayout>(R.id.recycler_view_rootscene)
        val scene = Scene.getSceneForLayout(rootView as ViewGroup, R.layout.recycler_view_collapsed_scene, itemView.context)
        val transition = TransitionInflater.from(itemView.context).inflateTransition(R.transition.recycler_view_child_collapse_transition)
        transition.addListener(object: Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                dropImageView = itemView.findViewById(R.id.imageView_drop)
                dateTextView = itemView.findViewById(R.id.textView_date)
                weatherCharacterImageView = itemView.findViewById(R.id.imageView_weatherCharacter)
                temperatureTextView = itemView.findViewById(R.id.textView_temperature)
                comfortTemperatureTextView = itemView.findViewById(R.id.textView_comfortTemperature)
                dateTextView.text = weather.date
                weatherCharacterImageView.setImageDrawable(AppCompatResources.getDrawable(itemView.context, DrawableMapper.map(weather.weatherCharacterSvgCode)))
                temperatureTextView.text = weather.temperature
                comfortTemperatureTextView.text = weather.comfortTemperature
                setDropImageViewOnClickListener(data, weather)
            }

            override fun onTransitionEnd(transition: Transition) {
            }

            override fun onTransitionCancel(transition: Transition) {
            }

            override fun onTransitionPause(transition: Transition) {
            }

            override fun onTransitionResume(transition: Transition) {
            }
        })
        TransitionManager.go(scene, transition)
    }

    fun setDropImageViewOnClickListener(data: ExpandableTreeWeatherData.Child, weather: Weather) {
        dropImageView.setOnClickListener {
            if (data.isExpanded) {
                data.isExpanded = false
                collapseView(data, weather)
            } else {
                data.isExpanded = true
                expandView(data, weather)
            }
        }
    }
}