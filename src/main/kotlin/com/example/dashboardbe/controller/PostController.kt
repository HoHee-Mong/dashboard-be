package com.example.dashboardbe.controller

import com.example.dashboardbe.dto.PostRequestDto
import com.example.dashboardbe.dto.PostResponseDto
import com.example.dashboardbe.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    // ▼ 새롭게 추가할 Read One (게시글 상세 조회 API) ▼
    @GetMapping("/api/posts/{id}")
    fun getPost(@PathVariable id: Long): PostResponseDto {
        // 주소창에서 뽑아낸 번호(id)를 주방장에게 넘겨주고, 완성된 요리를 손님에게 줍니다!
        return postService.getPost(id)
    }

    @PutMapping("/api/posts/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody requestDto: PostRequestDto
    ): PostResponseDto {
        // 주소창의 번호표(id)와, 몸통에 담겨온 새 주문서(requestDto)를 통째로 주방장에게 토스!
        return postService.updatePost(id, requestDto)
    }

    // ▼ 새롭게 추가할 Delete (게시글 삭제 API) ▼
    @DeleteMapping("/api/posts/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> {
        // 1. 주소창에서 빼온 번호표(id)를 주방장에게 주면서 파기하라고 시킵니다.
        postService.deletePost(id)

        // 2. 파기가 완료되면, 프론트엔드에게 "204 No Content(잘 지워졌고, 돌려줄 데이터는 없음!)" 도장을 찍어 보냅니다.
        return ResponseEntity.noContent().build()
    }
}