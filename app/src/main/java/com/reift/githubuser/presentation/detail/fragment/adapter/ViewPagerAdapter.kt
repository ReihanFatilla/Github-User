package com.reift.githubuser.presentation.detail.fragment.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.presentation.detail.fragment.FollowFragment

class ViewPagerAdapter(fa: FragmentActivity, private val username: String) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {

        val followingFragment = FollowFragment()
        val followingBundle = Bundle()
        followingBundle.putString(Constant.BUNDLE_FOLLOW, Constant.TYPE_FOLLOWING)
        followingBundle.putString(Constant.BUNDLE_USERNAME, username)
        followingFragment.arguments = followingBundle

        val followersFragment = FollowFragment()
        val followersBundle = Bundle()
        followersBundle.putString(Constant.BUNDLE_FOLLOW, Constant.TYPE_FOLLOWERS)
        followersBundle.putString(Constant.BUNDLE_USERNAME, username)
        followersFragment.arguments = followersBundle

        return when(position) {
            0 -> followersFragment
            else -> followingFragment
        }
    }

}