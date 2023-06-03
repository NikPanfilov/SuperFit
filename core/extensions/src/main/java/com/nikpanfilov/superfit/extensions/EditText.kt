package com.nikpanfilov.superfit.extensions

import android.widget.EditText

inline fun EditText.onTextChanged(action: (String) -> Unit) {
	action(this.text.toString())
}