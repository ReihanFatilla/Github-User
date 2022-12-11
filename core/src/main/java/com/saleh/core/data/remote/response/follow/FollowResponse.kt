package com.saleh.core.data.remote.response.follow

import com.google.gson.annotations.SerializedName

data class FollowResponse(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String
)