package com.nikpanfilov.superfit.squats.ui

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import com.nikpanfilov.superfit.squats.presentation.SquatsEvent
import com.nikpanfilov.superfit.squats.presentation.SquatsViewModel

class AccelerometerListener(private val viewModel: SquatsViewModel) : SensorEventListener {

	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
	}

	private var previousY: Float? = null
	private val deltas = mutableListOf<Float>()
	private var flag = false

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
				viewModel.handle(SquatsEvent.Done)
			}
		}

		if (previousY == null || yAxis != previousY)
			previousY = yAxis
	}
}
