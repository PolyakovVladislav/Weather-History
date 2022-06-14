package com.dualbot.weatherhistory.ui.storageFragment.viewHolders

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.Constraints
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.dualbot.weatherhistory.R

class GroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val groupTitleTextView: TextView = itemView.findViewById<TextView>(R.id.storage_recycler_group_title)
    private val dropArrowImageView: ImageView = itemView.findViewById<ImageView>(R.id.drop_down_arrow)

    fun moveView(depthElevation: Int) {
        val layoutParams = Constraints.LayoutParams(0, WRAP_CONTENT)
        val density = Resources.getSystem().displayMetrics.density
        layoutParams.setMargins((8 * density).toInt())
        layoutParams.marginStart = (8 * density + 12 * density * depthElevation).toInt()
        layoutParams.topToTop = 0
        layoutParams.bottomToBottom = 0
        layoutParams.startToStart = 0
        layoutParams.endToStart = dropArrowImageView.id
        groupTitleTextView.layoutParams = layoutParams
//        when (depthElevation) {
//            0 -> itemView.setBackgroundColor(itemView.resources.getColor(R.color.depth_0, itemView.context.theme))
//            1 -> itemView.setBackgroundColor(itemView.resources.getColor(R.color.depth_1, itemView.context.theme))
//            2 -> itemView.setBackgroundColor(itemView.resources.getColor(R.color.depth_2, itemView.context.theme))
//        }
    }

    fun setIconArrowDropDown() {
        dropArrowImageView
            .setImageDrawable(
                AppCompatResources
                    .getDrawable(
                        itemView.context,
                        R.drawable.ic_baseline_arrow_drop_down_24
                    )
            )
    }

    fun setIconArrowRight() {
        dropArrowImageView
            .setImageDrawable(
                AppCompatResources
                    .getDrawable(
                        itemView.context,
                        R.drawable.ic_baseline_arrow_right_24
                    )
            )
    }

    fun animateIconRotateDown() {
        val animation = AnimatedVectorDrawableCompat.create(
            itemView.context,
            R.drawable.animation_drop_arrow_rotate_down)
        dropArrowImageView.setImageDrawable(animation)
        animation?.start()
    }

    fun animateIconRotateRight() {
        val animation = AnimatedVectorDrawableCompat.create(
            itemView.context,
            R.drawable.animation_drop_arrow_rotate_right)
        dropArrowImageView.setImageDrawable(animation)
        animation?.start()
    }
}