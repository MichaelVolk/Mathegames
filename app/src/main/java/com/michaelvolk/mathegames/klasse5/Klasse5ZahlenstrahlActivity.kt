package com.michaelvolk.mathegames.klasse5

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.michaelvolk.mathegames.MainActivity
import com.michaelvolk.mathegames.R
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs
import com.michaelvolk.mathegames.math.Utils

class Klasse5ZahlenstrahlActivity : AppCompatActivity(), OnTouchListener {
    var height: Int = 0
    var screenwidth: Int = 0
    var navBarHeight = 0
    var actionBarHeight = 0
    var numberviews: ArrayList<TextView> = ArrayList<TextView>()



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klasse5_zahlenstrahl)
        val rahmenview = findViewById<ImageView>(R.id.rahmen1)
        var zahlenstrahlview = findViewById<ImageView>(R.id.paintzahlenstrahl)





        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val resources: Resources = applicationContext.resources
        height = displayMetrics.heightPixels
        screenwidth = displayMetrics.widthPixels
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navBarHeight = resources.getDimensionPixelSize(resourceId)
        }
        //val navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        val tv = TypedValue()

        if (this.theme.resolveAttribute(androidx.appcompat.R.attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        } else {
            actionBarHeight = 0
        }


        rahmenview.x = 200F
        rahmenview.y = 300F
        zahlenstrahlview.x = 100F
        zahlenstrahlview.y = 600F
        val startZahlenstrahl = (1..19).random()
        var lower = 0
        var upper = 0
        if (startZahlenstrahl < 11) {
            upper = startZahlenstrahl + 10
            lower = startZahlenstrahl
        } else {
            upper  = startZahlenstrahl
            lower = upper - 10
        }
        val numbers = Utils.getRandomNumbers(lower, upper, 5)
        val bitmap = drawOnCanvas(lower, upper, numbers)
        zahlenstrahlview.background = BitmapDrawable(getResources(), bitmap)
        var layout: RelativeLayout = findViewById(R.id.relLayout)
        for (i in 1..4) {
            val viewtemp = TextView(this.applicationContext).apply {
                text = numbers[i].toString()
                x = 100F + 200 * (i - 1)
                y = 1200F
                width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics)
                    .toInt()
                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics)
                    .toInt()
                gravity = Gravity.CENTER
                textSize = 30F
            }
            viewtemp.setBackgroundResource(R.drawable.rounded_edges)
            viewtemp.setOnTouchListener(this)
            layout.addView(viewtemp)
            numberviews.add(viewtemp)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        var xDown = 0f
        var yDown = 0f
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                xDown = event.x
                yDown = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val newX: Float = event.x
                val newY: Float = event.y
                val deltaX = newX - xDown
                val deltaY = newY - yDown

                view.x = min(
                    max(0f, view.x + deltaX - view.width / 2), screenwidth.toFloat() - view.width
                )
                if (Build.VERSION.SDK_INT > 31) {
                    view.y = min(
                        max(0f, view.y + deltaY - view.height / 2),
                        height.toFloat() - view.height - (actionBarHeight + navBarHeight)
                    )
                } else {
                    view.y = min(
                        max(0f, view.y + deltaY - view.height / 2),
                        height.toFloat() - view.height - (actionBarHeight + navBarHeight / 2)
                    )

                }

            }
            MotionEvent.ACTION_UP -> {
                if(abs(view.x - 200) < 100 && abs(view.y - 300) < 100) {
                    view.x = 221F
                    view.y = 321F
                }
                //TODO("Hier ablegen handeln (check, ob in der Nähe einer anderen Box...")

            }


        }
        return true
    }

    fun drawOnCanvas(lower: Int, upper: Int, nums: ArrayList<Int>): Bitmap {
        val thickLine =
            Paint().apply {
                isAntiAlias = true
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 15F
            }
        val thinLine =
            Paint().apply {
                isAntiAlias = true
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 10F
            }
        val textLine =
            Paint().apply {
                isAntiAlias = true
                color = Color.BLACK
                style = Paint.Style.FILL_AND_STROKE
                strokeWidth = 2F
                textSize = 60F
            }
        val localwidth = screenwidth - 50
        val bitmap: Bitmap = Bitmap.createBitmap(localwidth, 500, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        val bigLineAt = 10 - lower
        canvas.drawLine(0F, 30F, localwidth.toFloat(), 30F, thickLine)
        for (i in 0 until 11) {
            if (i != bigLineAt) {
                canvas.drawLine(
                    (i * localwidth) / 10F, 10F, (i * localwidth) / 10F,
                    50F, thinLine
                )
            }
        }
        canvas.drawLine(
            (bigLineAt * localwidth) / 10F, 0F, (bigLineAt * localwidth) / 10F,
            60F, thinLine
        )

        return bitmap
    }
}