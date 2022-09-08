package com.reift.githubuser.data.network

import com.reift.githubuser.BuildConfig.API_KEY
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.data.network.response.search.UserResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token $API_KEY")
    fun searchByUsername(
        @Query("q")
        username: String
    ): Flowable<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $API_KEY")
    fun getUserDetail(
        @Path("username")
        username: String
    ): Flowable<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $API_KEY")
    fun getUserFollowers(
        @Path("username")
        username: String
    ): Flowable<List<FollowResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $API_KEY")
    fun getUserFollowing(
        @Path("username")
        username: String
    ): Flowable<List<FollowResponse>>

}