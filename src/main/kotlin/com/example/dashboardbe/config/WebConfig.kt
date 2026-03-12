package com.example.dashboardbe.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration // "스프링부트야, 서버 켜질 때 이 설정 파일 꼭 읽어!" 라는 명찰
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 백엔드의 모든 API 주소(/**)에 대하여
            .allowedOrigins("http://localhost:5173") // 이 프론트엔드 주소(5173)의 접근을 허락한다!
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허락할 HTTP 통신 방법들 (CRUD 전체)
            .allowedHeaders("*") // 어떤 형태의 헤더 데이터든 다 받아주겠다
            .allowCredentials(true) // 나중에 로그인(쿠키/세션) 같은 인증 정보도 주고받을 수 있게 허락!
    }
}