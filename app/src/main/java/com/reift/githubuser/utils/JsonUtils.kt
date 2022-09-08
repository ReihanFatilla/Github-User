package com.reift.githubuser.utils

import android.content.Context
import com.reift.githubuser.model.User
import org.json.JSONObject

object JsonUtils {
    fun getGithubUser(ctx: Context): List<User> {
        val result = ArrayList<User>()
        val json = JSONObject(ctx.resources.assets.open("githubuser.json").bufferedReader().readText())
        val jsonArray = json.getJSONArray("users")

        for(i in 0 until jsonArray.length()){
            val jsonObjectData = jsonArray.getJSONObject(i)
            val user = User(
                jsonObjectData.getString("username"),
                jsonObjectData.getString("name"),
                jsonObjectData.getString("avatar"),
                jsonObjectData.getString("company"),
                jsonObjectData.getString("location"),
                jsonObjectData.getInt("repository"),
                jsonObjectData.getInt("follower"),
                jsonObjectData.getInt("following"),
            )
            result.add(user)
        }
        return result
    }
}