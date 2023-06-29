package org.e1i4.petvully.data.remote.model

import com.google.gson.annotations.SerializedName

data class PetRemote (
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("feature")
    val feature: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("gender")
    val gender: String
)