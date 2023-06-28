package org.e1i4.petvully.data.remote.model

import com.google.gson.annotations.SerializedName

data class APIResponse<T> (
    @SerializedName("data")
    val data:T?,
    @SerializedName("statusCode")
    val statusCode:Int,
    @SerializedName("msg")
    val msg:String,
)