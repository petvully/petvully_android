package org.e1i4.petvully.data.remote.model

import java.io.Serializable

data class DonationGoods (
    val imageUrl:String,
    val category:String,
    val name:String,
    val price:Int
):Serializable