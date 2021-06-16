package com.example.myapplication.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import java.text.SimpleDateFormat
import java.util.*


open class Utils {
     open fun formatDate(date: Date): String {
        val simpleDateFormat: SimpleDateFormat =
            SimpleDateFormat.getDateInstance() as SimpleDateFormat
        simpleDateFormat.applyPattern("EEE, MMM d")
        return simpleDateFormat.format(date)
    }

     open fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

     open fun priorityColor(task: Task): Int {
        val color: Int
        color = if (task.priority == Priority.HIGH) {
            Color.argb(150, 201, 21, 23)
        } else if (task.priority == Priority.MEDIUM) {
            Color.argb(150, 255, 179, 0)
        } else {
            Color.argb(150, 51, 181, 229)
        }
        return color
    }
}