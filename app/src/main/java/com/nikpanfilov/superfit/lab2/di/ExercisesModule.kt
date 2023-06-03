package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.superfit.exercises.presentation.ExercisesViewModel
import com.nikpanfilov.superfit.plank.presentation.PlankViewModel
import com.nikpanfilov.superfit.pushups.presentation.PushUpsViewModel
import com.nikpanfilov.superfit.running.presentation.RunningViewModel
import com.nikpanfilov.superfit.squats.presentation.SquatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exercisesModule = module {
	viewModel {
		ExercisesViewModel(
			router = get()
		)
	}
	viewModel {
		PlankViewModel(
			router = get(),
			saveTrainingUseCase = get(),
			getPlankCountUseCase = get(),
			savePlankCountUseCase = get()
		)
	}
	viewModel {
		PushUpsViewModel(
			router = get(),
			saveTrainingUseCase = get(),
			getPushUpsCountUseCase = get(),
			savePushUpsCountUseCase = get()
		)
	}
	viewModel {
		RunningViewModel(
			router = get(),
			saveTrainingUseCase = get(),
			getRunningCountUseCase = get(),
			saveRunningCountUseCase = get()
		)
	}
	viewModel {
		SquatsViewModel(
			router = get(),
			saveTrainingUseCase = get(),
			getMaxSquatsUseCase = get(),
			saveMaxSquatsUseCase = get()
		)
	}
}