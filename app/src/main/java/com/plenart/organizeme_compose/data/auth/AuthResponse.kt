package com.plenart.organizeme_compose.data.auth

sealed class AuthResponse<out T> {
    object Loading : AuthResponse<Nothing>()
    data class Success<T>(val data: T) : AuthResponse<T>()
    data class Error(val message: String) : AuthResponse<Nothing>()
}
