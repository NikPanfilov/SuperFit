package com.nikpanfilov.superfit.signup.data.datasource

import com.nikpanfilov.superfit.signup.domain.entity.RegistrationBody

interface SignUpDataSource {

	suspend fun signUp(data: RegistrationBody)
}