package com.saleh.core.data.remote.response.search

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("items")
	val items: List<UserResponseItem>

)