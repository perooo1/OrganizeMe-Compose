package com.plenart.organizeme_compose.data.database.model

import com.plenart.organizeme_compose.model.User

data class DbUser(
    val id: String,
    val name: String,
    val email: String,
    val image: String,
    val mobile: Long = 0L,
    var selected: Boolean = false
){
    fun toUser(): User = User(
        id = id,
        name = name,
        email = email,
        image = image,
        mobile = mobile,
        selected = selected
    )
}
