package com.reift.githubuser.utils

import com.reift.core.domain.entity.detail.Detail
import com.reift.core.domain.entity.detail.Follow
import com.reift.core.domain.entity.followuser.FollowUser

object Dummy {
    fun generateDetail(): Detail{
        return Detail(
            id = 88997868,
            followingUrl = "https://api.github.com/users/ReihanFatilla/following{/other_user}",
            login = "ReihanFatilla",
            company = "IDN Boarding School",
            publicRepos = 60,
            htmlUrl = "https://github.com/ReihanFatilla",
            gravatarId = "",
            followersUrl = "https://api.github.com/users/ReihanFatilla/followers",
            followers = 32,
            avatarUrl = "https://avatars.githubusercontent.com/u/88997868?v=4",
            following = 45,
            name = "Reihan Fatilla",
            location = "Palu, Indonesia"
        )
    }

    fun generateFollow(): List<Follow>{
        val listFollow = arrayListOf<Follow>()
        for(i in 1..10){
            val follow = Follow(
                login = "ReihanFatilla",
                avatarUrl = "https://avatars.githubusercontent.com/u/88997868?v=4",
            )
            listFollow.add(follow)
        }
        return listFollow
    }

    fun generateUser(): FollowUser {
        return FollowUser(
            id = 88997868,
            login = "ReihanFatilla",
            company = "IDN Boarding School",
            publicRepos = 60,
            htmlUrl = "https://github.com/ReihanFatilla",
            followers = 32,
            avatarUrl = "https://avatars.githubusercontent.com/u/88997868?v=4",
            following = 45,
            name = "Reihan Fatilla",
            location = "Palu, Indonesia"
        )

    }
}