package com.nikpanfilov.superfit.crunch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.crunch.databinding.FragmentCrunchBinding
import com.nikpanfilov.superfit.crunch.presentation.CrunchEvent
import com.nikpanfilov.superfit.crunch.presentation.CrunchState
import com.nikpanfilov.superfit.crunch.presentation.CrunchViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class CrunchFragment : Fragment() {

	companion object {

		fun newInstance() = CrunchFragment()
	}

	private val binding by lazy { FragmentCrunchBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<CrunchViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		viewModel.uiState.onEach {
			if (it is CrunchState.Content) {
				binding.countTextView.text = it.left.toString()
				if (it.left <= 0)
					viewModel.handle(CrunchEvent.Finish)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)
		binding.finishButton.setOnClickListener { viewModel.handle(CrunchEvent.Finish) }

		return binding.root
	}
}