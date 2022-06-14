package com.dualbot.weatherhistory.domain.useCases.mainActivity

import androidx.fragment.app.Fragment
import com.dualbot.weatherhistory.ui.Callback
import com.dualbot.weatherhistory.domain.utils.animations.AnimationTask
import com.dualbot.weatherhistory.domain.utils.animations.Animations
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import com.dualbot.weatherhistory.ui.mainFragment.MainFragment
import com.dualbot.weatherhistory.ui.storageFragment.StorageFragment
import java.util.*

class UseCaseActionRefreshPressed(private val callBack: Callback) {

    fun execute(currentFragment: Fragment, toolbarAnimations: ToolbarAnimations) {
        if (currentFragment is MainFragment) {
            val queue = LinkedList<AnimationTask>()
            queue.add(AnimationTask(Animations.RefreshRotate, 5))
            callBack.runAnimations(queue)
            currentFragment.loadWeather()
        }
        else if (currentFragment is StorageFragment) {
            val queue = LinkedList<AnimationTask>()
            queue.add(AnimationTask(Animations.StorageToRotateRefreshRotate, 1))
            queue.add(AnimationTask(Animations.RefreshRotate, 4))
            callBack.runAnimations(queue)
            callBack.switchFragment(MainFragment(toolbarAnimations))
        }
    }
}