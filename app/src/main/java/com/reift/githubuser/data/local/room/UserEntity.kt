package com.reift.githubuser.data.local.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserEntity (

    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,

    @ColumnInfo(name = "followers")
    val followers: Int,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "following")
    val following: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "location")
    val location: String

): Parcelable
