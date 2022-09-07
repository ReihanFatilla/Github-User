package com.reift.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.githubuser.adapter.UserAdapter
import com.reift.githubuser.data.JsonUtils
import com.reift.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvGithubUser.apply {
            val mAdapter = UserAdapter()
            mAdapter.setData(JsonUtils.getGithubUser(applicationContext))
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mAdapter
        }
    }
}