package com.reift.githubuser.utils

import androidx.recyclerview.widget.DiffUtil
import com.reift.githubuser.data.network.response.search.UserItem

class UserDiffUtil(private val oldList: List<UserItem>, private val newList: List<UserItem>)
    : DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val OldList = oldList[oldItemPosition]
        val NewList = newList[newItemPosition]
        return OldList.login == NewList.login
                && OldList.avatarUrl == NewList.avatarUrl
                && OldList.htmlUrl == NewList.htmlUrl
    }
}