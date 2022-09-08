package com.reift.githubuser.utils

import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.model.User

object Utils {
    fun getShareMessage(user: DetailResponse): String{
        return "This is ${user.name}'s GitHub Profile\n" +
                "Username: ${user.location}\n" +
                "Company: ${user.company}\n" +
                "Location: ${user.location}\n" +
                "Total ${user.publicRepos} Repositories\n" +
                "${user.followers} Followers & ${user.following} Following"
    }

    val listRandomName = arrayListOf(
        "Reihan",
        "Richard",
        "Naufal",
        "Fathan",
        "Devin",
        "Reyhan",
        "Saleh",
        "Alif",
        "Azzam",
        "Danish",
        "Haydar",
        "Miqdad"
    ).shuffled()[0]

}