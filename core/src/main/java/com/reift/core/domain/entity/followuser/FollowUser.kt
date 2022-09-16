package com.reift.core.domain.entity.followuser

data class FollowUser (
    val id: Int,
    val login: String,
    val company: String,
    val publicRepos: Int,
    val htmlUrl: String,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String,
    val location: String
)