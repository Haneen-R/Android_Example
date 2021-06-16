package com.example.myapplication.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Task
import com.example.myapplication.util.Utils
import com.google.android.material.chip.Chip


open class TaskAdapter():
    RecyclerView.Adapter<TaskAdapter.ViewHolder>(), Filterable {

    lateinit var tasks: ArrayList<Task>
    lateinit var searchTasks: ArrayList<Task>
    lateinit var onTodoClickListener: OnTodoClickListener

    constructor(tasks: ArrayList<Task>, onTodoClickListener: OnTodoClickListener) : this() {
        this.tasks = tasks
        searchTasks = tasks
        this.onTodoClickListener = onTodoClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task:Task= tasks[position]
        holder.updateTask(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val radioButton: AppCompatRadioButton = itemView.findViewById(R.id.todo_radio_button)
        val taskText: AppCompatTextView = itemView.findViewById(R.id.todo_row_todo)
        val todayChip: Chip = itemView.findViewById(R.id.todo_row_chip)
        val deleteTask:ImageButton=itemView.findViewById(R.id.delete_task)
        var todoClickListener: OnTodoClickListener=onTodoClickListener

        fun updateTask(task:Task){
            val util=Utils()
            val formatted: String = util.formatDate(task.getDueDateTask())
            val states=arrayOf(intArrayOf(-android.R.attr.state_enabled),intArrayOf(android.R.attr.state_enabled))
            val colors=
                intArrayOf(Color.LTGRAY, //disabled
                    util.priorityColor(task))
            val colorStateList = ColorStateList(states,colors)
              taskText.text = task.getTaskString()
              todayChip.text=formatted
              todayChip.setTextColor(util.priorityColor(task))
              todayChip.setChipIconTint(colorStateList)
              radioButton.setButtonTintList(colorStateList)
              deleteTask.setOnClickListener(this)
              itemView.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            val currTask: Task = tasks.get(adapterPosition)
            val id = view.id
            if (id == R.id.todo_row_layout) {
                onTodoClickListener.onTodoClick(currTask)
            } else if (id == R.id.delete_task) {
                onTodoClickListener.onTodoDeleteClick(currTask)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList:ArrayList<Task> = ArrayList()
                if(constraint==null|| constraint.isEmpty()){
                    filteredList.addAll(searchTasks)
                }else{

                    val filterPattern:String=constraint.toString().lowercase().trim()
                    for (task:Task in searchTasks){
                        if(task.getTaskString().lowercase().contains(filterPattern)){
                            filteredList.add(task)
                        }
                    }
                }
                val filteredResults= FilterResults()
                filteredResults.values=filteredList
           return filteredResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                tasks.clear()
                tasks.addAll(results!!.values as ArrayList<Task>)
                notifyDataSetChanged()
            }

        }
    }

}