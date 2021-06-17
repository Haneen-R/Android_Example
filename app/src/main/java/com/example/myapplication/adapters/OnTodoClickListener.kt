package com.example.myapplication.adapters
import com.example.myapplication.model.Task

interface OnTodoClickListener {
    fun onTodoClick(task: Task)
    fun onTodoDeleteClick(task: Task)
}