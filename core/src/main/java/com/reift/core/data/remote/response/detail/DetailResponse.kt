package com.reift.core.data.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResponse(
	val id: Int,

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("company")
	val company: String? = "none",

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("gravatar_id")
	val gravatarId: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String? = "username",

	@field:SerializedName("location")
	val location: String? = "none"
)