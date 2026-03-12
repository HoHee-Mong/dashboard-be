package com.example.dashboardbe.service

import com.example.dashboardbe.domain.Post
import com.example.dashboardbe.dto.PostRequestDto
import com.example.dashboardbe.dto.PostResponseDto
import com.example.dashboardbe.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {

    // 게시글 전체 목록 조회 (Read)
    fun getAllPosts(): List<PostResponseDto> {
        // 1. 창고(DB)에서 모든 식재료(Entity)를 다 꺼내옵니다.
        val posts: List<Post> = postRepository.findAll()

        // 2. 꺼내온 식재료(Entity)들을 손님상에 나갈 예쁜 접시(DTO)로 하나씩 옮겨 담습니다.
        return posts.map { post ->
            PostResponseDto(
                id = post.id!!,
                title = post.title,
                content = post.content,
                writer = post.writer
            )
        }
    }

    // ▼ 새롭게 추가할 Create (게시글 작성) 함수 ▼
    fun createPost(requestDto: PostRequestDto): Long {
        // 1. 프론트엔드에서 날아온 주문서(DTO)의 내용을 보고, 실제 저장할 식재료(Entity)를 조립합니다.
        val newPost = Post(
            title = requestDto.title,
            content = requestDto.content,
            writer = requestDto.writer
        )

        // 2. 창고 관리자에게 "이 조립된 식재료 좀 창고(DB)에 저장해 줘!" 하고 넘깁니다.
        val savedPost = postRepository.save(newPost)

        // 3. 저장이 무사히 끝나면, 데이터베이스가 부여해 준 고유 번호(id)를 돌려줍니다.
        return savedPost.id!!
    }
}