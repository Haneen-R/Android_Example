package com.example.myapplication.model

import java.util.*

class Task(
    var taskId: Long,
    var task: String,
    var priority: Priority,
    var dueDate: Date,
    var dateCreated: Date,
    var isDone: Boolean
) {
    fun getTaskID(): Long{return this.taskId}
    fun getTaskString(): String{return this.task}
    fun getPriorityTask(): Priority{return this.priority}
    fun getDueDateTask() :Date{return this.dueDate}
    fun getDateCreatedTask(): Date{return this.dateCreated}
    fun isDoneTask(): Boolean{return this.isDone}
    fun setTaskID(taskId: Long) {this.taskId=taskId}
    fun setITaskString(task: String) {this.task=task}
    fun setPriorityTask(priority: Priority) {this.priority=priority}
    fun setDueDateTask(dueDate: Date){this.dueDate=dueDate}
    fun setDateCreatedTask(dateCreated: Date){this.dateCreated=dateCreated}
}
