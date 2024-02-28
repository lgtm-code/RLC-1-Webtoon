package com.rlc.webtoon.config

import com.rlc.webtoon.dto.common.ResponseResult
import com.rlc.webtoon.util.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class WebInterceptor(
    private val jwtUtil: JwtUtil,
): HandlerInterceptor {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorization: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        //accessToken 검증
        if(authorization != null){
            if(!jwtUtil.isValidAccessToken(authorization)) {
                response.apply {
                    contentType = MediaType.APPLICATION_JSON.toString()
                    characterEncoding = Charsets.UTF_8.toString()
                    writer.write(
                        ResponseResult(
                            code = "403",
                            result = "accessToken이 유효하지 않습니다."
                        ).toString()
                    )

                    status = HttpStatus.FORBIDDEN.value()
                }
                return false
            }
        }


        return super.preHandle(request, response, handler)
    }

}