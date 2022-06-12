package com.lee.androidnewtech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee.androidnewtech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.text1.setOnClickListener {
            val intent = Intent(this, MotionActivity::class.java)
            intent.putExtra(MotionActivity.ACTIVITY_TYPE, 1)
            startActivity(intent)
        }
        mainBinding.text2.setOnClickListener {
            val intent = Intent(this, MotionActivity::class.java)
            intent.putExtra(MotionActivity.ACTIVITY_TYPE, 2)
            startActivity(intent)
        }
    }
}