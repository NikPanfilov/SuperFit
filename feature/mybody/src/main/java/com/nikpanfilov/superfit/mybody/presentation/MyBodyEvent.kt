package com.nikpanfilov.superfit.mybody.presentation

sealed class MyBodyEvent {

	object NavigateToTrainProgress : MyBodyEvent()
	object NavigateToStatistics : MyBodyEvent()
	object NavigateToImageList : MyBodyEvent()

	data class AddPhoto(val photo: ByteArray) : MyBodyEvent()

	data class EditWeight(val weight: Int) : MyBodyEvent()
	data class EditHeight(val height: Int) : MyBodyEvent()
}
