package com.example.myapplication.ui

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.StationAdapter
import com.example.myapplication.services.DataServive

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class StationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    open val STATION_TYPE_FEATURED: Int =0
    open val STATION_TYPE_RECENT: Int =1
    open val STATION_TYPE_PARTY: Int =2
    val ARG_STATION_TYPE: String ="station_type"

    var stationType:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stationType = it.getInt(ARG_STATION_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_station, container, false)
        val recyclerView:RecyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager= LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        val stationAdpater:StationAdapter
        if(stationType==STATION_TYPE_FEATURED){
            stationAdpater= DataServive.getInstance().getFeaturedStations()?.let { StationAdapter(it) }!!
        }else if(stationType==STATION_TYPE_RECENT){
            stationAdpater= DataServive.getInstance().getRecentStations()?.let { StationAdapter(it) }!!
        }else{
            stationAdpater= DataServive.getInstance().getPlayedStations()?.let { StationAdapter(it) }!!
        }
        recyclerView.addItemDecoration(HorizontalSpaceItemDecorator(30))
        recyclerView.adapter=stationAdpater

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param stationType the radio station type.

         * @return A new instance of fragment StationFragment.
         */
        @JvmStatic
        fun newInstance(stationType: Int) =
            StationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_STATION_TYPE, stationType)
                }
            }
    }
    class HorizontalSpaceItemDecorator(private val spacer: Int):RecyclerView.ItemDecoration(){

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right=spacer
        }


    }
}