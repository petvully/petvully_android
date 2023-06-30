package org.e1i4.petvully.data.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.e1i4.petvully.data.remote.model.PetRemote
import org.e1i4.petvully.data.remote.model.ResponsePetsInfo
import org.e1i4.petvully.data.remote.model.ResponsePetsInfoItem
import org.e1i4.petvully.data.remote.model.UserAndPetRemote
import org.e1i4.petvully.data.remote.model.UserRemote
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PVService {
    /** 퀘스트 */
    @POST("quests/give-water")
    @Headers("Content-type: application/json")
    suspend fun giveWater(
        @Query("userId") userId:Int
    ):UserRemote
    @POST("quests/give-walk")
    suspend fun giveWalk(
        @Field("userId") userId:Int
    ):UserRemote
    @POST("quests/give-touch")
    suspend fun giveTouch(
        @Body userId:Int
    ):UserRemote
    @POST("quests/give-shower")
    suspend fun giveShower(
        @Body userId:Int
    ):UserRemote
    @POST("quests/give-food")
    suspend fun giveFood(
        @Body userId:Int
    ):UserRemote
    @GET("quests/info/{userId}")
    suspend fun questInfo(
        @Path("userId") userId:Int
    ): List<JsonObject>

    /** 유기 동물 */
    @POST("pets/adoption")
    suspend fun petAdoption(
        @Body pet: PetRemote
    ):String
    @GET("pets/mypet/{userId}")
    suspend fun myPet(
        @Path("userId") userId:Int
    ): UserAndPetRemote
    @GET("pets/info")
    suspend fun petList(
    ): ResponsePetsInfo
    @GET("pets/info/{petId}")
    suspend fun petInfo(
        @Path("petId") petId:Int
    ): UserAndPetRemote


    /** 유저 */
    @PUT("users/update")
    suspend fun userUpdate(
        @Body user: UserRemote
    ): UserRemote
    @POST("users/signup")
    suspend fun userSignup(
        @Body user: UserRemote
    ): String
    @POST("users/login")
    suspend fun userLogin(
        @Body user: JsonObject
    )
    @POST("users/emailDup")
    suspend fun emailDup(
        @Body email: String
    ): String
    @GET("users/info/{userId}")
    suspend fun userUpdate(
        @Path("userId") userId: Int
    ): UserRemote

}