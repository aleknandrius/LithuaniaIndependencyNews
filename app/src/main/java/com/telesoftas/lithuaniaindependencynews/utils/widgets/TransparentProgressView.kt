package com.telesoftas.lithuaniaindependencynews.utils.widgets

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ProgressBar

class TransparentProgressView : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val progressBar = ProgressBar(context)
        val layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        progressBar.layoutParams = layoutParams
        progressBar.isIndeterminate = true
        isClickable = true
        addView(progressBar)
    }

    override fun setVisibility(visibility: Int) {
        if (visibility == View.VISIBLE && getVisibility() != View.VISIBLE) {
            startAnimation(getShowAnimation())
        } else if (visibility != View.VISIBLE && getVisibility() == View.VISIBLE) {
            startAnimation(getHideAnimation())
        }
        super.setVisibility(visibility)
    }

    private fun getShowAnimation(): AlphaAnimation {
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = ANIMATION_DURATION_MILLIS.toLong()
        animation.fillAfter = true
        return animation
    }

    private fun getHideAnimation(): AlphaAnimation {
        val animation = AlphaAnimation(alpha, 0.0f)
        animation.duration = ANIMATION_DURATION_MILLIS.toLong()
        animation.fillAfter = false
        return animation
    }

    companion object {
        private const val ANIMATION_DURATION_MILLIS = 200
    }
}