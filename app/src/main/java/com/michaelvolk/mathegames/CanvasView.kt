package com.michaelvolk.mathegames

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val thickLine =
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 15F
        }
    private val thinLine =
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 10F
        }
    private val textLine =
        Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 2F
            textSize = 60F
        }


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // DRAW STUFF HERE
        canvas.drawLine(0F, 30F, width.toFloat(), 30F, thickLine)
        for (i in 1 until 10) {
            canvas.drawLine((i * width) / 10F, 10F, (i * width) / 10F,
                50F, thinLine)
        }
        canvas.drawLine(8F, 0F, 8F, 60F, thickLine)
        canvas.drawLine(width.toFloat()-8, 0F, width.toFloat()-8, 60F, thickLine)
        canvas.drawText("0", 0F, 120F, textLine)


    }
}