package com.saleh.githubsocial.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.saleh.core.domain.entity.search.SearchItem

class UserDiffUtil(private val oldList: List<SearchItem>, private val newList: List<SearchItem>)
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