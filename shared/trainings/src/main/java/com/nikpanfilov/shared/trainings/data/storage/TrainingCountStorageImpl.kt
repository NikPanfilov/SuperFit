package com.nikpanfilov.shared.trainings.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.nikpanfilov.shared.trainings.MIN_CRUNCH
import com.nikpanfilov.shared.trainings.MIN_PLANK
import com.nikpanfilov.shared.trainings.MIN_PUSHUPS
import com.nikpanfilov.shared.trainings.MIN_RUNNING
import com.nikpanfilov.shared.trainings.MIN_SQUATS
import com.nikpanfilov.shared.trainings.TrainingType

class TrainingCountStorageImpl(private val context: Context) : TrainingCountStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun getMaxCrunch(): Int =
		sharedPreferences.getInt(TrainingType.CRUNCH.type, MIN_CRUNCH)

	override fun setMaxCrunch(count: Int) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()
		e.putInt(TrainingType.CRUNCH.type, count)
		e.apply()
	}

	override fun getMaxSquats(): Int = sharedPreferences.getInt(TrainingType.SQUATS.type, MIN_SQUATS)

	override fun setMaxSquats(count: Int) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()
		e.putInt(TrainingType.SQUATS.type, count)
		e.apply()
	}

	override fun getMaxPlank(): Int = sharedPreferences.getInt(TrainingType.PLANK.type, MIN_PLANK)

	override fun setMaxPlank(count: Int) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()
		e.putInt(TrainingType.PLANK.type, count)
		e.apply()
	}

	override fun getMaxRunning(): Int = sharedPreferences.getInt(TrainingType.RUNNING.type, MIN_RUNNING)

	override fun setMaxRunning(count: Int) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()
		e.putInt(TrainingType.RUNNING.type, count)
		e.apply()
	}

	override fun getMaxPushUps(): Int = sharedPreferences.getInt(TrainingType.PUSH_UP.type, MIN_PUSHUPS)

	override fun setMaxPushUps(count: Int) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()
		e.putInt(TrainingType.PUSH_UP.type, count)
		e.apply()
	}
}