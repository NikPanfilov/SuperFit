package com.nikpanfilov.superfit.mybody.ui

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.shared.body.UNDEFINED
import com.nikpanfilov.superfit.extensions.toDate
import com.nikpanfilov.superfit.mybody.databinding.EditHeightDialogBinding
import com.nikpanfilov.superfit.mybody.databinding.EditWeightDialogBinding
import com.nikpanfilov.superfit.mybody.databinding.FragmentMyBodyBinding
import com.nikpanfilov.superfit.mybody.presentation.MyBodyEvent
import com.nikpanfilov.superfit.mybody.presentation.MyBodyState
import com.nikpanfilov.superfit.mybody.presentation.MyBodyViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyBodyFragment : Fragment() {

	companion object {

		private const val MIN_PARAM = 9
		private const val MAX_PARAM = 301
		private const val REQUEST_GALLERY = 1
		private const val REQUEST_CAMERA = 2
		private const val REQUEST_PERMISSIONS = 3

		fun newInstance() = MyBodyFragment()
	}

	private val binding by lazy { FragmentMyBodyBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<MyBodyViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		viewModel.uiState.onEach {
			if (it is MyBodyState.Content) {
				if (it.body != null && it.body.weight != UNDEFINED)
					binding.weightTextView.text = it.body.weight.toString()

				if (it.body != null && it.body.height != UNDEFINED)
					binding.heightTextView.text = it.body.height.toString()

				if (it.firstImage != null) {
					binding.firstImageView.setImageBitmap(it.firstImage)
					binding.lastImageView.setImageBitmap(it.lastImage!!)
					binding.firstDateTextView.text = it.firstImageTimeStamp.toDate()
					binding.lastDateTextView.text = it.lastImageTimeStamp.toDate()
				}
//				binding.seeAllButton.isVisible = !it.firstImage.isNullOrEmpty()
//				binding.firstDateTextView.isVisible = !it.firstImage.isNullOrEmpty()
//				binding.lastDateTextView.isVisible = !it.firstImage.isNullOrEmpty()
			}
		}.launchIn(viewLifecycleOwner.lifecycleScope)

		binding.editWeightButton.setOnClickListener { showWeightDialog() }
		binding.editHeightButton.setOnClickListener { showHeightDialog() }
		binding.seeAllButton.setOnClickListener { viewModel.handle(MyBodyEvent.NavigateToImageList) }
		binding.toTrainProgressButton.setOnClickListener { viewModel.handle(MyBodyEvent.NavigateToTrainProgress) }
		binding.toStatisticsButton.setOnClickListener { viewModel.handle(MyBodyEvent.NavigateToStatistics) }
		binding.addImageButton.setOnClickListener { galleryLauncher.launch("image/*") }

		return binding.root
	}

	private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
		uri?.let { selectedImageUri ->
			val inputStream = requireContext().contentResolver.openInputStream(selectedImageUri)
			val image = inputStream?.readBytes()
			inputStream?.close()
			if (image != null)
				viewModel.handle(MyBodyEvent.AddPhoto(image))
		}
	}

	private fun showWeightDialog() {
		val editDialogBinding = EditWeightDialogBinding.inflate(layoutInflater)
		val dialogBuilder = AlertDialog.Builder(this.requireContext()).setView(editDialogBinding.root)
		val alertDialog = dialogBuilder.create()
		alertDialog.show()

		editDialogBinding.cancelButton.setOnClickListener {
			alertDialog.dismiss()
		}
		editDialogBinding.changeButton.setOnClickListener {
			val text = editDialogBinding.valueEditText.text.toString()
			if (text.isNotEmpty() && text.isDigitsOnly() && text.toInt() > MIN_PARAM && text.toInt() < MAX_PARAM) {
				viewModel.handle(MyBodyEvent.EditWeight(text.toInt()))
			}
			alertDialog.dismiss()
		}
	}

	private fun showHeightDialog() {
		val editDialogBinding = EditHeightDialogBinding.inflate(layoutInflater)
		val dialogBuilder = AlertDialog.Builder(this.requireContext()).setView(editDialogBinding.root)
		val alertDialog = dialogBuilder.create()
		alertDialog.show()

		editDialogBinding.cancelButton.setOnClickListener {
			alertDialog.dismiss()
		}
		editDialogBinding.changeButton.setOnClickListener {
			val text = editDialogBinding.valueEditText.text.toString()
			if (text.isNotEmpty() && text.isDigitsOnly() && text.toInt() > MIN_PARAM && text.toInt() < MAX_PARAM)
				viewModel.handle(MyBodyEvent.EditHeight(text.toInt()))
			alertDialog.dismiss()
		}
	}
}