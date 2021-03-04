package com.daerong.refresh_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RoutineFragment : Fragment(){
    private var routineDB: RoutineDB? = null
    private var routineList = listOf<Routine>()
    lateinit var adapter: RoutineAdapter
    lateinit var adapter2: RoutineAdapter
    var routines = listOf<Routine>()
    var schedules = listOf<Routine>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routine, container, false)
        val addRoutineBtn:Button = view.findViewById(R.id.addRoutineBtn)
        val addScheduleBtn:Button = view.findViewById(R.id.addScheduleBtn)
        val routineListView:RecyclerView = view.findViewById(R.id.routineList)
        val scheduleList:RecyclerView = view.findViewById(R.id.scheduleList)

        routineDB = RoutineDB.getInstance(context)

        val r = Runnable {
//            routineList = routineDB?.routineDao()?.getAll()!! //room db에 저장된 정보를 모두 읽어와서 리스트에 저장

        }

        val thread = Thread(r)
        thread.start()

        adapter = RoutineAdapter(context, routines)
        routineListView.adapter = adapter
        routineListView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter2 = RoutineAdapter(context, schedules)
        scheduleList.adapter = adapter
        scheduleList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        addRoutineBtn.setOnClickListener {
            val dialog :AddRoutineDialogFragment = AddRoutineDialogFragment().getInstance()
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog.show(
                    fragmentManager,
                    "ADD_ROUTINE"
                )
            }
        }

        addScheduleBtn.setOnClickListener {
            val dialog :AddScheduleDialogFragment = AddScheduleDialogFragment().getInstance()
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog.show(
                    fragmentManager,
                    "ADD_SCHEDULE"
                )
            }
        }

        return view
    }
}