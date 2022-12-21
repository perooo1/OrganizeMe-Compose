package com.plenart.organizeme_compose.model

data class Task(
    var title: String,
    val createdBy: String,
    var cards: List<Card>
)
