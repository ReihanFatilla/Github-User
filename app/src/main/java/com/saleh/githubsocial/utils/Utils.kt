package com.saleh.githubsocial.utils

import com.saleh.core.domain.entity.detail.Detail

object Utils {
    fun getShareMessage(user: Detail): String{
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