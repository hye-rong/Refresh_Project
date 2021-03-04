package com.daerong.refresh_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.routine.view.*

class RoutineAdapter(val context: Context?, val routines: List<Routine>)
    :RecyclerView.Adapter<RoutineAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkbox: CheckBox = view.checkBox
        val textView: TextView = view.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.routine, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineAdapter.ViewHolder, position: Int) {
        val item = routines[position]
        holder.checkbox.isChecked = item.isChecked != 0
        holder.textView.text = item.rname
    }

    override fun getItemCount(): Int = routines.size
}