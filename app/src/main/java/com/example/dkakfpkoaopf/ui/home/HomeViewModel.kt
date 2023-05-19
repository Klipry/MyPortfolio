package com.example.dkakfpkoaopf.ui.home

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val containerMap: MutableMap<LinearLayout, AnimatorSet> = mutableMapOf()
    private val animatedLayouts: MutableSet<LinearLayout> = mutableSetOf()
    fun initialize(layout: LinearLayout) {
        val container = layout

        val startAlpha = 0f
        val endAlpha = 1f

        val appearanceAnimator = ObjectAnimator.ofFloat(container, "alpha", startAlpha, endAlpha)
        appearanceAnimator.duration = 2500
        appearanceAnimator.interpolator = AccelerateDecelerateInterpolator()

        val startX = -1500f
        val endX = 0f

        val movementAnimator = ObjectAnimator.ofFloat(container, "translationX", startX, endX)
        movementAnimator.duration = 2500
        movementAnimator.interpolator = AccelerateDecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(appearanceAnimator, movementAnimator)

        containerMap[container] = animatorSet
        Log.d("Animation", "Animation initialized for layout: $layout")
    }

    fun startAnimation(layout: LinearLayout) {
        if (!animatedLayouts.contains(layout)) {
            containerMap[layout]?.let { animatorSet ->
                if (animatorSet.isRunning) {
                    // Animation is already running, no need to start it again
                    return
                }

                val listener = object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        layout.visibility = View.VISIBLE
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        // Actions after animation ends
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        // Actions on animation cancellation
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                        // Actions on animation repetition
                    }
                }
                animatorSet.addListener(listener)
                layout.visibility = View.INVISIBLE
                animatorSet.start()
                animatedLayouts.add(layout) // Add the layout to the list of animated layouts



            }
        }
    }


}

