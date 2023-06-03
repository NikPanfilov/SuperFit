package com.nikpanfilov.superfit.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Long.toDate(): String {
	val calendar = Calendar.getInstance()
	calendar.timeInMillis = this * 1000

	val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
	return format.format(calendar.time)
}

fun Long.toMonthAndYear(): String {
	val calendar = Calendar.getInstance()
	calendar.timeInMillis = this * 1000

	// Установка дня в начало месяца
	calendar.set(Calendar.DAY_OF_MONTH, 1)

	// Установка времени в полночь
	calendar.set(Calendar.HOUR_OF_DAY, 0)
	calendar.set(Calendar.MINUTE, 0)
	calendar.set(Calendar.SECOND, 0)
	calendar.set(Calendar.MILLISECOND, 0)

	val format = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
	return format.format(calendar.time)
}