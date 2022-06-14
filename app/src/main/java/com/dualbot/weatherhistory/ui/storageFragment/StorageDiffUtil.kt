package com.dualbot.weatherhistory.ui.storageFragment

import androidx.recyclerview.widget.DiffUtil
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData

class StorageDiffUtil(
    private val newList: List<ExpandableTreeWeatherData>,
    private val oldList: List<ExpandableTreeWeatherData>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
            return if (oldItem.javaClass == newItem.javaClass) {
                if (oldItem is ExpandableTreeWeatherData.Child) {
                    newItem as ExpandableTreeWeatherData.Child
                    oldItem.data.date.equals(newItem.data.date)
                } else {
                    oldItem as ExpandableTreeWeatherData.Parent
                    newItem as ExpandableTreeWeatherData.Parent
                    oldItem.title == newItem.title
                }
            } else
                false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]
        return oldItem == newItem
    }
}