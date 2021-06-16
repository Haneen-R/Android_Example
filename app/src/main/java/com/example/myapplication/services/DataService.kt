package com.example.myapplication.services

import com.example.myapplication.model.Priority
import com.example.myapplication.model.Station
import com.example.myapplication.model.Task
import java.util.*
import kotlin.collections.ArrayList

object DataServive {
    var ourInstance:DataServive= DataServive
    fun getInstance():DataServive{
        return ourInstance
    }
    private fun DataService(){}
    fun getFeaturedStations(): ArrayList<Station>? {
        //download features from the internet

        val list:ArrayList<Station>?=ArrayList()
        list?.add(Station("Flight Plan(Tunes for Travel)"
            ,"flightplanmusic"))
        list?.add(Station("Two Wheelin' (Biking playlist)"
            ,"bicyclemusic"))
        list?.add(Station("Kids Jams(Music for children)"
            ,"kidsmusic"))


        return list

    }
    fun getRecentStations(): ArrayList<Station>? {
        //download features from the internet

        val list:ArrayList<Station>?=ArrayList()
        list?.add(Station("Flight Plan(Tunes for Travel)"
            ,"vinylmusic"))
        list?.add(Station("Two Wheelin' (Biking playlist)"
            ,"socialmusic"))
        list?.add(Station("Kids Jams(Music for children)"
            ,"keymusic"))

        return list

    }
    fun getPlayedStations(): ArrayList<Station>? {
        //download features from the internet

        val list:ArrayList<Station>?=ArrayList()


        return list

    }
    fun getTodoTasks(): ArrayList<Task>? {
        //download features from the internet

        val list:ArrayList<Task>?=ArrayList()
       list?.add(Task(123456789,"First Task",Priority.HIGH,
           Date(2021,6,15),Date(2021,6,14),true))
        list?.add(Task(1234567894,"Second Task",Priority.MEDIUM,
            Date(2021,6,15),Date(2021,6,14),true))

        return list

    }
}