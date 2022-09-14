package com.reift.core.utils

object DataMapper {
    fun mapResponseToEntity(input: com.reift.core.data.remote.response.detail.DetailResponse, id: Int): com.reift.core.data.local.room.UserEntity {
        return with(input){
            com.reift.core.data.local.room.UserEntity(
                id,
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

    fun mapListEntityToResponse(input: List<com.reift.core.data.local.room.UserEntity>): List<com.reift.core.data.remote.response.search.UserResponseItem>{
        return input.map {
            com.reift.core.data.remote.response.search.UserResponseItem(
                it.login,
                it.avatarUrl,
                it.htmlUrl
            )
        }
    }

    fun mapEntityToResponse(input: com.reift.core.data.local.room.UserEntity): com.reift.core.data.remote.response.detail.DetailResponse {
        return with(input){
            com.reift.core.data.remote.response.detail.DetailResponse(
                id,
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