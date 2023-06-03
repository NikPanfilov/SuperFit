package com.nikpanfilov.superfit.extensions

import androidx.activity.addCallback
import androidx.fragment.app.Fragment

fun Fragment.setOnBackPressedListener(listener: () -> Unit) {
	requireActivity()
		.onBackPressedDispatcher
		.addCallback(viewLifecycleOwner) {
			listener()
		}
}