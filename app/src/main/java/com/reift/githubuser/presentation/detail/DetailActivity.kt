package com.reift.githubuser.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.core.constant.Constant
import com.reift.core.domain.entity.detail.Detail
import com.reift.core.utils.DataMapper
import com.reift.githubuser.R
import com.reift.githubuser.databinding.ActivityDetailBinding
import com.reift.githubuser.presentation.detail.fragment.adapter.ViewPagerAdapter
import com.reift.githubuser.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    private var _user: Detail? = null
    private val user get() = _user as Detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        if(intent.getBooleanExtra(Constant.EXTRA_IS_ONLINE, true)){
            onlineDetail()
        } else {
//            offlineDetail()
        }

        setUpToolbar()
    }

//    private fun offlineDetail() {
//        val intentUser = intent.getParcelableExtra<com.reift.core.data.local.room.UserEntity>(Constant.EXTRA_DETAIL_OBJECT)
//        _user = intentUser?.let {
//            DataMapper.mapEntityToResponse(it)
//        }
//        setUpDetailView()
//    }

    private fun onlineDetail() {
        viewModel.getUserDetail(intent.getStringExtra(Constant.EXTRA_DETAIL).toString()).observe(this){
            if (it != null && it.avatarUrl.isNotEmpty()){
                showLoading(false)
                _user = it
                setUpDetailView()
                setUpViewPager()
                setUpFollowFeature()
            }
        }
    }

    private fun setUpFollowFeature() {
        viewModel.getIdByUsername(user.login).observe(this){ entity ->
            val isFollowing = entity != null
            if(isFollowing) unFollowButtonMode()

            binding.btnFollow.setOnClickListener {
                if(isFollowing){
                    entity?.let { userDel -> viewModel.deleteFollowing(userDel) }
                    Toast.makeText(applicationContext, "Unfollowed!", Toast.LENGTH_SHORT).show()
                    followButtonMode()
                } else {
                    viewModel.insertFollowing(DataMapper.mapDetailDomainToEntity(user, 0))
                    Toast.makeText(applicationContext, "Followed!", Toast.LENGTH_SHORT).show()
                    unFollowButtonMode()
                }
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