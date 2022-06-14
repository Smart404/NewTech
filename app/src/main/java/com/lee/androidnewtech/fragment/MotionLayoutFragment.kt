package com.lee.androidnewtech.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lee.androidnewtech.R

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
    }
}