package com.reift.githubuser.presentation.detail.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.databinding.ItemFollowBinding
import com.reift.githubuser.utils.OnItemClickCallback

class FollowAdapter: RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {

    private var listFollow = ArrayList<FollowResponse>()

    private var onItemClickCallBack: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    fun setData(list: List<FollowResponse>){
        listFollow.clear()
        listFollow.addAll(list)
    }

    class FollowViewHolder(val binding: ItemFollowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FollowViewHolder(ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        with(listFollow[position]) {
            holder.apply {
                binding.apply {

                    tvName.text = login
                    com.bumptech.glide.Glide.with(imgUser.context)
                        .load(avatarUrl)
                        .override(300, 300)
                        .into(imgUser)
                }

                itemView.setOnClickListener {
                    onItemClickCallBack?.onItemClicked(login, position)
                }
            }
        }
    }

    override fun getItemCount() = listFollow.size
}