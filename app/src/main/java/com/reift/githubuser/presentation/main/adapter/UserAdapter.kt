package com.reift.githubuser.presentation.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.data.network.response.search.UserItem
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.databinding.ItemGithubUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listUser = ArrayList<UserItem>()

    fun setData(list: List<UserItem>){
        listUser.clear()
        listUser.addAll(list)
    }

    class UserViewHolder(val binding: ItemGithubUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                with(listUser[position]){
                    tvName.text = login
                    tvGithubLink.text = htmlUrl.drop(8)
                    Glide.with(imgUser.context)
                        .load(avatarUrl)
                        .override(300, 300)
                        .into(imgUser)
                }
            }

            itemView.apply {
                setOnClickListener {
                    context.startActivity(
                        Intent(context, DetailActivity::class.java)
                            .putExtra(Constant.EXTRA_DETAIL, listUser[position].login)
                    )
                }
            }
        }
    }

    override fun getItemCount() = listUser.size
}