package com.reift.githubuser.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.databinding.ActivityMainBinding
import com.reift.githubuser.presentation.main.adapter.UserAdapter
import com.reift.githubuser.utils.Utils

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel!!

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
                    Log.i("setUpRecyclerViewAsd", "setUpRecyclerView: $it")
                }
            } else showLoading(true)
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