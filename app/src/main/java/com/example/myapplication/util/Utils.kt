package com.example.myapplication.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.myapplication.model.DATE_PATTERN
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import java.text.SimpleDateFormat
import java.util.*


 class Utils {
     fun formatDate(date: Date): String {
        val simpleDateFormat = SimpleDateFormat.getDateInstance() as SimpleDateFormat
        simpleDateFormat.applyPattern(DATE_PATTERN)
        return simpleDateFormat.format(date)
    }

     fun hideSoftKeyboard(view: View) {
         (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
             .hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun priorityColor(task: Task): Int {
        return when (task.priority) {
            Priority.HIGH -> Color.argb(150, 201, 21, 23)
            Priority.MEDIUM -> Color.argb(150, 255, 179, 0)
            else -> Color.argb(150, 51, 181, 229)
        }
    }
}