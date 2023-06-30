package org.e1i4.petvully.data.remote.model

class ResponsePetsInfo : ArrayList<ResponsePetsInfoItem>()

data class ResponsePetsInfoItem(
    val id: Int,
    val userId: UserId,
    val name: String,
    val image: String,
    val kind: String,
    val color: String,
    val age: Int,
    val weight: Int,
    val feature: String,
    val region: String,
    val gender: String
    )

data class UserId(
    val createdDate: String,
    val modifiedDate: String,
    val id: Int,
    val email: String,
    val password: String,
    val phone: String,
    val nickname: String,
    val level: Int,
    val exp: Int,
    val coin: Int,
)