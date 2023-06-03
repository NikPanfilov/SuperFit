package com.nikpanfilov.shared.login.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.nikpanfilov.shared.login.domain.entity.UserData

class UserDataDataStorageImpl(private val context: Context) : UserDataDataStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val NAME = "name"
		const val PASSWORD = "password"
	}

	private val sharedPreferences = EncryptedSharedPreferences.create(
		SHARED_PREFERENCES_FILENAME,
		MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
		context,
		EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
		EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)

	override fun saveData(data: UserData) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(NAME, data.name)
		e.putString(PASSWORD, data.password)
		e.apply()
	}
}