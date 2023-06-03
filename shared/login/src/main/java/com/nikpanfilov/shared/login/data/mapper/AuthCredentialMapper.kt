package com.nikpanfilov.signin.data.mapper

import com.nikpanfilov.shared.login.data.dto.AuthCredentialDto
import com.nikpanfilov.shared.login.domain.entity.AuthCredential

internal fun AuthCredential.toDto() = AuthCredentialDto(email = email, password = password)