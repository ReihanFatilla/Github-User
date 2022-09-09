package com.reift.githubuser.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.databinding.ActivityMainBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.main.adapter.UserAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import com.reift.githubuser.utils.Utils

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setUpSearchView()
        setUpRecyclerView()
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
        viewModel.userResponse.observe(this@MainActivity) {
            if(!it.isNullOrEmpty()){
                showLoading(false)
                binding.rvGithubUser.apply {
                    val mAdapter = UserAdapter()
                    mAdapter.setData(it)
                    layoutManager = LinearLayoutManager(applicationContext)
                    adapter = mAdapter
                    setHasFixedSize(true)

                    mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                        override fun onItemClicked(username: String) {
                            startActivity(
                                Intent(applicationContext, DetailActivity::class.java)
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