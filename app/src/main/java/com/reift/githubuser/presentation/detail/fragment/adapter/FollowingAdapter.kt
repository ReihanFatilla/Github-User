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

class FollowingAdapter: RecyclerView.Adapter<FollowingAdapter.FollowViewHolder>() {

    var listFollowing = ArrayList<FollowResponse>()

    fun setData(list: List<FollowResponse>){
        listFollowing.clear()
        listFollowing.addAll(list)
    }

    class FollowViewHolder(val binding: ItemFollowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowViewHolder(ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                with(listFollowing[position]){
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
                            .putExtra(Constant.EXTRA_DETAIL, listFollowing[position].login)
                    )
                }
            }
        }
    }

    override fun getItemCount() = listFollowing.size
}