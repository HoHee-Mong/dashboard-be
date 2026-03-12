package com.example.dashboardbe.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/api/aa")
    fun hellotest(): String {
        return "백엔드 서버 켜기 성공! 스스로 생각하는 개발자가 되자!"
    }
}