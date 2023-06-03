package com.nikpanfilov.superfit.main.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.nikpanfilov.network.token.data.storage.SharedPrefsDataStorage

class SignOutDataStorageImpl(private val context: Context) : SignOutDataStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val NAME = "name"
		const val ACCESS_TOKEN = "access_token"
		const val REFRESH_TOKEN = "refresh_token"
	}

	private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
		SHARED_PREFERENCES_FILENAME,
		MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
		context,
		EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
		EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun signOut() {
		deleteName()
		deleteTokens()
	}

	private fun deleteName() {
		val e: SharedPreferences.Editor = encryptedSharedPreferences.edit()

		e.putString(NAME, null)
		e.apply()
	}

	private fun deleteTokens() {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(SharedPrefsDataStorage.ACCESS_TOKEN, null)
		e.putString(SharedPrefsDataStorage.REFRESH_TOKEN, null)
		e.apply()
	}
}