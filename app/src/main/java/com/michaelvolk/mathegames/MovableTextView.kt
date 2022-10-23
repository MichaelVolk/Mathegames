package com.michaelvolk.mathegames

import android.content.Context
import android.util.AttributeSet

class MovableTextView(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    var initY: Float
    var initX: Float

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MovableTextView,
            0, 0
        ).apply {

            try {
                initX = getFloat(R.styleable.MovableTextView_initX, 0F)
                initY = getFloat(R.styleable.MovableTextView_initY, 0F)

            } finally {
                recycle()
            }
        }
    }
}