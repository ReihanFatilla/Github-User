package com.reift.githubuser.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.githubuser.R
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.data.network.response.detail.DetailResponse
import com.reift.githubuser.databinding.ActivityDetailBinding
import com.reift.githubuser.presentation.detail.fragment.adapter.ViewPagerAdapter
import com.reift.githubuser.utils.DataMapper
import com.reift.githubuser.utils.Utils

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel!!

    private var _user: DetailResponse? = null
    private val user get() = _user as DetailResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        viewModel.getUserDetail(intent.getStringExtra(Constant.EXTRA_DETAIL).toString())
        viewModel.detailResponse.observe(this){
            if (it != null && it.avatarUrl.isNotEmpty()){
                showLoading(false)
                _user = it
                setUpDetailView()
                setUpViewPager()
                setUpFollowFeature()
            }
        }

        setUpToolbar()
    }

    private fun setUpFollowFeature() {
        val isFollowing = viewModel.getFollowStatus(user.login)
        if(isFollowing) unFollowButtonMode()

        val userEntity = DataMapper.mapResponseToEntity(user)

        binding.btnFollow.setOnClickListener {
            if(!isFollowing){
                viewModel.insertFollowing(userEntity)
                viewModel.saveFollowingStatus(user.login, true)
            } else if(isFollowing){
                viewModel.deleteFollowing(userEntity)
                viewModel.removeFollowingStatus(user.login)
            }
        }
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
                    .override(500, 500)
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

    private fun showLoading(loading: Boolean){
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.constraintDetail.visibility = View.INVISIBLE
            binding.constraintFollow.visibility = View.INVISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.constraintDetail.visibility = View.VISIBLE
            binding.constraintFollow.visibility = View.VISIBLE
        }
    }

    private fun followButtonMode(){
        binding.apply {
            btnFollow.setCardBackgroundColor(resources.getColor(R.color.primary_color))
            tvFollowStatus.text = resources.getString(R.string.follow)
        }
    }

    private fun unFollowButtonMode(){
        binding.apply {
            btnFollow.setCardBackgroundColor(resources.getColor(R.color.card_background))
            tvFollowStatus.text = resources.getString(R.string.unfollow)
        }
    }
}