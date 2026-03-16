package com.example.dashboardbe.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,
    var content: String,
    var writer: String
)   {
    fun update(newTitle: String, newContent: String, newWriter: String) {
        this.title = newTitle
        this.content = newContent
        this.writer = newWriter
    }
}