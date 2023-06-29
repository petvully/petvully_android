package org.e1i4.petvully.data.remote.model

import com.google.gson.annotations.SerializedName

data class QuestRemote (
    @SerializedName("id")
    val id:Int,
    @SerializedName("userId")
    val user:UserRemote?=null,
    @SerializedName("questDate")
    val date:String,
    @SerializedName("water")
    val water: Boolean,
    @SerializedName("food")
    val food: Boolean,
    @SerializedName("walk")
    val walk: Boolean,
    @SerializedName("shower")
    val shower: Boolean,
    @SerializedName("touch")
    val touch: Boolean

)