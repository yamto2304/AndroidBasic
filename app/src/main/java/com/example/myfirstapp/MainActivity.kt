package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            Toast.makeText(this, "Xin chao", Toast.LENGTH_SHORT).show()
        }
    }

//    fun Youclicked(view: View) {
//        Toast.makeText(this, "bandaclick", Toast.LENGTH_SHORT).show()
//    }
}
