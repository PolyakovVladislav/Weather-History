package com.dualbot.weatherhistory.domain.useCases.mainActivity

import androidx.fragment.app.Fragment
import com.dualbot.weatherhistory.ui.Callback
import com.dualbot.weatherhistory.domain.utils.animations.AnimationTask
import com.dualbot.weatherhistory.domain.utils.animations.Animations
import com.dualbot.weatherhistory.ui.mainFragment.MainFragment
import com.dualbot.weatherhistory.ui.storageFragment.StorageFragment
import java.util.*

class UseCaseActionStoragePressed(private val callback: Callback) {

    fun execute(currentFragment: Fragment) {
        if (currentFragment is MainFragment) {
            val queue = LinkedList<AnimationTask>()
            queue.add(AnimationTask(Animations.RefreshToStorage, 1))
            callback.runAnimations(queue)
            callback.switchFragment(StorageFragment())
        }
    }
}