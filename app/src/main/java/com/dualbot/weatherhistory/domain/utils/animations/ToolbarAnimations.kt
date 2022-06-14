package com.dualbot.weatherhistory.domain.utils.animations

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.dualbot.weatherhistory.R
import java.util.*

class ToolbarAnimations(private val context: Context
                        ): Animatable2Compat.AnimationCallback() {

    private var animationsQueue = LinkedList<AnimationTask>()
    private var currentAnimation: AnimatedVectorDrawableCompat? = null
    private lateinit var callback: Callback

    fun runAnimations(animationsStack: Queue<AnimationTask>) {
        stopAnimations()
        animationsQueue.addAll(animationsStack)
        currentAnimation?.unregisterAnimationCallback(this)
        runNextAnimation()
    }

    fun stopAnimations() {
        animationsQueue.clear()
        currentAnimation?.unregisterAnimationCallback(this)
    }

    fun seNewDrawableListener(callback: Callback) {
        this.callback = callback
    }

    private fun runNextAnimation() {
        val animationTask = animationsQueue.peek()
        if (animationTask != null) {
            if (animationTask.repeats > 0) {
                animationTask.repeats = animationTask.repeats - 1
                when (animationTask.animation) {
                    Animations.None -> {
                        callback.onNewAnimation(
                            ResourcesCompat.getDrawable(
                                context.resources,
                                R.drawable.ic_baseline_refresh_24,
                                context.theme)!!) }
                    Animations.RefreshRotate -> startAnimation(R.drawable.animation_refresh_rotate)
                    Animations.StorageToRotateRefreshRotate -> startAnimation(R.drawable.animation_storage_to_refresh_rotate)
                    Animations.RefreshToStorage -> startAnimation(R.drawable.animation_refresh_to_storage)
                }
                if (animationTask.repeats == 0)
                    animationsQueue.removeFirst()
            } else {
                animationsQueue.removeFirst()
                runNextAnimation()
            }
        }
        else
            currentAnimation?.unregisterAnimationCallback(this)
    }

    private fun startAnimation(resId: Int) {
        currentAnimation = AnimatedVectorDrawableCompat.create(context, resId)
        currentAnimation?.registerAnimationCallback(this)
        callback.onNewAnimation(currentAnimation!!)
        currentAnimation?.start()
    }

    override fun onAnimationEnd(drawable: Drawable?) {
        runNextAnimation()
        super.onAnimationEnd(drawable)
    }

    interface Callback {

        fun onNewAnimation(newAnimation: Drawable)
    }
}