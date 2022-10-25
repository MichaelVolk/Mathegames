package com.michaelvolk.mathegames.klasse5

import android.annotation.SuppressLint
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

import androidx.appcompat.app.AppCompatActivity

import com.michaelvolk.mathegames.MovableTextView
import com.michaelvolk.mathegames.R
import com.michaelvolk.mathegames.math.Utils

import java.lang.Float.max
import java.lang.Float.min

import kotlin.math.abs

class Klasse5ZahlenstrahlActivity : AppCompatActivity(), OnTouchListener {
    private var height: Int = 0
    private var screenwidth: Int = 0
    private var navBarHeight = 0
    private var actionBarHeight = 0
    private var numberviews: ArrayList<MovableTextView> = ArrayList()
    private var rahmenviews: ArrayList<ImageView> = ArrayList()
    private var xInit = 0F
    private var yInit = 0F


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Actionbar, damit der zurück-Knopf erscheint
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        // setting up views and nav
        setContentView(R.layout.activity_klasse5_zahlenstrahl)
        val zahlenstrahlview = findViewById<ImageView>(R.id.paintzahlenstrahl)


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

        actionBarHeight =
            if (this.theme.resolveAttribute(androidx.appcompat.R.attr.actionBarSize, tv, true)) {
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            } else {
                0
            }


        zahlenstrahlview.x = 0F
        zahlenstrahlview.y = 600F
        val startZahlenstrahl = (1..19).random()
        val upper: Int
        val lower: Int
        if (startZahlenstrahl < 11) {
            upper = startZahlenstrahl + 10
            lower = startZahlenstrahl
        } else {
            upper = startZahlenstrahl
            lower = upper - 10
        }
        val numbers = Utils.getRandomNumbers(lower, upper, 5)
        val bitmap = drawOnCanvas(lower, numbers)
        zahlenstrahlview.background = BitmapDrawable(getResources(), bitmap)
        numberviews.add(findViewById(R.id.number1))
        numberviews.add(findViewById(R.id.number2))
        numberviews.add(findViewById(R.id.number3))
        numberviews.add(findViewById(R.id.number4))
        for (i in 1..4) {
            val viewtemp = numberviews[i - 1]
            viewtemp.text = numbers[i].toString()
            viewtemp.x = 100F + 200 * (i - 1)
            viewtemp.y = 1200F
            viewtemp.initX = 100F + 200 * (i - 1)
            viewtemp.initY = 1200F
            viewtemp.width = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics
            ).toInt()
            viewtemp.height = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics
            ).toInt()
            viewtemp.gravity = Gravity.CENTER
            viewtemp.textSize = 30F
            viewtemp.setBackgroundResource(R.drawable.rounded_edges)
            viewtemp.setOnTouchListener(this)
        }

        rahmenviews.add(findViewById(R.id.rahmen1))
        rahmenviews.add(findViewById(R.id.rahmen2))
        rahmenviews.add(findViewById(R.id.rahmen3))
        rahmenviews.add(findViewById(R.id.rahmen4))

        for (i in 0..3) {
            val tempnums = numbers.subList(1, 5)
            tempnums.sort()
            rahmenviews[i].x = (tempnums[i] - lower.toFloat()) * (screenwidth - 200) / 10F + 25

            rahmenviews[i].y = if (i % 2 == 0) {
                400F
            } else {
                720F
            }
        }


    }

    // Wenn der Menüknopf "zurück" geklickt wird, schließt die Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
/*        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)*/
        this.finish()
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val xDown = 0f
        val yDown = 0f


        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (view is MovableTextView) {
                    yInit = view.initY
                    xInit = view.initX
                }
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
                var inBorder = false
                for (i in 0..3) {
                    if (abs(view.x - rahmenviews[i].x) < 100 && abs(view.y - rahmenviews[i].y) < 100) {
                        view.x = rahmenviews[i].x
                        view.y = rahmenviews[i].y
                        inBorder = true
                    }
                }
                if (!inBorder) {
                    view.x = xInit
                    view.y = yInit
                }

            }


        }
        return true
    }

    private fun drawOnCanvas(lower: Int, nums: ArrayList<Int>): Bitmap {
        /**
         *
         *  @property lower Lower limit of the number line
         *  @property nums Numbers to be shown at the number line
         *  @return A Bitmap containing the canvas and the number line drawn on it
         */
        val thickLine = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 15F
        }
        val thinLine = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 10F
        }
        val textLine = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 2F
            textSize = 60F
        }
        val localwidth = screenwidth - 200
        val bitmap: Bitmap = Bitmap.createBitmap(screenwidth, 500, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bigLineAt = 10 - lower
        canvas.drawLine(100F, 30F, screenwidth.toFloat() - 100, 30F, thickLine)
        for (i in 0 until 11) {
            if (i != bigLineAt) {
                canvas.drawLine(
                    100 + (i * localwidth) / 10F, 10F, 100 + (i * localwidth) / 10F, 50F, thinLine
                )
            }
        }
        canvas.drawLine(
            100 + (bigLineAt * localwidth) / 10F,
            0F,
            100 + (bigLineAt * localwidth) / 10F,
            60F,
            thinLine
        )
        if (nums[0] > 9) {
            canvas.drawText(
                nums[0].toString(), 40 + (nums[0] - lower) * localwidth / 10F, 110F, textLine
            )
        } else {
            canvas.drawText(
                nums[0].toString(), 70 + (nums[0] - lower) * localwidth / 10F, 110F, textLine
            )
        }

        return bitmap
    }
}