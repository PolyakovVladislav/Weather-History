package com.dualbot.weatherhistory.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dualbot.weatherhistory.domain.useCases.mainActivity.UseCaseActionRefreshPressed
import com.dualbot.weatherhistory.domain.useCases.mainActivity.UseCaseActionStoragePressed
import com.dualbot.weatherhistory.domain.useCases.mainActivity.UseCaseBackPressed
import com.dualbot.weatherhistory.domain.utils.animations.AnimationTask
import com.dualbot.weatherhistory.domain.utils.animations.Animations
import com.dualbot.weatherhistory.domain.utils.animations.ToolbarAnimations
import java.util.*

class MainActivityViewModel(context: Context,
                            private val toolbarAnimations: ToolbarAnimations
) : ViewModel(), Callback, ToolbarAnimations.Callback {

    private val useCaseActionRefreshPressed: UseCaseActionRefreshPressed =
        UseCaseActionRefreshPressed(this)
    private val useCaseActionStoragePressed = UseCaseActionStoragePressed(this)
    private val useCaseOnBackPressed = UseCaseBackPressed(this)

    init {
        toolbarAnimations.seNewDrawableListener(this)
    }

    private val _refreshIcon = MutableLiveData<Drawable>()
    val refreshIcon: LiveData<Drawable> = _refreshIcon

    private val _fragment = MutableLiveData<Fragment>()
    val fragment: LiveData<Fragment> = _fragment

    fun actionRefreshPressed(currentFragment: Fragment) {
        useCaseActionRefreshPressed.execute(currentFragment, toolbarAnimations)
    }

    fun actionStoragePressed(currentFragment: Fragment) {
        useCaseActionStoragePressed.execute(currentFragment)
    }

    fun backPressed(currentFragment: Fragment) {
        useCaseOnBackPressed.execute(currentFragment, toolbarAnimations)
    }

    fun rotateActionRefresh() {
        val queue = LinkedList<AnimationTask>()
        queue.add(AnimationTask(Animations.RefreshRotate, 5))
        toolbarAnimations.runAnimations(queue)
    }

    override fun runAnimations(stack: Queue<AnimationTask>) {
        toolbarAnimations.runAnimations(stack)
    }

    override fun switchFragment(fragment: Fragment) {
        _fragment.value = fragment
    }

    override fun onNewAnimation(newAnimation: Drawable) {
        _refreshIcon.value = newAnimation
    }
}

interface Callback {
    fun runAnimations(stack: Queue<AnimationTask>)
    fun switchFragment(fragment: Fragment)
}