package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.lastIndexOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var temp1 : Int? = null
        var temp2 : Int? = null
        var isFirst : Boolean? = null
        firstedittext.setOnClickListener {
            isFirst = true
            temp1 = 0
        }
        secondedittext.setOnClickListener {
            isFirst = false
            temp2 =0
        }

        so1.setOnClickListener {
            if(isFirst!!){
                temp1 = 10 * temp1!! + 1
                temp11.text = temp1.toString()
            } else {
                temp2 = 10 * temp2!! + 1
                temp22.text = temp2.toString()
            }
        }
        cong.setOnClickListener {
//            var sothu1 : Int = (firstedittext.text.toString()).toInt()
//            var sothu2 : Int = (secondedittext.text.toString()).toInt()
            var ketqua : Int = temp1!! + temp2!!
            result.text = ketqua.toString()


        }
        tru.setOnClickListener {
            var sothu1 : Int = (firstedittext.text.toString()).toInt()
            var sothu2 : Int = (secondedittext.text.toString()).toInt()
            var ketqua : Int = sothu1 - sothu2
            result.text = ketqua.toString()
        }
        nhan.setOnClickListener {
            var sothu1 : Int = (firstedittext.text.toString()).toInt()
            var sothu2 : Int = (secondedittext.text.toString()).toInt()
            var ketqua : Int = sothu1 * sothu2
            result.text = ketqua.toString()
        }
        chia.setOnClickListener {
            var sothu1 : Int = (firstedittext.text.toString()).toInt()
            var sothu2 : Int = (secondedittext.text.toString()).toInt()
            var ketqua : Int = sothu1 / sothu2
            result.text = ketqua.toString()
        }
    }
}
