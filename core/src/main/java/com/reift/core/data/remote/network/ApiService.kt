package com.reift.core.data.remote.network

import com.reift.core.BuildConfig.API_KEY
import com.reift.core.data.remote.response.detail.DetailResponse
import com.reift.core.data.remote.response.follow.FollowResponse
import com.reift.core.data.remote.response.search.UserResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchByUsername(
        @Query("q")
        username: String
    ): Flowable<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username")
        username: String
    ): Flowable<DetailResponse>


    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username")
        username: String
    ): Flowable<List<FollowResponse>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username")
        username: String
    ): Flowable<List<FollowResponse>>

}