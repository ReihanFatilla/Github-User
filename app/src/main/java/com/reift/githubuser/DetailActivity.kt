package com.reift.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.reift.githubuser.databinding.ActivityDetailBinding
import com.reift.githubuser.model.User

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDetail = intent.getParcelableExtra<User>(EXTRA_DETAIL)

        setUpButton()
        setUpDetailView(userDetail)
    }

    private fun setUpButton() {
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun setUpDetailView(user: User?) {
        if (user != null){
            binding.apply {
                with(user){
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
    }

    companion object{
        const val EXTRA_DETAIL = "INTENT_EXTRA_DETAIL"
    }
}