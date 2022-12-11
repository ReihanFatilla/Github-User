package com.reift.core.data.remote.response.search

import com.google.gson.annotations.SerializedName

data class UserResponseItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String
)