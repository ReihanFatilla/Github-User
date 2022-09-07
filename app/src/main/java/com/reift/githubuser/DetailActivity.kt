package com.reift.githubuser

import android.content.Intent
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

        }
    }

    private fun setUpDetailView(user: User?) {

            binding.apply {
                if (user != null){

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


                        btnShare.setOnClickListener {
                            val textValue = "This is ${user.name}'s GitHub Profile\n" +
                                    "Username: ${user.username}\n" +
                                    "Company: ${user.company}\n" +
                                    "Location: ${user.location}\n" +
                                    "Total ${user.repository} Repositories\n" +
                                    "${user.follower} Followers & ${user.following} Following"

                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, textValue)
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, getString(R.string.txt_share_msg))
                            startActivity(shareIntent)
                        }
                    }
                }
                btnBack.setOnClickListener {
                    finish()
                }
            }

    }

    companion object{
        const val EXTRA_DETAIL = "INTENT_EXTRA_DETAIL"
    }
}