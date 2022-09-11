package com.reift.githubuser.utils

import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.response.detail.DetailResponse

object DataMapper {
    fun detailResponseToEntity(input: DetailResponse): UserEntity{
        return with(input){
            UserEntity(
                0,
                login,
                company.toString(),
                publicRepos,
                followers,
                avatarUrl,
                following,
                name.toString(),
                location.toString()
            )
        }
    }
}