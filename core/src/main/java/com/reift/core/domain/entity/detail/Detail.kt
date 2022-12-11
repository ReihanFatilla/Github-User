package com.reift.core.domain.entity.detail

import com.google.gson.annotations.SerializedName

data class Detail(
	val id: Int,
	val followingUrl: String,
	val login: String,
	val company: String? = "none",
	val publicRepos: Int,
	val htmlUrl: String,
	val gravatarId: String,
	val followersUrl: String,
	val followers: Int,
	val avatarUrl: String,
	val following: Int,
	val name: String? = "username",
	val location: String? = "none"
)