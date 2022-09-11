package com.reift.githubuser.utils

import com.reift.githubuser.data.local.room.UserEntity
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.data.network.response.search.UserItem

object DataMapper {
    fun mapResponseToEntity(input: DetailResponse): UserEntity{
        return with(input){
            UserEntity(
                0,
                login,
                company.toString(),
                publicRepos,
                htmlUrl,
                followers,
                avatarUrl,
                following,
                name.toString(),
                location.toString()
            )
        }
    }

    fun mapListEntityToResponse(input: List<UserEntity>): List<UserItem>{
        return input.map {
            UserItem(
                it.login,
                it.avatarUrl,
                it.htmlUrl
            )
        }
    }

    fun mapEntityToResponse(input: UserEntity): DetailResponse{
        return with(input){
            DetailResponse(
                "",
                login,
                company,
                publicRepos,
                htmlUrl,
                "",
                "",
                followers,
                avatarUrl,
                following,
                name,
                location
            )
        }
    }
}