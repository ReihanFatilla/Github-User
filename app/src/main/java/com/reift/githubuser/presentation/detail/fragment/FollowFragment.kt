package com.reift.githubuser.presentation.detail.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.core.constant.Constant
import com.reift.core.domain.entity.detail.Follow
import com.reift.githubuser.databinding.FragmentFollowBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.detail.DetailViewModel
import com.reift.githubuser.presentation.detail.fragment.adapter.FollowAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import org.koin.android.viewmodel.ext.android.viewModel


class FollowFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding as FragmentFollowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
                    viewModel.getUserFollowers(username).observe(viewLifecycleOwner) {
                        setUpFollowersRV(it)
                        if(it.isNullOrEmpty()) showLoading(true) else showLoading(false)
                    }
                }
                Constant.TYPE_FOLLOWING -> {
                    viewModel.getUserFollowing(username).observe(viewLifecycleOwner) {
                        setUpFollowingRV(it)
                        if(it.isNullOrEmpty()) showLoading(true) else showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(loading: Boolean){
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvFollow.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvFollow.visibility = View.VISIBLE
        }
    }

    private fun setUpFollowingRV(following: List<Follow>?) {
        if (following != null) {
            binding.rvFollow.apply {
                val mAdapter = FollowAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setData(following)

                mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                    override fun onItemClicked(username: String, position: Int) {
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra(Constant.EXTRA_DETAIL, username)
                                .putExtra(Constant.EXTRA_IS_ONLINE, true)
                        )
                    }
                })
            }
        }
    }

    private fun setUpFollowersRV(followers: List<Follow>?) {
        if (followers != null) {
            binding.rvFollow.apply {
                val mAdapter = FollowAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setData(followers)

                mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                    override fun onItemClicked(username: String, position: Int) {
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra(Constant.EXTRA_DETAIL, username)
                                .putExtra(Constant.EXTRA_IS_ONLINE, true)
                        )
                    }
                })
            }
        }
    }
}