package com.reift.githubuser.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.data.network.response.follow.FollowResponse
import com.reift.githubuser.databinding.FragmentFollowBinding
import com.reift.githubuser.presentation.detail.DetailViewModel
import com.reift.githubuser.presentation.detail.fragment.adapter.FollowAdapter

class FollowFragment : Fragment() {

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel!!

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        setUpRecyclerView()

        _binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setUpRecyclerView() {
        val type = arguments?.getString(Constant.BUNDLE_FOLLOW)
        val username = arguments?.getString(Constant.BUNDLE_USERNAME)

        if (type != null && username != null) {
            when (type) {
                Constant.TYPE_FOLLOWERS -> {
                    viewModel.getUserFollowers(username)

                    viewModel.followersResponse.observe(viewLifecycleOwner) {
                        setUpFollowersRV(it)
                    }
                }
                Constant.TYPE_FOLLOWING -> {
                    viewModel.getUserFollowing(username)

                    viewModel.followingResponse.observe(viewLifecycleOwner) {
                        setUpFollowingRV(it)
                    }
                }
            }
        }
    }

    private fun setUpFollowingRV(following: List<FollowResponse>?) {
        if (following != null) {
            binding.rvFollow.apply {
                val mAdapter = FollowAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setData(following)
            }
        }
    }

    private fun setUpFollowersRV(followers: List<FollowResponse>?) {
        if (followers != null) {
            binding.rvFollow.apply {
                val mAdapter = FollowAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setData(followers)
            }
        }
    }
}