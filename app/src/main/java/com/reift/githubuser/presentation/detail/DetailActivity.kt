package com.reift.githubuser.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.githubuser.R
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.databinding.ActivityDetailBinding
import com.reift.githubuser.model.User
import com.reift.githubuser.presentation.detail.fragment.adapter.ViewPagerAdapter
import com.reift.githubuser.utils.Utils

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel!!

    private var _user: DetailResponse? = null
    private val user get() = _user!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        viewModel.getUserDetail(intent.getStringExtra(Constant.EXTRA_DETAIL).toString())
        viewModel.detailResponse.observe(this){
            _user = it
            setUpDetailView()
            setUpViewPager()
        }

        setUpToolbar()
    }

    private fun setUpViewPager() {
        binding.viewpager.adapter = ViewPagerAdapter(this, user.login)
        TabLayoutMediator(binding.tabFollow, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.followers)
                1 -> tab.text = getString(R.string.following)
            }
        }.attach()
    }

    private fun setUpToolbar() {
        binding.toolbarDetail.apply {
            setNavigationIcon(R.drawable.ic_back)
            setSupportActionBar(this)
        }
    }

    private fun setUpDetailView() {
        binding.apply {
            with(user) {
                tvName.text = name
                tvUsername.text = login
                tvLocation.text = if(location.isNullOrEmpty()) "none" else location
                tvCompany.text = if(company.isNullOrEmpty()) "none" else company
                tvUserFollowers.text = followers.toString()
                tvUserFollowing.text = following.toString()
                tvUserRepository.text = publicRepos.toString()

                Glide.with(applicationContext)
                    .load(avatarUrl)
                    .into(imgUser)

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, Utils.getShareMessage(user))
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(sendIntent, getString(R.string.txt_share_msg))
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}