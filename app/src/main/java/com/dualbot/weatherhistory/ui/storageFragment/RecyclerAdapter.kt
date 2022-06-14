package com.dualbot.weatherhistory.ui.storageFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.dualbot.weatherhistory.R
import com.dualbot.weatherhistory.domain.model.Constants
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.model.Weather
import com.dualbot.weatherhistory.ui.storageFragment.viewHolders.GroupViewHolder
import com.dualbot.weatherhistory.ui.storageFragment.viewHolders.WeatherViewHolder

class RecyclerAdapter(private val adapterCallback: AdapterCallback):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<ExpandableTreeWeatherData>()
    var list: List<ExpandableTreeWeatherData> = _list

    fun setData(data: List<ExpandableTreeWeatherData>) {
        this._list.clear()
        this._list.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.PARENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_group_item, parent, false)
            GroupViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_rootscene, parent, false)
            WeatherViewHolder(view)
        }
    }

    private fun expandOrCollapseGroup(holder: GroupViewHolder,
                                      position: Int,
                                      data: ExpandableTreeWeatherData.Parent) {
        val oldList: List<ExpandableTreeWeatherData> = ArrayList(_list)
        if (data.isExpanded) {
            collapseParentRow(data)
            holder.animateIconRotateDown()
        }
        else {
            expandParentRow(position, data)
            holder.animateIconRotateRight()
        }
        val newList = ArrayList(_list)
        adapterCallback.listChanged(newList = newList, oldList = oldList)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = this._list[position]
        if (data.type == Constants.PARENT) {
            holder as GroupViewHolder
            data as ExpandableTreeWeatherData.Parent
            holder.moveView(data.depth)
            holder.apply {
                holder.groupTitleTextView.text = data.title
                if (data.isExpanded)
                    holder.setIconArrowRight()
                else
                    holder.setIconArrowDropDown()
                holder.itemView.setOnClickListener {
                    expandOrCollapseGroup(holder, position, data)
                }
            }
        }
        else {
            holder as WeatherViewHolder
            data as ExpandableTreeWeatherData.Child
            val weather = data.data
            val context = holder.itemView.context
            holder.apply {
                setDropImageViewOnClickListener(data, weather)
                dateTextView.text = weather.date
                weatherCharacterImageView.setImageDrawable(AppCompatResources.getDrawable(context, DrawableMapper.map(weather.weatherCharacterSvgCode)))
                temperatureTextView.text = weather.temperature
                comfortTemperatureTextView.text = weather.comfortTemperature

            }
        }
    }

    private fun collapseParentRow(parent: ExpandableTreeWeatherData.Parent) {
        parent.subData.forEach {el ->
            if (el is ExpandableTreeWeatherData.Child)
                _list.remove(el)
            else if (el is ExpandableTreeWeatherData.Parent) {
                collapseParentRow(el)
                _list.remove(el)
            }
        }
        parent.isExpanded = false
    }

    private fun expandParentRow(position: Int, parent: ExpandableTreeWeatherData.Parent) {
        var nextPosition = position + 1
        val subList = parent.subData
        subList.forEach{
            it as ExpandableTreeWeatherData
            _list.add(nextPosition, it)
            nextPosition++
        }
        parent.isExpanded = true
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun getItemViewType(position: Int) = _list[position].type
}