package com.reift.githubuser.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reift.githubuser.data.network.response.search.UserItem
import com.reift.githubuser.databinding.ItemGithubUserBinding
import com.reift.githubuser.utils.OnItemClickCallback

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<UserItem>()

    private var onItemClickCallBack: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    fun setData(list: List<UserItem>) {
        listUser.clear()
        listUser.addAll(list)
    }

    class UserViewHolder(val binding: ItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            ItemGithubUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(listUser[position]) {
            holder.apply {
                binding.apply {
                    tvName.text = login
                    tvGithubLink.text = htmlUrl.drop(8)
                    Glide.with(imgUser.context)
                        .load(avatarUrl)
                        .override(300, 300)
                        .into(imgUser)
                }

                itemView.setOnClickListener {
                    onItemClickCallBack?.onItemClicked(login)
                }
            }
        }
    }

    override fun getItemCount() = listUser.size
}