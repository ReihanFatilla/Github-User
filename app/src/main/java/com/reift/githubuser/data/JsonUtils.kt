package com.reift.githubuser.data

import android.content.Context
import com.reift.githubuser.R
import com.reift.githubuser.model.User
import org.json.JSONArray
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