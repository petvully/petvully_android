package org.e1i4.petvully.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserRemote (
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("level")
    val level:Int,
    @SerializedName("exp")
    val exp:Int,
    @SerializedName("coin")
    val coin:Int,
    @SerializedName("id")
    val id:Int?=null,
    @SerializedName("createdDate")
    val createdDate:String ?= null,
    @SerializedName("modifiedDate")
    val modifiedDate:String ?= null,
)