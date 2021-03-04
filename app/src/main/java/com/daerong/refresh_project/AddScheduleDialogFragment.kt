package com.daerong.refresh_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddScheduleDialogFragment() : DialogFragment() {
    private var routineDB: RoutineDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_schedule_dialog, container, false)
        val addBtn : Button = view.findViewById(R.id.addBtn)
        val scheduleText : EditText = view.findViewById(R.id.scheduleText)

        routineDB = RoutineDB.getInstance(context)

        val addRunnable = Runnable {
            val newRoutine = Routine()
            newRoutine.rname = scheduleText.text.toString()
            newRoutine.isRoutine = 0
            newRoutine.isChecked = 0
            routineDB?.routineDao()?.insert(newRoutine)
        }

        addBtn.setOnClickListener {
            val thread = Thread(addRunnable)
            thread.start()
        }

        return view
    }

    override fun onDestroy() {
        RoutineDB.destroyInstance()
        super.onDestroy()
    }

    fun getInstance():AddScheduleDialogFragment {
        return AddScheduleDialogFragment()
    }
}