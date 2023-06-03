package com.nikpanfilov.superfit.exercises.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikpanfilov.shared.trainings.trainings
import com.nikpanfilov.superfit.exercises.databinding.FragmentExercisesBinding
import com.nikpanfilov.superfit.exercises.presentation.ExercisesEvent
import com.nikpanfilov.superfit.exercises.presentation.ExercisesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExercisesFragment : Fragment() {

	companion object {

		fun newInstance() = ExercisesFragment()
	}

	private val binding by lazy { FragmentExercisesBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ExercisesViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val adapter = TrainingsAdapter(viewModel::handle)
		binding.exercisesRecyclerView.adapter = adapter
		adapter.data = trainings.keys.toList()

		binding.backButton.setOnClickListener { viewModel.handle(ExercisesEvent.NavigateBack) }

		return binding.root
	}
}