package com.daerong.refresh_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddRoutineDialogFragment() : DialogFragment() {
    private var routineDB: RoutineDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_routine_dialog, container, false)
        val addBtn :Button = view.findViewById(R.id.addBtn)
        val routineText :EditText = view.findViewById(R.id.routineText)

        routineDB = RoutineDB.getInstance(context)

        val addRunnable = Runnable {
            val newRoutine = Routine()
            newRoutine.rname = routineText.text.toString()
            newRoutine.isRoutine = 1
            newRoutine.isChecked = 0
            routineDB?.routineDao()?.insert(newRoutine)
        }

        addBtn.setOnClickListener {
            val thread = Thread(addRunnable)
            thread.start()
        }

        return view
    }

    fun getInstance():AddRoutineDialogFragment {
        return AddRoutineDialogFragment()
    }
}