package com.reift.core.utils

import androidx.recyclerview.widget.DiffUtil

class UserDiffUtil(private val oldList: List<com.reift.core.data.network.response.search.UserItem>, private val newList: List<com.reift.core.data.network.response.search.UserItem>)
    : DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val mOldList = oldList[oldItemPosition]
        val mNewList = newList[newItemPosition]
        return mOldList.login == mNewList.login
                && mOldList.avatarUrl == mNewList.avatarUrl
                && mOldList.htmlUrl == mNewList.htmlUrl
    }
}