package com.nikpanfilov.shared.login.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenDto(@Json(name = "refresh_token") val refreshToken: String)
