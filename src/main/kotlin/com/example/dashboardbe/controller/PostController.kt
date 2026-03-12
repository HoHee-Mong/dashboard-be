package com.example.dashboardbe.controller

import com.example.dashboardbe.dto.PostRequestDto
import com.example.dashboardbe.dto.PostResponseDto
import com.example.dashboardbe.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {

    @GetMapping("/api/posts")
    fun getAllPosts(): List<PostResponseDto> {
        // 주방장(Service)에게 요리를 시키고, 완성된 접시들(List)을 그대로 손님에게 전달!
        return postService.getAllPosts()
    }

    // ▼ 새롭게 추가할 Create (게시글 등록 API) ▼
    @PostMapping("/api/posts")
    fun createPost(@RequestBody requestDto: PostRequestDto): ResponseEntity<Long> {
        // 1. 주방장(Service)에게 주문서(DTO)를 넘겨주며 요리를 부탁합니다.
        val savedPostId = postService.createPost(requestDto)

        // 2. 요리가 완성되면, 손님에게 "201 Created(잘 생성됨!)" 상태 코드와 함께 완성된 글 번호를 돌려줍니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPostId)
    }
}