package com.nikpanfilov.superfit.image.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikpanfilov.superfit.image.databinding.FragmentImageBinding
import com.nikpanfilov.superfit.image.presentation.ImageEvent
import com.nikpanfilov.superfit.image.presentation.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageFragment : Fragment() {

	companion object {

		private const val IMAGE = "image"
		private const val DATE = "date"
		fun newInstance(image: Bitmap, date: String) = ImageFragment().apply {
			this.image = image
			this.date = date
			arguments = bundleOf(IMAGE to image, DATE to date)
		}
	}

	private var image: Bitmap? = null
	private var date = ""

	private val binding by lazy { FragmentImageBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ImageViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.backButton.setOnClickListener { viewModel.handle(ImageEvent.NavigateBack) }
		binding.imageView.setImageBitmap(image)
		binding.dateTextView.text = date

		return binding.root
	}
}