package com.reift.core.domain.entity.detail.follow

import com.google.gson.annotations.SerializedName

data class Follow(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String
)
