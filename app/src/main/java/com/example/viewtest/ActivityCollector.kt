package com.example.viewtest

import android.app.Activity

object ActivityCollector {
    private val activities=ArrayList<Activity>()
    fun add(activity: Activity){
        activities.add(activity)
    }
    fun remove(activity: Activity){
        activities.remove(activity)
    }
    fun exit(){
        for (activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }

}