package com.example.myapplication.util
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import com.example.myapplication.fragments.PageTwoFragment
import com.example.myapplication.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs() {
    val LIST_KEY = "list_key"
    private lateinit var preferences: SharedPreferences

    fun Prefs(context: Activity) {

    }

    fun saveTaskList(taskList: ArrayList<Task>,context: Context) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson=Gson()
        val jsonString:String=gson.toJson(taskList)
        val editor:SharedPreferences.Editor=preferences.edit()
        editor.putString(LIST_KEY,jsonString)
        editor.apply()
    }
    fun readTaskList(context: Context): ArrayList<Task> {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gson=Gson()
        val jsonString: String? = preferences.getString(LIST_KEY,"")
        val type = object : TypeToken<ArrayList<Task>>() {}.type
        val tasks = gson.fromJson<ArrayList<Task>>(jsonString, type)
        return tasks
    }

}