package com.example.testactivity

import android.os.Build
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //private var tv2: TextView? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv1 : TextView = findViewById(R.id.tv1Id)
        var np : NumberPicker = findViewById(R.id.npId)

        //bat su kien o number picker cho textview
        np.setOnValueChangedListener { picker, oldVal, newVal -> tv1!!.text = newVal.toString() }
        np.maxValue = 100
        np.minValue = 0


        var tv2 : TextView = findViewById(R.id.tv2Id)
        val tp : TimePicker = findViewById(R.id.tpId)
        val h = tp.currentHour
        val m = tp.currentMinute
        val tm = StringBuilder()
        tm.append("$h h $m m")
        tv2!!.text = tm
        //bat su kien time picker cho textview
        tp.setOnTimeChangedListener { view, hour, minute ->
            val tm = StringBuilder()
            tm.append("$hour h $minute m ")
            tv2!!.text = tm
        }
    }
}

