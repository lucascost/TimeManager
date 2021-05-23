package com.webdicas.timemanager

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class Task(view: View, name:String, var duration:Long) {
    var isRunning = false
    var timer:CountDownTimer?=null
    var startBtn=view.findViewById<ImageButton>(R.id.start)

    init {
        view.findViewById<TextView>(R.id.name).setText(name)
        view.findViewById<TextView>(R.id.timer).setText(formatFull(duration.toInt()))

        startBtn.setOnClickListener {
            if(!isRunning){
                isRunning=true
                startBtn.setImageResource(R.drawable.pause)
                startTimer(view)
            }
            else{
                startBtn.setImageResource(R.drawable.start)
                pauseTimer()
                isRunning=false
            }
        }
    }
    private fun startTimer(v:View){
        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                duration= millisUntilFinished
                v.findViewById<TextView>(R.id.timer).setText(formatFull(duration.toInt()))
            }

            override fun onFinish() {
                isRunning = false
            }
        }.start()
    }
    private fun pauseTimer(){
        timer?.cancel()
    }
}