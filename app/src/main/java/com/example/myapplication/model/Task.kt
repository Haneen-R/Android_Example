package com.example.myapplication.model

import java.util.*

class Task (taskId: Long,task: String,priority:Priority,dueDate:Date,dateCreated:Date,isDone:Boolean){
    var taskId:Long
    var task:String
    var priority:Priority
    var dueDate: Date
    var dateCreated: Date
    var isDone = false

    init {
        this.taskId = taskId
        this.task = task
        this.priority = priority
        this.dueDate=dueDate
        this.dateCreated=dateCreated
        this.isDone=isDone

    }

    fun getTaskID():Long{return this.taskId}
    fun getTaskString():String{return this.task}
    fun getPriorityTask():Priority{return this.priority}
    fun getDueDateTask():Date{return this.dueDate}
    fun getDateCreatedTask():Date{return this.dateCreated}
    fun isDoneTask():Boolean{return this.isDone}
    fun setTaskID(taskId:Long) {this.taskId=taskId}
    fun setITaskString(task:String) {this.task=task}
    fun setPriorityTask(priority:Priority) {this.priority=priority}
    fun setDueDateTask(dueDate:Date){this.dueDate=dueDate}
    fun setDateCreatedTask(dateCreated: Date){this.dateCreated=dateCreated}
}
