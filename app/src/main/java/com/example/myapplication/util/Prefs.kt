package com.example.myapplication.util
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.myapplication.model.LIST_KEY
import com.example.myapplication.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs {
    fun saveTaskList(taskList: ArrayList<Task>,context: Context) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val jsonString = gson.toJson(taskList)
        preferences.edit().apply {
            putString(LIST_KEY,jsonString)
            apply()
        }
    }

    fun readTaskList(context: Context): ArrayList<Task> {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val jsonString = preferences.getString(LIST_KEY,"")
        val type = object : TypeToken<ArrayList<Task>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}