package com.example.testactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv : TextView = findViewById(R.id.tvId)
        var np : NumberPicker = findViewById(R.id.npId)

        //bat su kien o number picker cho textview
        np.setOnValueChangedListener { picker, oldVal, newVal -> tv!!.text = newVal.toString() }
        np.maxValue = 100
        np.minValue = 0
    }
}

