package com.nikpanfilov.superfit.pushups.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.nikpanfilov.superfit.pushups.presentation.PushUpsEvent
import com.nikpanfilov.superfit.pushups.presentation.PushUpsViewModel

class AccelerometerListener(private val context: Context, private val viewModel: PushUpsViewModel) : SensorEventListener {

	private var sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
	val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

	private var previousY: Float? = null
	private var flag = false

	fun start() {
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
	}

	fun stop() {
		sensorManager.unregisterListener(this)
	}

	/*
	Y ~ 0
	X ~ |90| +- 20
	 */
	override fun onSensorChanged(event: SensorEvent) {
		val gravity = event.values
		event.values.last()
		val yAxis = gravity[1]

		if (previousY != null && yAxis != previousY!!) {
			val delta = yAxis - previousY!!
			Log.i("delta", delta.toString())
			if (delta < -0.2f) {
				flag = true
			}
			if (delta > 0.2f && flag) {
				flag = false
				viewModel.handle(PushUpsEvent.Done)
			}
		}

		if (previousY == null || yAxis != previousY)
			previousY = yAxis
	}

	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
