package com.example.myapplication.adapters

import com.example.myapplication.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Station


class StationAdapter(): RecyclerView.Adapter<StationAdapter.ViewHolder>() {
    private lateinit var stations: ArrayList<Station>

    constructor(stations: ArrayList<Station>) : this() {
        this.stations = stations
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_station, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val station:Station= stations!![position]
        holder.updateUI(station)
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

           val imageView: ImageView = itemView.findViewById(R.id.main_image)
           val textView: TextView = itemView.findViewById(R.id.main_text)
        fun updateUI(station:Station){
            val url:String=station.getImgUrl()
            val resource:Int=imageView.getResources().getIdentifier(url,null
                ,imageView.context.packageName)
            imageView.setImageResource(resource)
            textView.text = station.getStationTitle()
        }
    }

}