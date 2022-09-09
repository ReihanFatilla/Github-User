package com.reift.githubuser.presentation.detail.fragment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.databinding.ItemFollowBinding
import com.reift.githubuser.presentation.detail.DetailActivity

class FollowersAdapter: RecyclerView.Adapter<FollowersAdapter.FollowViewHolder>() {

    var listFollowers = ArrayList<FollowResponse>()

    fun setData(list: List<FollowResponse>){
        listFollowers.clear()
        listFollowers.addAll(list)
    }

    class FollowViewHolder(val binding: ItemFollowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowViewHolder(ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                with(listFollowers[position]){
                    tvName.text = login
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
                            .putExtra(Constant.EXTRA_DETAIL, listFollowers[position].login)
                    )
                }
            }
        }
    }

    override fun getItemCount() = listFollowers.size
}