package com.nikpanfilov.superfit.trainprogress.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.superfit.trainprogress.R
import com.nikpanfilov.superfit.trainprogress.databinding.FragmentTrainProgressBinding
import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressEvent
import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressState
import com.nikpanfilov.superfit.trainprogress.presentation.TrainProgressViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainProgressFragment : Fragment() {
	companion object {

		fun newInstance() = TrainProgressFragment()
	}

	private val binding by lazy { FragmentTrainProgressBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<TrainProgressViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.backButton.setOnClickListener { viewModel.handle(TrainProgressEvent.NavigateBack) }
		viewModel.uiState.onEach {
			if (it is TrainProgressState.Content) {
				binding.pushupsLastTextView.setLast(R.string.last_times, it.trainings[TrainingType.PUSH_UP]?.last)
				binding.pushupsProgressTextView.setProgress(it.trainings[TrainingType.PUSH_UP]?.last)

				binding.plankLastTextView.setLast(R.string.last_seconds, it.trainings[TrainingType.PLANK]?.last)
				binding.plankProgressTextView.setProgress(it.trainings[TrainingType.PLANK]?.last)

				binding.crunchLastTextView.setLast(R.string.last_times, it.trainings[TrainingType.CRUNCH]?.last)
				binding.crunchProgressTextView.setProgress(it.trainings[TrainingType.CRUNCH]?.last)

				binding.squatsLastTextView.setLast(R.string.last_times, it.trainings[TrainingType.SQUATS]?.last)
				binding.squatsProgressTextView.setProgress(it.trainings[TrainingType.SQUATS]?.last)

				binding.runningLastTextView.setLast(R.string.last_meters, it.trainings[TrainingType.RUNNING]?.last)
				binding.runningProgressTextView.setProgress(it.trainings[TrainingType.RUNNING]?.last)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		return binding.root
	}

	private fun TextView.setLast(pattern: Int, count: Int?) {
		this.text = getString(pattern, count?.toString() ?: getString(R.string.undefined))
	}

	private fun TextView.setProgress(count: Int?) {
		if (count != null && count != 0) {
			this.text = getString(R.string.progress, count)

			val icon = if (count < 0) ContextCompat.getDrawable(this.context, R.drawable.ic_negative)
			else ContextCompat.getDrawable(this.context, R.drawable.ic_positive)

			this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null)
		} else {
			this.visibility = View.GONE
		}
	}
}