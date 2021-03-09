package com.daerong.refresh_project

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.daerong.refresh_project.AlarmReceiver.Companion.TAG
import kotlinx.android.synthetic.main.fragment_wakeup.*

class WakeupFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wakeup, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) 
        //OnCreated에서 했던 작업 하기
        val alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(activity, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            activity, AlarmReceiver.NOTIFICATION_ID, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        upToggleBtn.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                regist(view, pendingIntent, alarmManager)
            }
            else{
                unregist(view, pendingIntent, alarmManager)
            }

        }
    }

    fun regist(view: View, pi:PendingIntent, alarmManager: AlarmManager){
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 2)
            set(Calendar.MINUTE, 59)
        }
        Log.d(TAG, "Realtime periodic Alarm On")

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pi)
    }

    fun unregist(view: View, pi: PendingIntent, alarmManager: AlarmManager){
        alarmManager.cancel(pi)
        Log.d(TAG, "Realtime periodic Alarm Off")
    }
}