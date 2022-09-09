package com.reift.githubuser.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.databinding.FragmentHomeBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.home.adapter.UserAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import com.reift.githubuser.utils.Utils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        _viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setUpSearchView()
        setUpRecyclerView()

        return binding.root
    }

    private fun setUpSearchView() {
        val randomName = Utils.listRandomName
        var searchResultMsg = "Search result for \"$randomName\""

        viewModel.searchByUsername(randomName)
        binding.tvSearchResult.text = searchResultMsg

        binding.svGithubUser.apply{
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.searchByUsername(query)
                        clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        showLoading(true)
                        searchResultMsg = "Search result for \"$newText\""
                        binding.tvSearchResult.text = searchResultMsg
                    }
                    return true
                }
            })
        }
    }

    private fun setUpRecyclerView() {
        viewModel.userResponse.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                showLoading(false)
                binding.rvGithubUser.apply {
                    val mAdapter = UserAdapter()
                    mAdapter.setData(it)
                    layoutManager = LinearLayoutManager(context)
                    adapter = mAdapter
                    setHasFixedSize(true)

                    mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                        override fun onItemClicked(username: String) {
                            startActivity(
                                Intent(context, DetailActivity::class.java)
                                    .putExtra(Constant.EXTRA_DETAIL, username)
                            )
                        }
                    })

                }
            } else {
                showLoading(true)
                val errorSearchMsg = "No result for \"${binding.svGithubUser.query}\""
                binding.tvSearchResult.text = errorSearchMsg
            }
        }
    }

    private fun showLoading(loading: Boolean){
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvGithubUser.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvGithubUser.visibility = View.VISIBLE
        }
    }
}