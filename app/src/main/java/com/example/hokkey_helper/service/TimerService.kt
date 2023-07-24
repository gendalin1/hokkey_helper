package com.example.hokkey_helper.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class TimerService : Service() {

    private lateinit var timerUpdateListener: TimerUpdateListener
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var currentTime: Long = 0

    inner class LocalBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    // Метод onBind возвращает экземпляр LocalBinder
    override fun onBind(intent: Intent?): IBinder? {
        return LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
        handler = Handler(Looper.getMainLooper())
        startTimer()
    }

    fun registerTimerUpdateListener(listener: TimerUpdateListener) {
        timerUpdateListener = listener
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun startTimer() {
        runnable = Runnable {
            currentTime++
            timerUpdateListener.onTimerUpdate(currentTime)
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
    }



    interface TimerUpdateListener {
        fun onTimerUpdate(currentTime: Long)
    }
}