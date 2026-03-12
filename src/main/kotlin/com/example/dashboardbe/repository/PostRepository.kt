package com.example.dashboardbe.repository

import com.example.dashboardbe.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>