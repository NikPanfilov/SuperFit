package com.nikpanfilov.superfit.imagelist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.body.domain.usecase.DownloadPhotoUseCase
import com.nikpanfilov.shared.body.domain.usecase.GetPhotosUseCase
import com.nikpanfilov.superfit.extensions.toDate
import com.nikpanfilov.superfit.extensions.toMonthAndYear
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ImageListViewModel(
	private val router: ImageListRouter,
	private val getPhotosUseCase: GetPhotosUseCase,
	private val downloadPhotoUseCase: DownloadPhotoUseCase
) : ViewModel() {

	val uiState = MutableStateFlow<ImageListState>(ImageListState.Initial)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler {}

	init {
		viewModelScope.launch(sendErrorHandler) {
			val images = getPhotosUseCase().sortedByDescending { it.uploaded }
			val map = mutableMapOf<String, MutableList<ListItem.PhotoItem>>()
			images.forEach { image ->
				val date = image.uploaded.toMonthAndYear()
				downloadPhotoUseCase(image.id)?.let {
					if (map.keys.contains(date))
						map[date]!!.add(ListItem.PhotoItem(it, image.uploaded.toDate()))
					else {
						map[date] = mutableListOf(ListItem.PhotoItem(it, image.uploaded.toDate()))
					}
				}
			}

			val data = mutableListOf<ListItem>()
			map.keys.sortedByDescending { it }.forEach {
				data.add(ListItem.DateItem(it))
				map[it]!!.forEach { image ->
					data.add(image)
				}
			}

			uiState.value = ImageListState.Content(data)
		}
	}

	fun handle(event: ImageListEvent) {
		when (event) {
			ImageListEvent.NavigateBack       -> router.navigateBack()
			is ImageListEvent.NavigateToImage -> router.navigateToImage(event.image, event.date)
		}
	}
}