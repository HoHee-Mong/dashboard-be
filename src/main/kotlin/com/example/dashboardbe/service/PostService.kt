package com.example.dashboardbe.service

import com.example.dashboardbe.domain.Post
import com.example.dashboardbe.dto.PostRequestDto
import com.example.dashboardbe.dto.PostResponseDto
import com.example.dashboardbe.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    // ▼ 새롭게 추가할 Read One (게시글 상세 조회) 함수 ▼
    fun getPost(id: Long): PostResponseDto {
        // 1. 창고 관리자에게 "이 번호(id)표 가진 식재료(Entity) 하나만 찾아와!" 라고 시킵니다.
        // 만약 못 찾으면(orElseThrow) "그런 게시글 없는데요?" 하고 에러를 던집니다.
        val post = postRepository.findById(id).orElseThrow {
            IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=$id")
        }

        // 2. 무사히 찾았다면, 예쁜 접시(DTO)에 담아서 돌려줍니다.
        return PostResponseDto(
            id = post.id!!,
            title = post.title,
            content = post.content,
            writer = post.writer
        )
    }

    @Transactional // 마법의 명찰! (더티 체킹)
    fun updatePost(id: Long, requestDto: PostRequestDto): PostResponseDto {
        // 1. 창고에서 수정할 식재료(Entity)를 번호(id)로 꺼내옵니다.
        val post = postRepository.findById(id).orElseThrow {
            IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=$id")
        }

        // 2. 식재료에게 "손님이 준 새 주문서(DTO) 내용으로 네 모습 좀 바꿔!" 하고 버튼을 누릅니다.
        post.update(requestDto.title, requestDto.content, requestDto.writer)

        // 3. 수정된 요리를 예쁜 접시(DTO)에 담아서 돌려줍니다.
        return PostResponseDto(
            id = post.id!!,
            title = post.title,
            content = post.content,
            writer = post.writer
        )
    }

    fun deletePost(id: Long) {
        // 1. 일단 버릴 식재료가 창고에 진짜 있는지 번호(id)로 찾아봅니다. (없으면 에러 던지기!)
        val post = postRepository.findById(id).orElseThrow {
            IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=$id")
        }

        // 2. 창고 관리자에게 "이 식재료(post) 창고에서 완전히 지워버려!" 라고 명령합니다.
        postRepository.delete(post)
    }
}