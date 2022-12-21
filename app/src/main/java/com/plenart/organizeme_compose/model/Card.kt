package com.plenart.organizeme_compose.model

data class Card(
    val name: String,
    val createdBy: String,
    val assignedTo: List<String>,
    val labelColor: String,
    val dueDate: Long = 0L
)
