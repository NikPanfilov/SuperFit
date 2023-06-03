package com.nikpanfilov.superfit.lab2.di

import com.nikpanfilov.network.createRetrofitService
import com.nikpanfilov.shared.body.data.api.BodyParamsHistoryApi
import com.nikpanfilov.shared.body.data.api.PhotoApi
import com.nikpanfilov.shared.body.data.repository.BodyParamsHistoryRepositoryImpl
import com.nikpanfilov.shared.body.data.repository.ProfilePhotoRepositoryImpl
import com.nikpanfilov.shared.body.domain.repository.BodyParamsHistoryRepository
import com.nikpanfilov.shared.body.domain.repository.ProfilePhotoRepository
import com.nikpanfilov.shared.body.domain.usecase.DownloadPhotoUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetLastParamsUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetParamsUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetPhotosUseCase
import com.nikpanfilov.shared.body.domain.usecase.SetBodyParamsUseCase
import com.nikpanfilov.shared.body.domain.usecase.SetPhotoUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val bodyModule = module {
	single { DownloadPhotoUseCase(get()) }
	single { GetLastParamsUseCase(get()) }
	single { GetParamsUseCase(get()) }
	single { GetPhotosUseCase(get()) }
	single { SetBodyParamsUseCase(get()) }
	single { SetPhotoUseCase(get()) }

	single<BodyParamsHistoryRepository> { BodyParamsHistoryRepositoryImpl(get()) }
	single<ProfilePhotoRepository> { ProfilePhotoRepositoryImpl(get()) }

	factory { createRetrofitService<BodyParamsHistoryApi>(get(named(ORIGINAL))) }
	factory { createRetrofitService<PhotoApi>(get(named(ORIGINAL))) }
}