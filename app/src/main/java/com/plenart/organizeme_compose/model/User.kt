package com.plenart.organizeme_compose.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val image: String = "",
    val mobile: Long = 0L,
    var selected: Boolean = false
)
