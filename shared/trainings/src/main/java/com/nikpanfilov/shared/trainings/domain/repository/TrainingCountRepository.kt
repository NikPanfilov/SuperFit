package com.nikpanfilov.shared.trainings.domain.repository

interface TrainingCountRepository {

	fun getMaxCrunch(): Int
	fun setMaxCrunch(count: Int)

	fun getMaxSquats(): Int
	fun setMaxSquats(count: Int)

	fun getMaxPlank(): Int
	fun setMaxPlank(count: Int)

	fun getMaxRunning(): Int
	fun setMaxRunning(count: Int)

	fun getMaxPushUps(): Int
	fun setMaxPushUps(count: Int)
}