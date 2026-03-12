package com.example.dashboardbe.dto

data class PostResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val writer: String
)
