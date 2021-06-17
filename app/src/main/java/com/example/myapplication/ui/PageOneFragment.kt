package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PageOneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageOneFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    open val STATION_TYPE_FEATURED: Int =0
    open val STATION_TYPE_RECENT: Int =1
    open val STATION_TYPE_PARTY: Int =2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_page_one, container, false)
        val fm:FragmentManager= requireActivity().supportFragmentManager
        val stationsFragment1:StationFragment= StationFragment.newInstance(STATION_TYPE_FEATURED)
        val stationsFragment2:StationFragment= StationFragment.newInstance(STATION_TYPE_RECENT)
        fm.beginTransaction().add(R.id.container_top_row,stationsFragment1).commit()
        fm.beginTransaction().add(R.id.container_middle_row,stationsFragment2).commit()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PageObeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PageOneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}