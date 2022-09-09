package com.reift.githubuser.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reift.githubuser.R
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.databinding.ActivityMainBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.home.HomeViewModel
import com.reift.githubuser.presentation.home.adapter.UserAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import com.reift.githubuser.utils.Utils

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragmemt_container)
        binding.bottomNavBar.setupWithNavController(navController)
    }

}