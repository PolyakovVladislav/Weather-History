package com.dualbot.weatherhistory.repository

import com.dualbot.weatherhistory.domain.Repository
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.domain.utils.Resource
import kotlin.random.Random

class RepositoryImplementation : Repository {

    val list = mutableListOf<ExpandableTreeWeatherData>()
    val years = arrayOf("2021", "2022")
    val months = arrayOf("август", "сентябрь")
    val days = arrayOf("1", "2", "3")
    val weatherCharacterSvgCodes = arrayOf("c4_r3", "sun", "d_c3_r3_st")
    val weatherCharacters = arrayOf("дождь", "солнечно", "солнце, гроза и дождь")
    val temperatures = arrayOf("15", "25", "18")
    val comfortTemperatures = arrayOf("14", "28", "16")
    val windsSpeed = arrayOf("2", "0", "10")
    val windsDirection = arrayOf("южный", "северо-западный", "восточный")
    val pressures = arrayOf("750", "770", "740")
    val wetnesses = arrayOf("100%", "20%", "100%")
    val gmActivity = "1"
    val waterTemperature = "20"
    val sun = "some sun"
    val moonSvgCodes = arrayOf("moon_0", "moon_4", "moon_8")
    val moon = "some moon"

    override suspend fun getWeather(): Resource<Weather> {
        val gen1 = genInt(2)
        val weatherCharacterSvgCode = weatherCharacterSvgCodes[gen1]
        val weatherCharacter = weatherCharacters[gen1]
        val temperature = temperatures[gen1]
        val comfortTemperature = comfortTemperatures[gen1]
        val windSpeed = windsSpeed[gen1]
        val windDirection = windsDirection[gen1]
        val pressure = pressures[gen1]
        val wetness = wetnesses[gen1]
        val moonSvgCode = moonSvgCodes[genInt(2)]
        val weather = Weather(
            date = "2022.${genInt(12)}.${genInt(30)}",
            weatherCharacterSvgCode,
            weatherCharacter,
            temperature,
            comfortTemperature,
            windSpeed,
            windDirection,
            pressure,
            wetness,
            gmActivity,
            waterTemperature,
            sun,
            sun,
            sun,
            moonSvgCode,
            moon,
            moon,
            moon)
        Thread.sleep(5000)
        return Resource.success(weather)
    }

    override suspend fun loadWeatherFromDB(): Resource<List<ExpandableTreeWeatherData>> {
        return Resource.success(generateWeatherList())
    }

    private fun generateWeatherList(): List<ExpandableTreeWeatherData> {
        var yearParent: ExpandableTreeWeatherData.Parent

        for (y in 0..1) {
            val year = years[y]
            yearParent = ExpandableTreeWeatherData.Parent(year, mutableListOf(), depth = 0)
            for (m in 0..1) {
                val month = months[m]
                val monthParent = ExpandableTreeWeatherData.Parent(month, mutableListOf(), depth = 1)
                yearParent.subData.add(monthParent)
                for (d in 0..2) {
                    val day = days[d]
                    val dayParent = ExpandableTreeWeatherData.Parent(day, mutableListOf(), depth = 2)
                    monthParent.subData.add(dayParent)
                    for (i in 0..10) {
                        val gen1 = genInt(2)
                        val weatherCharacterSvgCode = weatherCharacterSvgCodes[gen1]
                        val weatherCharacter = weatherCharacters[gen1]
                        val temperature = temperatures[gen1]
                        val comfortTemperature = comfortTemperatures[gen1]
                        val windSpeed = windsSpeed[gen1]
                        val windDirection = windsDirection[gen1]
                        val pressure = pressures[gen1]
                        val wetness = wetnesses[gen1]
                        val moonSvgCode = moonSvgCodes[genInt(2)]
                        val weather = Weather(
                            date = "$year $month $day",
                            weatherCharacterSvgCode,
                            weatherCharacter,
                            temperature,
                            comfortTemperature,
                            windSpeed,
                            windDirection,
                            pressure,
                            wetness,
                            gmActivity,
                            waterTemperature,
                            sun,
                            sun,
                            sun,
                            moonSvgCode,
                            moon,
                            moon,
                            moon)
                        val weatherChild = ExpandableTreeWeatherData.Child(weather, depth = 3)
                        dayParent.subData.add(weatherChild)
                    }
                }
            }
            list.add(yearParent)
        }
        return list
    }


    private fun genInt(until: Int): Int {
        return Random.nextInt(until)
    }
}