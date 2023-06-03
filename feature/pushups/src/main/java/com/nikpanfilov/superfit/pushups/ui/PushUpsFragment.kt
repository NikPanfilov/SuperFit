package com.nikpanfilov.superfit.pushups.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.superfit.extensions.setOnBackPressedListener
import com.nikpanfilov.superfit.pushups.databinding.FragmentPushUpsBinding
import com.nikpanfilov.superfit.pushups.presentation.PushUpsEvent
import com.nikpanfilov.superfit.pushups.presentation.PushUpsState
import com.nikpanfilov.superfit.pushups.presentation.PushUpsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PushUpsFragment : Fragment() {

	companion object {

		fun newInstance() = PushUpsFragment()
	}

	private val binding by lazy { FragmentPushUpsBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<PushUpsViewModel>()

	private var accelerometerListener: AccelerometerListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		setOnBackPressedListener {
			accelerometerListener?.stop()
			viewModel.handle(PushUpsEvent.Finish)
		}
		viewModel.uiState.onEach {
			if (it is PushUpsState.Content) {
				binding.countTextView.text = it.left.toString()
				if (it.left <= 0) {
					accelerometerListener?.stop()
				}
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)
		binding.finishButton.setOnClickListener { viewModel.handle(PushUpsEvent.Finish) }

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		accelerometerListener = AccelerometerListener(requireContext(), viewModel)
		accelerometerListener?.start()
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onDetach() {
		accelerometerListener?.stop()
		super.onDetach()
	}
}