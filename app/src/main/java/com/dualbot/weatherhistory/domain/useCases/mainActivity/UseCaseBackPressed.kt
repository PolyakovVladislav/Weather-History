package com.dualbot.weatherhistory.domain.useCases.mainActivity

import androidx.fragment.app.Fragment
import com.dualbot.weatherhistory.ui.Callback
import com.dualbot.weatherhistory.domain.utils.animations.AnimationTask
import com.dualbot.weatherhistory.domain.utils.animations.Animations
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import com.dualbot.weatherhistory.ui.mainFragment.MainFragment
import com.dualbot.weatherhistory.ui.storageFragment.StorageFragment
import java.util.*

class UseCaseBackPressed(private val callback :Callback) {

    fun execute(currentFragment: Fragment, toolbarAnimations: ToolbarAnimations) {
        if (currentFragment is StorageFragment) {
            callback.switchFragment(MainFragment(toolbarAnimations))
            val queue = LinkedList<AnimationTask>()
            queue.add(AnimationTask(Animations.RefreshRotate, 5))
            callback.runAnimations(queue)
        }
    }
}