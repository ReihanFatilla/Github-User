package com.reift.core.utils

import android.util.Log
import com.reift.core.data.local.room.UserEntity
import com.reift.core.data.remote.response.detail.DetailResponse
import com.reift.core.data.remote.response.follow.FollowResponse
import com.reift.core.data.remote.response.search.UserResponse
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.entity.search.Search
import com.reift.core.domain.entity.search.SearchItem

object DataMapper {
    fun mapDetailResponseToDomain(input: DetailResponse): Detail {
        return with(input) {
            Detail(
                id,
                followingUrl,
                login,
                company,
                publicRepos,
                htmlUrl,
                gravatarId,
                followersUrl,
                followers,
                avatarUrl,
                following,
                name,
                location
            )
        }
    }

    fun mapDomainToEntity(input: FollowUser): UserEntity {
        return with(input) {
            UserEntity(
                id,
                login,
                company,
                publicRepos,
                htmlUrl,
                followers,
                avatarUrl,
                following,
                name,
                location
            )
        }
    }

    fun mapSearchResponseToDomain(input: UserResponse): Search {
        return Search(
            input.items.map {
                with(it) {
                    SearchItem(
                        login, avatarUrl, htmlUrl
                    )
                }
            }
        )
    }

    fun mapFollowResponseToDomain(input: List<FollowResponse>): List<Follow> {
        val domain = ArrayList<Follow>()
        input.map {
            val follow = Follow(
                it.login,
                it.avatarUrl
            )
            domain.add(follow)
        }
        return domain
    }

    fun mapListFollowEntityToDomain(input: List<UserEntity>): List<FollowUser> {
        val domain = ArrayList<FollowUser>()
        input.map {
            val follow = FollowUser(
                it.id,
                it.login,
                it.company,
                it.publicRepos,
                it.htmlUrl,
                it.followers,
                it.avatarUrl,
                it.following,
                it.name,
                it.location
            )
            domain.add(follow)
        }
        return domain
    }

    fun mapFollowEntityToDomain(input: UserEntity): FollowUser {
        Log.i("mapFollowEntToDomainA", "before result: $input")
        return with(input) {
            FollowUser(
                id,
                login,
                company,
                publicRepos,
                htmlUrl,
                followers,
                avatarUrl,
                following,
                name,
                location
            )
        }
    }

//    fun mapFollowUserResponseToDomain(input: DetailResponse, id: Int): FollowUser{
//        return with(input){
//            FollowUser(
//                id,
//                login,
//                company.toString(),
//                publicRepos,
//                htmlUrl,
//                followers,
//                avatarUrl,
//                following,
//                name.toString(),
//                location.toString()
//            )
//        }
//    }

    fun mapDetailDomainToEntity(
        input: Detail,
        id: Int
    ): FollowUser {
        return with(input) {
            FollowUser(
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
//
    fun mapEntityToResponse(input: UserEntity): DetailResponse {
        return with(input) {
            DetailResponse(
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