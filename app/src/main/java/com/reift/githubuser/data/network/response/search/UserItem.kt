package com.reift.githubuser.data.network.response.search

import com.google.gson.annotations.SerializedName

data class UserItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String
)