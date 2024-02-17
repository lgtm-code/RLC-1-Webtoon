package com.rlc.webtoon.dto.request

//TODO 유효성 검증
data class UserRequestDto constructor(
    val id: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val birth: String,
){

    companion object {
        fun fixture(
            id: String = "test@test.com",
            password: String = "password",
            email: String = "test@test.com",
            phoneNumber: String = "01012345678"
        ): UserRequestDto {
            return UserRequestDto(
                id = id,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                birth = "00000101"
            )
        }
    }
}
