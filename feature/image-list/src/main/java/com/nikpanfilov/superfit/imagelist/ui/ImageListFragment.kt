package com.nikpanfilov.superfit.imagelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.imagelist.databinding.FragmentImageListBinding
import com.nikpanfilov.superfit.imagelist.presentation.ImageListEvent
import com.nikpanfilov.superfit.imagelist.presentation.ImageListState
import com.nikpanfilov.superfit.imagelist.presentation.ImageListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageListFragment : Fragment() {

	companion object {

		fun newInstance() = ImageListFragment()
	}

	private val binding by lazy { FragmentImageListBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ImageListViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.backButton.setOnClickListener { viewModel.handle(ImageListEvent.NavigateBack) }
		val adapter = ListAdapter(viewModel::handle)
		binding.recyclerView.adapter = adapter

		viewModel.uiState.onEach {
			if (it is ImageListState.Content) {
				adapter.data = it.data
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		return binding.root
	}
}