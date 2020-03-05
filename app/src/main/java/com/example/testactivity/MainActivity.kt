package com.example.testactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun onAccess(view: View) {
        val uri: Uri = Uri.parse("http://www.google.com")
        val it = Intent(Intent.ACTION_VIEW, uri)
        startActivity(it)
    }
}
