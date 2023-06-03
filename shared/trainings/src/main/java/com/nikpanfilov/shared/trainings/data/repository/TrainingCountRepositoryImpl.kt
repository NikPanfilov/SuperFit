package com.nikpanfilov.shared.trainings.data.repository

import com.nikpanfilov.shared.trainings.data.storage.TrainingCountStorage
import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository

class TrainingCountRepositoryImpl(private val storage: TrainingCountStorage) : TrainingCountRepository {

	override fun getMaxCrunch(): Int =
		storage.getMaxCrunch()

	override fun setMaxCrunch(count: Int) {
		storage.setMaxCrunch(count)
	}

	override fun getMaxSquats(): Int =
		storage.getMaxSquats()

	override fun setMaxSquats(count: Int) {
		storage.setMaxSquats(count)
	}

	override fun getMaxPlank(): Int =
		storage.getMaxPlank()

	override fun setMaxPlank(count: Int) {
		storage.setMaxPlank(count)
	}

	override fun getMaxRunning(): Int =
		storage.getMaxRunning()

	override fun setMaxRunning(count: Int) {
		storage.setMaxRunning(count)
	}

	override fun getMaxPushUps(): Int =
		storage.getMaxPushUps()

	override fun setMaxPushUps(count: Int) {
		storage.setMaxPushUps(count)
	}
}