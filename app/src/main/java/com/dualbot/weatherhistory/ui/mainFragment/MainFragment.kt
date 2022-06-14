package com.dualbot.weatherhistory.ui.mainFragment

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dualbot.weatherhistory.R
import com.dualbot.weatherhistory.domain.utils.Status
import com.dualbot.weatherhistory.ui.ViewModelFactory
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import com.dualbot.weatherhistory.ui.storageFragment.DrawableMapper

class MainFragment(private val toolbarAnimations: ToolbarAnimations): Fragment() {

    private lateinit var viewModel: MainFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("MainFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(context)
        println("MainFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("MainFragment onCreateView")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        println("MainFragment onCreateOptionsMenu")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("MainFragment onOptionsItemSelected")
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("MainFragment onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver(view)
        viewModel.loadWeather()
    }

    override fun onStart() {
        println("MainFragment onStart")
        super.onStart()
    }

    override fun onResume() {
        println("MainFragment onResume")
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        println("MainFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        println("MainFragment OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainFragment onDestroy")
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        println("MainFragment onDestroyOptionsMenu")
    }

    fun loadWeather() {
        viewModel.loadWeather()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.MainFragmentViewModelFactory())[MainFragmentViewModel::class.java]
    }

    private fun setupObserver(view: View) {
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val weather = it.data!!

                    val dateTextView: TextView = view.findViewById(R.id.textView_date)
                    val weatherCharacterImageView: ImageView = view.findViewById(R.id.imageView_weatherCharacter)
                    val temperatureTextView: TextView = view.findViewById(R.id.textView_temperature)
                    val comfortTemperatureTextView: TextView = view.findViewById(R.id.textView_comfortTemperature)
                    val weatherCharacterTextView: TextView = view.findViewById(R.id.textView_weatherCharacter)
                    val windSpeedTextView: TextView = view.findViewById(R.id.textView_wind_speed)
                    val windDirectionTextView: TextView = view.findViewById(R.id.textView_windDirection)
                    val pressureCountTextView: TextView = view.findViewById(R.id.textView_pressure_count)
                    val wetnessCountTextView: TextView = view.findViewById(R.id.textView_wetness_count)
                    val gmActivityTextView: TextView = view.findViewById(R.id.textView_gm_activity)
                    val waterTemperatureTextView: TextView = view.findViewById(R.id.textView_water_temperature)
                    val sunSummaryTextView: TextView = view.findViewById(R.id.textView_sunSummary)
                    val sunRiseTextView: TextView = view.findViewById(R.id.textView_sunRise)
                    val sunFallTextView: TextView = view.findViewById(R.id.textView_sunFall)
                    val moonImageView: ImageView = view.findViewById(R.id.imageView_moon)
                    val moonSummaryTextView: TextView = view.findViewById(R.id.textView_moonSummary)
                    val moonRiseTextView: TextView = view.findViewById(R.id.textView_moonRise)
                    val moonFallTextView: TextView = view.findViewById(R.id.textView_moonFall)

                    dateTextView.text = weather.date
                    weatherCharacterImageView.setImageDrawable(AppCompatResources.getDrawable(requireContext(), DrawableMapper.map(weather.weatherCharacterSvgCode)))
                    temperatureTextView.text = weather.temperature
                    comfortTemperatureTextView.text = weather.comfortTemperature
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
                    moonImageView.setImageDrawable(AppCompatResources.getDrawable(view.context, DrawableMapper.map(weather.moonSvgCode)))
                    moonSummaryTextView.text = weather.moonSummery
                    moonRiseTextView.text = weather.moonRise
                    moonFallTextView.text = weather.moonFall
                    toolbarAnimations.stopAnimations()
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}