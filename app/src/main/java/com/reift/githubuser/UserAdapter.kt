package com.reift.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reift.githubuser.databinding.ItemGithubUserBinding
import com.reift.githubuser.model.User

class UserAdapter(): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listUser = ArrayList<User>()

    fun setData(list: List<User>){
        listUser.clear()
        listUser.addAll(list)
    }

    class UserViewHolder(val binding: ItemGithubUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            with(listUser[position]){
                tvName.text = name
                tvUsername.text = username
                Glide.with(imgUser.context)
                    .load("https://avatars.githubusercontent.com/u/1?v=4")
                    .into(imgUser)
            }
        }
    }

    override fun getItemCount() = listUser.size
}