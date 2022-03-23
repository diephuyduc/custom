package com.example.somemycustom

import android.content.Context
import android.graphics.Canvas
import android.graphics.fonts.FontFamily
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import java.util.concurrent.TimeUnit


import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.getResourceIdOrThrow
import kotlin.time.Duration.Companion.minutes


class TimerView(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attrs, defStyle) {
    private var textSize: Float = 0F
    private var showHour = true
    private var showMilliseconds = true
    private lateinit var fontFamily: FontFamily
    private var text: String? = null
    private lateinit var hour: TextView
    private lateinit var minute: TextView
    private lateinit var colon: TextView
    private lateinit var colon2: TextView
    private lateinit var second: TextView
    private lateinit var dot: TextView
    private lateinit var millisecond: TextView
    private var style: Int = 0
    var time: Long = 0L

    init {
        init(context, attrs, R.attr.s)
    }

    fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.timer_layout, this, true)
        val att = context.obtainStyledAttributes(attrs, R.styleable.timer_style)

        try {
            showHour = att.getBoolean(R.styleable.timer_style_showHours, true)
            showMilliseconds = att.getBoolean(R.styleable.timer_style_showMilliseconds, true)
            style = att.getInt(R.styleable.timer_style_style, 0)

            hour = findViewById(R.id.hour)
            colon = findViewById(R.id.colon)
            minute = findViewById(R.id.minutes)
            colon2 = findViewById(R.id.colon2)
            second = findViewById(R.id.second)
            dot = findViewById(R.id.dot)
            millisecond = findViewById(R.id.millisecond)
            style = defStyle



            hour.setTextAppearance(style)
            colon.setTextAppearance(style)
            minute.setTextAppearance(style)
            colon2.setTextAppearance(style)
            second.setTextAppearance(style)
            dot.setTextAppearance(style)
            millisecond.setTextAppearance(style)


        } catch (e: Exception) {
            print(e.toString())
        } finally {
            att.recycle()
        }
    }


    fun setMilliSecond(time: Long) {
        this.time = time
        if (showHour) {
            hour.text = time.toHH()
        } else {
            hour.visibility = View.GONE
            colon.visibility = View.GONE
        }

        if (showMilliseconds) {
            millisecond.text = time.toMs()
        } else {
            millisecond.visibility = View.GONE
            dot.visibility = View.GONE
        }
        this.invalidate()
    }


}

fun Long.toHH(): String {
    return String.format("%02d", (this / 1000) / (60 * 24))
}

fun Long.toMM(): String {
    return String.format("%02d", (this / 1000) / 60)
}

fun Long.toSS(): String {
    return String.format("%02d", (this / 1000))
}

fun Long.toMs(): String {
    return String.format("%02d", this % 100)
}
