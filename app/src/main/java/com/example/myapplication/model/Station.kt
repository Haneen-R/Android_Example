package com.example.myapplication.model

class Station(private var stationTitle: String, private var imgUrl: String) {
    val DRAWABLE:String="drawable/"

    fun getStationTitle():String{return this.stationTitle}
    fun getImgUrl():String{return DRAWABLE+this.imgUrl}
    fun setStationTitle(stationTitle:String) {this.stationTitle=stationTitle}
    fun setImgUrl(imgUrl:String) {this.imgUrl=imgUrl}
}