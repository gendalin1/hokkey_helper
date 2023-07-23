package com.example.hokkey_helper.ui.store

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ShakeDetector(private val context: Context, private val listener: OnShakeListener) :
    SensorEventListener {

    private val shakeThresholdGravity = 2.7f // Это значение можно настроить под свои нужды
    private val shakeTimeLapse = 500 // Интервал времени между двумя событиями тряски в миллисекундах
    private var lastShakeTime: Long = 0

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Ничего не делаем
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // Вычисляем силу тряски
            val gForce = Math.sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()

            if (gForce > shakeThresholdGravity) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastShakeTime >= shakeTimeLapse) {
                    lastShakeTime = currentTime
                    listener.onShake()
                }
            }
        }
    }

    interface OnShakeListener {
        fun onShake()
    }
}