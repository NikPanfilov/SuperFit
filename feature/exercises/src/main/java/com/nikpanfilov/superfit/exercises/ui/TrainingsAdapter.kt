package com.nikpanfilov.superfit.exercises.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikpanfilov.shared.trainings.TrainingType
import com.nikpanfilov.shared.trainings.trainings
import com.nikpanfilov.superfit.exercises.databinding.ExercisesItemBinding
import com.nikpanfilov.superfit.exercises.presentation.ExercisesEvent

class TrainingsAdapter(private val toExercise: (ExercisesEvent.NavigateToExercise) -> Unit) : RecyclerView.Adapter<TrainingsAdapter.TrainingViewHolder>() {

	var data: List<TrainingType> = emptyList()
		set(newValue) {
			field = newValue
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ExercisesItemBinding.inflate(inflater, parent, false)

		return TrainingViewHolder(binding)
	}

	override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
		with(holder.binding) {
			val card = trainings[data[position]]
			if (card != null) {
				trainingImageView.setImageResource(card.image)
				titleTextView.setText(card.title)
				descriptionTextView.setText(card.text)

				root.setOnClickListener { toExercise(ExercisesEvent.NavigateToExercise(data[position])) }
			}
		}
	}

	override fun getItemCount() = data.size

	class TrainingViewHolder(val binding: ExercisesItemBinding) : RecyclerView.ViewHolder(binding.root)
}