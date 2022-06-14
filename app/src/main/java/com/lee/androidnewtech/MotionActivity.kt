package com.lee.androidnewtech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.lee.androidnewtech.databinding.ActivityViewpager2Binding
import com.lee.androidnewtech.fragment.MotionLayoutFragment
import com.lee.androidnewtech.viewpager.LivePageAdapter
import com.lee.androidnewtech.viewpager.MyOnPageChangeCallback

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
            3 -> setContentView(R.layout.activity_motion3)
            4 -> {
                val itemBinding = ActivityViewpager2Binding.inflate(layoutInflater)
                setContentView(itemBinding.root)
                val fragmentList: ArrayList<Fragment> = ArrayList()
                for (i in 0..4) {
                    fragmentList.add(MotionLayoutFragment.newInstance("$i"))
                }
                itemBinding.viewpager.adapter = LivePageAdapter(this, fragmentList)
                itemBinding.viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
                itemBinding.viewpager.registerOnPageChangeCallback(MyOnPageChangeCallback(itemBinding.viewpager,fragmentList.size))
            }
            else -> setContentView(R.layout.activity_motion)
        }

    }
}