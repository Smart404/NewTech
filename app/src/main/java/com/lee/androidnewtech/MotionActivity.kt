package com.lee.androidnewtech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MotionActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val ACTIVITY_TYPE = "activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (intent.getIntExtra(ACTIVITY_TYPE, -1)) {
            1 -> setContentView(R.layout.activity_motion)
            2 -> setContentView(R.layout.activity_motion2)
            else -> setContentView(R.layout.activity_motion)
        }

    }
}