package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.createRetrofitService
import com.nikpanfilov.shared.trainings.data.api.TrainingsHistoryApi
import com.nikpanfilov.shared.trainings.data.repository.TrainingCountRepositoryImpl
import com.nikpanfilov.shared.trainings.data.repository.TrainingsHistoryRepositoryImpl
import com.nikpanfilov.shared.trainings.data.storage.TrainingCountStorage
import com.nikpanfilov.shared.trainings.data.storage.TrainingCountStorageImpl
import com.nikpanfilov.shared.trainings.domain.repository.TrainingCountRepository
import com.nikpanfilov.shared.trainings.domain.repository.TrainingsHistoryRepository
import com.nikpanfilov.shared.trainings.domain.usecase.GetCrunchCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetPlankCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetPushUpsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetRunningCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetSquatsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.GetTrainingsUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveCrunchCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SavePlankCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SavePushUpsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveRunningCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveSquatsCountUseCase
import com.nikpanfilov.shared.trainings.domain.usecase.SaveTrainingUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val trainingsModule = module {
	single { GetTrainingsUseCase(get()) }
	single { SaveTrainingUseCase(get()) }

	single<TrainingsHistoryRepository> { TrainingsHistoryRepositoryImpl(get()) }

	factory { createRetrofitService<TrainingsHistoryApi>(get(named(ORIGINAL))) }


	single { GetCrunchCountUseCase(get()) }
	single { GetPlankCountUseCase(get()) }
	single { GetSquatsCountUseCase(get()) }
	single { GetRunningCountUseCase(get()) }
	single { GetPushUpsCountUseCase(get()) }

	single { SaveCrunchCountUseCase(get()) }
	single { SavePlankCountUseCase(get()) }
	single { SaveSquatsCountUseCase(get()) }
	single { SaveRunningCountUseCase(get()) }
	single { SavePushUpsCountUseCase(get()) }

	single<TrainingCountRepository> { TrainingCountRepositoryImpl(get()) }
	single<TrainingCountStorage> { TrainingCountStorageImpl(get()) }
}