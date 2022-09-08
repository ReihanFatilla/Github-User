package com.reift.githubuser.utils

import com.reift.githubuser.model.User

object Utils {
    fun getShareMessage(user: User): String{
        return "This is ${user.name}'s GitHub Profile\n" +
                "Username: ${user.username}\n" +
                "Company: ${user.company}\n" +
                "Location: ${user.location}\n" +
                "Total ${user.repository} Repositories\n" +
                "${user.follower} Followers & ${user.following} Following"
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