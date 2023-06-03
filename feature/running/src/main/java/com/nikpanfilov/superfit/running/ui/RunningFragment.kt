package com.nikpanfilov.superfit.running.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.extensions.setOnBackPressedListener
import com.nikpanfilov.superfit.running.databinding.FragmentRunningBinding
import com.nikpanfilov.superfit.running.presentation.RunningEvent
import com.nikpanfilov.superfit.running.presentation.RunningState
import com.nikpanfilov.superfit.running.presentation.RunningViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RunningFragment : Fragment() {

	companion object {

		fun newInstance() = RunningFragment()
	}

	private val binding by lazy { FragmentRunningBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<RunningViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		setOnBackPressedListener { viewModel.handle(RunningEvent.Finish) }
		viewModel.uiState.onEach {
			if (it is RunningState.Content) {
				binding.countTextView.text = it.left.toString()
				if (it.left <= 0)
					viewModel.handle(RunningEvent.Finish)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)
		binding.finishButton.setOnClickListener { viewModel.handle(RunningEvent.Finish) }

		return binding.root
	}
}