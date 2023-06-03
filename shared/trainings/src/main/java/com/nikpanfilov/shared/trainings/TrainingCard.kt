package com.nikpanfilov.shared.trainings

import com.nikpanfilov.shared.trainings.domain.entity.TrainingCard

val trainings = mapOf(
	Pair(TrainingType.CRUNCH, TrainingCard(title = R.string.crunch, text = R.string.crunch_text, image = R.drawable.crunch)),
	Pair(TrainingType.SQUATS, TrainingCard(title = R.string.squat, text = R.string.squat_text, image = R.drawable.squat)),
	Pair(TrainingType.PUSH_UP, TrainingCard(title = R.string.pushup, text = R.string.pushup_text, image = R.drawable.pushup)),
	Pair(TrainingType.PLANK, TrainingCard(title = R.string.plank, text = R.string.plank_text, image = R.drawable.plank)),
	Pair(TrainingType.RUNNING, TrainingCard(title = R.string.running, text = R.string.running_text, image = R.drawable.running)),
)

enum class TrainingType(val type: String) {
	CRUNCH("CRUNCH"),
	SQUATS("SQUATS"),
	PUSH_UP("PUSH_UP"),
	PLANK("PLANK"),
	RUNNING("RUNNING"),
}

const val MIN_RUNNING = 900
const val INCREASE_RUNNING = 100
const val MIN_CRUNCH = 5
const val INCREASE_CRUNCH = 5
const val MIN_SQUATS = 5
const val INCREASE_SQUATS = 5
const val MIN_PLANK = 15
const val INCREASE_PLANK = 5
const val MIN_PUSHUPS = 5
const val INCREASE_PUSHUPS = 5