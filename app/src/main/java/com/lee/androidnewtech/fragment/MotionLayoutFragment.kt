package com.lee.androidnewtech.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import com.lee.androidnewtech.R
import com.lee.androidnewtech.viewpager.ViewPagerAdapter

private const val ARG_PARAM1 = "param1"

class MotionLayoutFragment : Fragment() {
    private var param1: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }//获取数据
        val view = inflater.inflate(R.layout.fragment_motion_layout, container, false)
        view.findViewById<TextView>(R.id.tipText).text = param1
        val motionLayout = view.findViewById<MotionLayout>(R.id.content)
        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ViewPagerAdapter()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    Log.d(Companion.TAG, "onScrolled: $dx -- $dy")
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val canScrollTopEdgeTreatment = recyclerView.canScrollVertically(-1)
                    Log.d(TAG, "onScrollStateChanged: $newState -- $canScrollTopEdgeTreatment")
                    if (newState == SCROLL_STATE_DRAGGING && !canScrollTopEdgeTreatment) {
                        motionLayout.transitionToStart()
                    }
                }
            })
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MotionLayoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            MotionLayoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }

        private const val TAG = "MotionLayoutFragment11"
    }
}