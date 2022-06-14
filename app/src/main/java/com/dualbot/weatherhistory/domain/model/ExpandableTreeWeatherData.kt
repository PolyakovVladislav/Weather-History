package com.dualbot.weatherhistory.domain.model

sealed class ExpandableTreeWeatherData(var type: Int) {

    abstract val depth: Int
    abstract var isExpanded: Boolean

    class Parent(
        var title: String,
        val subData: MutableList<in ExpandableTreeWeatherData>,
        override var isExpanded: Boolean = false,
        override val depth: Int
    ): ExpandableTreeWeatherData(Constants.PARENT)

    data class Child(
        val data: Weather,
        override var isExpanded: Boolean = false,
        override val depth: Int
    ): ExpandableTreeWeatherData(Constants.CHILD)
}
