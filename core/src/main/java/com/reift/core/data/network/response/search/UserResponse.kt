package com.reift.core.data.network.response.search

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("items")
	val items: List<UserResponseItem>

)