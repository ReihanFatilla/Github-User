package com.reift.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.reift.githubuser.databinding.ActivityDetailBinding
import com.reift.githubuser.model.User
import com.reift.githubuser.utils.Utils

class DetailActivity : AppCompatActivity() {

    private var _user: User? = null
    private val user get() = _user!!

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _user = intent.getParcelableExtra(EXTRA_DETAIL)

        setUpToolbar()
        setUpDetailView()
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
                tvUsername.text = username
                tvLocation.text = location
                tvCompany.text = company
                tvUserFollowers.text = follower.toString()
                tvUserFollowing.text = following.toString()
                tvUserRepository.text = repository.toString()

                Glide.with(applicationContext)
                    .load(avatar)
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

    companion object {
        const val EXTRA_DETAIL = "INTENT_EXTRA_DETAIL"
    }
}