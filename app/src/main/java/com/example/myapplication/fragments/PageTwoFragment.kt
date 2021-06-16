package com.example.myapplication.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapters.OnTodoClickListener
import com.example.myapplication.adapters.TaskAdapter
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import com.example.myapplication.util.Prefs
import com.example.myapplication.util.Utils
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PageTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PageTwoFragment : Fragment(),View.OnClickListener , OnTodoClickListener{
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dialog: Dialog
    lateinit var calendar: Calendar
    lateinit var myTask:Task
    var dueDate:Date?=null
    //var sharedViewModel:SharedViewModel
    var isEdit:Boolean = false
    var priority: Priority?=null
    lateinit var tasks:ArrayList<Task>
    lateinit var taskAdpater: TaskAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var prefs:Prefs
    var idTask:Long=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_page_two, container, false)
        prefs= Prefs()
        tasks= context?.let { prefs.readTaskList(it) }!!


        recyclerView =view.findViewById(R.id.recycler_view)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        dialog= context?.let { Dialog(it) }!!
        fab.setOnClickListener { view: View? -> showDialog() }
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager= LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        taskAdpater= TaskAdapter(tasks,this)
        recyclerView.adapter=taskAdpater

        return view
    }

    private fun showDialog() {
        val utils=Utils()
        dialog.setContentView(R.layout.tododialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val imageViewClose: ImageView =dialog.findViewById(R.id.imageViewClose)
        val saveBtn: Button =dialog.findViewById(R.id.save_btn)
        calendar= Calendar.getInstance()
        val enterTodo: EditText=dialog.findViewById(R.id.enter_todo_et)
        val calendarButton: ImageButton=dialog.findViewById(R.id.today_calendar_button)
        val priorityButton: ImageButton=dialog.findViewById(R.id.priority_todo_button)
        val priorityRadioGroup: RadioGroup=dialog.findViewById(R.id.radioGroup_priority)
        var selectedRadioButton: RadioButton
        var selectedButtonId: Int
        val calendarView:CalendarView=dialog.findViewById(R.id.calendar_view)
        val calendarGroup: Group =dialog.findViewById(R.id.calendar_group)
        val todayChip: Chip = dialog.findViewById(R.id.today_chip)
        todayChip.setOnClickListener(this)
        val tomorrowChip: Chip = dialog.findViewById(R.id.tomorrow_chip)
        tomorrowChip.setOnClickListener(this)
        val nextWeekChip: Chip = dialog.findViewById(R.id.next_week_chip)
        nextWeekChip.setOnClickListener(this)
        calendarButton.setOnClickListener { view12: View? ->
            calendarGroup.visibility =
                if (calendarGroup.visibility == View.GONE) View.VISIBLE else View.GONE
            if (view12 != null) {
                utils.hideSoftKeyboard(view12)
            }
        }
        calendarView.setOnDateChangeListener { calendarView: CalendarView?, year: Int, month: Int, dayOfMoth: Int ->
            calendar.clear()
            calendar[year, month] = dayOfMoth
            dueDate = calendar.time
        }
        priorityButton.setOnClickListener { view13: View? ->
            if (view13 != null) {
                utils.hideSoftKeyboard(view13)
            }
            priorityRadioGroup.visibility =
                if (priorityRadioGroup.visibility == View.GONE) View.VISIBLE else View.GONE
            priorityRadioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
                if (priorityRadioGroup.visibility == View.VISIBLE) {
                    selectedButtonId = checkedId
                    selectedRadioButton = dialog.findViewById(selectedButtonId)
                    priority = if (selectedRadioButton.id == R.id.radioButton_high) {
                        Priority.HIGH
                    } else if (selectedRadioButton.id == R.id.radioButton_med) {
                        Priority.MEDIUM
                    } else if (selectedRadioButton.id == R.id.radioButton_low) {
                        Priority.LOW
                    } else {
                        Priority.LOW
                    }
                } else {
                    priority = Priority.LOW
                }
            }
        }
        imageViewClose.setOnClickListener { dialog.dismiss()}
        saveBtn.setOnClickListener {
            val task = enterTodo.text.toString().trim()
            if (!TextUtils.isEmpty(task) && dueDate != null && priority != null){
                val myTask = Task(idTask++,
                    task, priority!!,
                    dueDate!!, Calendar.getInstance().time,
                    false
                )
                tasks.add(myTask)
                tasks.let { it1 -> context?.let { it2 -> prefs.saveTaskList(it1, it2) } }
                taskAdpater.notifyDataSetChanged()



            }

            else {
                Snackbar.make(saveBtn, R.string.empty_field, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        dialog.show()
        }

    private fun editDialog(myTaskEdited:Task) {
        val utils=Utils()
        dialog.setContentView(R.layout.tododialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val imageViewClose: ImageView =dialog.findViewById(R.id.imageViewClose)
        val saveBtn: Button =dialog.findViewById(R.id.save_btn)
        calendar= Calendar.getInstance()
        val enterTodo: EditText=dialog.findViewById(R.id.enter_todo_et)
        val calendarButton: ImageButton=dialog.findViewById(R.id.today_calendar_button)
        val priorityButton: ImageButton=dialog.findViewById(R.id.priority_todo_button)
        val priorityRadioGroup: RadioGroup=dialog.findViewById(R.id.radioGroup_priority)
        var selectedRadioButton: RadioButton
        var selectedButtonId: Int
        val calendarView:CalendarView=dialog.findViewById(R.id.calendar_view)
        val calendarGroup: Group =dialog.findViewById(R.id.calendar_group)
        val todayChip: Chip = dialog.findViewById(R.id.today_chip)
        todayChip.setOnClickListener(this)
        val tomorrowChip: Chip = dialog.findViewById(R.id.tomorrow_chip)
        tomorrowChip.setOnClickListener(this)
        val nextWeekChip: Chip = dialog.findViewById(R.id.next_week_chip)
        nextWeekChip.setOnClickListener(this)
        calendarButton.setOnClickListener { view12: View? ->
            calendarGroup.visibility =
                if (calendarGroup.visibility == View.GONE) View.VISIBLE else View.GONE
            if (view12 != null) {
                utils.hideSoftKeyboard(view12)
            }
        }
        calendarView.setOnDateChangeListener { calendarView: CalendarView?, year: Int, month: Int, dayOfMoth: Int ->
            calendar.clear()
            calendar[year, month] = dayOfMoth
            dueDate = calendar.time
        }
        priorityButton.setOnClickListener { view13: View? ->
            if (view13 != null) {
                utils.hideSoftKeyboard(view13)
            }
            priorityRadioGroup.visibility =
                if (priorityRadioGroup.visibility == View.GONE) View.VISIBLE else View.GONE
            priorityRadioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup?, checkedId: Int ->
                if (priorityRadioGroup.visibility == View.VISIBLE) {
                    selectedButtonId = checkedId
                    selectedRadioButton = dialog.findViewById(selectedButtonId)
                    priority = if (selectedRadioButton.id == R.id.radioButton_high) {
                        Priority.HIGH
                    } else if (selectedRadioButton.id == R.id.radioButton_med) {
                        Priority.MEDIUM
                    } else if (selectedRadioButton.id == R.id.radioButton_low) {
                        Priority.LOW
                    } else {
                        Priority.LOW
                    }
                } else {
                    priority = Priority.LOW
                }
            }
        }
        imageViewClose.setOnClickListener { dialog.dismiss()}
        saveBtn.setOnClickListener {
            val task = enterTodo.text.toString().trim()
            if (!TextUtils.isEmpty(task) && dueDate != null && priority != null){
                val myTask = Task(idTask++,
                    task, priority!!,
                    dueDate!!, Calendar.getInstance().time,
                    false
                )
                val updateTask: Task = myTaskEdited

                updateTask.task = task
                updateTask.dateCreated = Calendar.getInstance().time
                updateTask.priority = priority as Priority
                updateTask.dueDate = dueDate as Date

                tasks.let { it1 -> context?.let { it2 -> prefs.saveTaskList(it1, it2) } }
                taskAdpater.notifyDataSetChanged()



            }

            else {
                Snackbar.make(saveBtn, R.string.empty_field, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
        dialog.show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PageTwoFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PageTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        val id = requireView().id
        if (id == R.id.today_chip) {
            //set data for today
            calendar.add(Calendar.DAY_OF_YEAR, 0)
            dueDate = calendar.getTime()
        } else if (id == R.id.tomorrow_chip) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            dueDate = calendar.getTime()
        } else if (id == R.id.next_week_chip) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
            dueDate = calendar.getTime()
        }
    }

    override fun onTodoClick(task: Task?) {

        if (task != null) {
            editDialog(task)
        }

    }

    override fun onTodoDeleteClick(task: Task?) {
          tasks.remove(task)
          taskAdpater.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar,menu)
        val searchItem:MenuItem=menu.findItem(R.id.search)
        val sv = SearchView((activity as MainActivity?)!!.supportActionBar!!.themedContext)
        MenuItemCompat.setShowAsAction(
            searchItem,
            MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItemCompat.SHOW_AS_ACTION_IF_ROOM
        )
        MenuItemCompat.setActionView(searchItem, sv)
        sv.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                taskAdpater.filter.let { query }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                taskAdpater.filter.let { newText }
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)

    }

}