package com.reift.follow.followuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.core.constant.Constant
import com.reift.githubuser.databinding.FragmentFollowingBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.follow.adapter.FollowUserAdapter
import com.reift.follow.di.useCaseModule
import com.reift.follow.di.viewModelModule
import com.reift.githubuser.utils.OnItemClickCallback
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FollowUserFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding as FragmentFollowingBinding

    private val viewModel: FollowUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)

        loadKoinModules(listOf(useCaseModule, viewModelModule))

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        viewModel.getFollowList().observe(viewLifecycleOwner){
            binding.rvFollowingList.apply {
                val mAdapter = FollowUserAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setData(it)
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

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

}