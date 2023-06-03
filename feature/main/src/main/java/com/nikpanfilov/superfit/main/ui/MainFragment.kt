package com.nikpanfilov.superfit.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.shared.body.UNDEFINED
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.superfit.main.databinding.FragmentMainBinding
import com.nikpanfilov.superfit.main.presentation.MainEvent
import com.nikpanfilov.superfit.main.presentation.MainState
import com.nikpanfilov.superfit.main.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

	companion object {

		fun newInstance() = MainFragment()
	}

	private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<MainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.myBodyView.setOnClickListener { viewModel.handle(MainEvent.BodyDetails) }
		binding.seeAllTextView.setOnClickListener { viewModel.handle(MainEvent.SeeAllExercises) }
		binding.signOutButton.setOnClickListener { viewModel.handle(MainEvent.SignOut) }

		val adapter = TrainingsAdapter(viewModel::handle)
		binding.lastExercisesRecyclerView.adapter = adapter

		viewModel.uiState.onEach { state ->
			if (state is MainState.Content) {
				if (state.body != null && state.body.weight != UNDEFINED)
					binding.weightTextView.text = state.body.weight.toString()

				if (state.body != null && state.body.height != UNDEFINED)
					binding.heightTextView.text = state.body.height.toString()

				if (state.trainings.isNotEmpty()) {
					val trainings = state.trainings.sortedByDescending { it.date }.distinctBy { it.exercise.type }.map { it.exercise }
					if (trainings.size == 1)
						adapter.data = trainings
					else
						adapter.data = listOf(trainings[0], trainings[1])
				} else
					adapter.data = listOf(TrainingType.PUSH_UP, TrainingType.PLANK)
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		return binding.root
	}
}