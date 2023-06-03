package com.nikpanfilov.superfit.lab2.domain.usecase

import com.nikpanfilov.superfit.lab2.domain.repository.FirstStartRepository

class CheckFirstStartUseCase(private val repository: FirstStartRepository) {

	operator fun invoke() = repository.isFirstStart()
}