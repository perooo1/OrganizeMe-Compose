package com.plenart.organizeme_compose.model

data class Board(
    val name: String = "",
    val image: String = "",
    val createdBy: String = "",
    val assignedTo: List<String> = emptyList(),
    var documentID: String = "",
    var taskList: List<Task> = emptyList()
)
