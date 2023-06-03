package com.nikpanfilov.superfit.mybody.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.body.UNDEFINED
import com.nikpanfilov.shared.body.domain.entity.Body
import com.nikpanfilov.shared.body.domain.usecase.DownloadPhotoUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetLastParamsUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetPhotosUseCase
import com.nikpanfilov.shared.body.domain.usecase.SetBodyParamsUseCase
import com.nikpanfilov.shared.body.domain.usecase.SetPhotoUseCase
import com.nikpanfilov.superfit.extensions.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyBodyViewModel(
	private val router: MyBodyRouter,
	private val getLastBodyParamsUseCase: GetLastParamsUseCase,
	private val setBodyParamsUseCase: SetBodyParamsUseCase,
	private val getPhotosUseCase: GetPhotosUseCase,
	private val downloadPhotoUseCase: DownloadPhotoUseCase,
	private val setPhotoUseCase: SetPhotoUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<MyBodyState>(MyBodyState.Initial)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		getData()
	}

	fun handle(event: MyBodyEvent) {
		when (event) {
			is MyBodyEvent.AddPhoto             -> setPhoto(event.photo)
			MyBodyEvent.NavigateToImageList     -> router.navigateToImageList()
			MyBodyEvent.NavigateToStatistics    -> router.navigateToStatistics()
			MyBodyEvent.NavigateToTrainProgress -> router.navigateToTrainProgress()
			is MyBodyEvent.EditHeight           -> setHeight(event.height)
			is MyBodyEvent.EditWeight           -> setWeight(event.weight)
		}
	}

	private fun setPhoto(photo: ByteArray) {
		viewModelScope.launch(sendErrorHandler) {
			setPhotoUseCase(photo)
			getData()
		}
	}

	private fun setHeight(height: Int) {
		viewModelScope.launch(sendErrorHandler) {
			val state = uiState.value as? MyBodyState.Content ?: return@launch
			val weight = state.body?.weight ?: UNDEFINED
			setBodyParamsUseCase(Body(weight = weight, height = height, date = getCurrentDate()))
			getData()
		}
	}

	private fun setWeight(weight: Int) {
		viewModelScope.launch(sendErrorHandler) {
			val state = uiState.value as? MyBodyState.Content ?: return@launch
			val height = state.body?.height ?: UNDEFINED
			setBodyParamsUseCase(Body(weight = weight, height = height, date = getCurrentDate()))
			getData()
		}
	}

	private fun getData() {
		viewModelScope.launch(sendErrorHandler) {
			val bodyParams = getLastBodyParamsUseCase()
			val photos = getPhotosUseCase().sortedBy { it.uploaded }

			if (photos.isEmpty())
				uiState.value = MyBodyState.Content(
					body = bodyParams,
					firstImage = null,
					lastImage = null,
					firstImageTimeStamp = 0,
					lastImageTimeStamp = 0
				)
			else
				uiState.value = MyBodyState.Content(
					body = bodyParams,
					firstImage = downloadPhotoUseCase(photos.first().id),
					lastImage = downloadPhotoUseCase(photos.last().id),
					firstImageTimeStamp = photos.first().uploaded,
					lastImageTimeStamp = photos.last().uploaded
				)
		}
	}
}