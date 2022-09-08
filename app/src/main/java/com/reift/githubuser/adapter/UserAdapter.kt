package com.reift.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.databinding.ItemGithubUserBinding
import com.reift.githubuser.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var listUser = ArrayList<User>()

    fun setData(list: List<User>){
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
                    tvName.text = name
                    tvUsername.text = username
                    Glide.with(imgUser.context)
                        .load(avatar)
                        .into(imgUser)
                }
            }

            itemView.apply {
                setOnClickListener {
                    context.startActivity(
                        Intent(context, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DETAIL, listUser[position])
                    )
                }
            }
        }
    }

    override fun getItemCount() = listUser.size
}