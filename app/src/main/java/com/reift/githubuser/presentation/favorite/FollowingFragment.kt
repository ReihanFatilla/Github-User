package com.reift.githubuser.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.R
import com.reift.githubuser.databinding.FragmentFollowingBinding
import com.reift.githubuser.presentation.home.adapter.UserAdapter
import com.reift.githubuser.utils.DataMapper

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
            }
        }
    }

}