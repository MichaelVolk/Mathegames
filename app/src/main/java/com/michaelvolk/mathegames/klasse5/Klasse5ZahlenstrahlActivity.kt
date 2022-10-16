package com.michaelvolk.mathegames.klasse5

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.michaelvolk.mathegames.CanvasView
import com.michaelvolk.mathegames.MainActivity
import com.michaelvolk.mathegames.R
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs


class Klasse5ZahlenstrahlActivity : AppCompatActivity(), OnTouchListener {
    var height: Int = 0
    var width: Int = 0
    var navBarHeight = 0
    var actionBarHeight = 0
    var numbers: ArrayList<TextView> = ArrayList<TextView>()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_klasse5_zahlenstrahl)
        val textview = findViewById<TextView>(R.id.mytextview)
        val textview1 = findViewById<TextView>(R.id.mytextview1)
        val rahmenview = findViewById<ImageView>(R.id.rahmen1)
        val zahlenstrahlview = findViewById<CanvasView>(R.id.paintzahlenstrahl)



        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val resources: Resources = applicationContext.resources
        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
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


        textview.text = "1"
        textview1.text = "2"
        textview.x = 100F
        textview.y = 800F
        textview1.x = 300F
        textview1.y = 800F
        rahmenview.x = 200F
        rahmenview.y = 300F
        zahlenstrahlview.x = 100F
        zahlenstrahlview.y = 600F

        textview.setOnTouchListener(this)
        textview1.setOnTouchListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myIntent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(myIntent, 0)
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        var xDown: Float = 0f
        var yDown: Float = 0f
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
                    max(0f, view.x + deltaX - view.width / 2), width.toFloat() - view.width
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
}