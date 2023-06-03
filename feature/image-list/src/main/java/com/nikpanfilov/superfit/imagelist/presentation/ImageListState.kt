package com.nikpanfilov.superfit.imagelist.presentation

sealed class ImageListState {
	object Initial : ImageListState()

	data class Content(val data: List<ListItem>) : ImageListState()
}