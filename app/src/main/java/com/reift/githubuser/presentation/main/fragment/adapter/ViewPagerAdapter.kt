package com.reift.githubuser.presentation.main.fragment.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reift.githubuser.constant.Constant
import com.reift.githubuser.presentation.main.fragment.FollowFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        val followingFragment = FollowFragment()
        val followingBundle = Bundle()
        followingBundle.putString(Constant.BUNDLE_FOLLOW, Constant.TYPE_FOLLOWERS)
        followingFragment.arguments = followingBundle

        val followersFragment = FollowFragment()
        val followersBundle = Bundle()
        followersBundle.putString(Constant.BUNDLE_FOLLOW, Constant.TYPE_FOLLOWERS)
        followersFragment.arguments = followersBundle

        return when(position) {
            0 -> followersFragment
            else -> followingFragment
        }
    }

}