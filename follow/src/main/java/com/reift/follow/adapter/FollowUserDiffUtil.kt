package com.reift.follow.adapter

import androidx.recyclerview.widget.DiffUtil
import com.reift.core.domain.entity.followuser.FollowUser

class FollowUserDiffUtil(private val oldList: List<FollowUser>, private val newList: List<FollowUser>)
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