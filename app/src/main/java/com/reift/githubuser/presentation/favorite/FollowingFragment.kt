package com.reift.githubuser.presentation.favorite

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.core.constant.Constant
import com.reift.githubuser.databinding.FragmentFollowingBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.home.adapter.UserAdapter
import com.reift.core.utils.DataMapper
import com.reift.core.utils.OnItemClickCallback


class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding as FragmentFollowingBinding

    private var _viewModel: FollowingViewModel? = null
    private val viewmodel get() = _viewModel as FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[FollowingViewModel::class.java]

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        viewmodel.getFollowList().observe(viewLifecycleOwner){
            binding.rvFollowingList.apply {
                val mAdapter = UserAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                val userItem = DataMapper.mapListEntityToResponse(it)
                mAdapter.setData(userItem)
                mAdapter.setOnItemClickCallback(object : OnItemClickCallback{
                    override fun onItemClicked(username: String, position: Int) {
                        if(isOnline()){
                            startActivity(
                                Intent(context, DetailActivity::class.java)
                                    .putExtra(Constant.EXTRA_DETAIL, username)
                                    .putExtra(Constant.EXTRA_IS_ONLINE, isOnline())
                            )
                        } else if(!isOnline()){
                            startActivity(
                                Intent(context, DetailActivity::class.java)
                                    .putExtra(Constant.EXTRA_DETAIL_OBJECT, it[position])
                                    .putExtra(Constant.EXTRA_IS_ONLINE, !isOnline())
                            )
                        }
                    }
                })
            }
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                return false
            }
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

}