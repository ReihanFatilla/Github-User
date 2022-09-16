package com.reift.githubuser.presentation.followuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.core.constant.Constant
import com.reift.githubuser.databinding.FragmentFollowingBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.followuser.adapter.FollowUserAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import org.koin.android.viewmodel.ext.android.viewModel


class FollowUserFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding as FragmentFollowingBinding

    private val viewModel: FollowUserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        viewModel.getFollowList().observe(viewLifecycleOwner){
            binding.rvFollowingList.apply {
                val mAdapter = FollowUserAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                Log.i("setUpRecyclerViewAa", "setUpRecyclerView: $it")
                mAdapter.setData(it)
                mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                    override fun onItemClicked(username: String, position: Int) {
                        startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra(Constant.EXTRA_DETAIL, username)
                                .putExtra(Constant.EXTRA_IS_ONLINE, true)
                        )
//                        if(isOnline()){
//                            startActivity(
//                                Intent(context, DetailActivity::class.java)
//                                    .putExtra(Constant.EXTRA_DETAIL, username)
//                                    .putExtra(Constant.EXTRA_IS_ONLINE, true)
//                            )
//                        }
//                        else if(!isOnline()){
//                            startActivity(
//                                Intent(context, DetailActivity::class.java)
//                                    .putExtra(Constant.EXTRA_DETAIL_OBJECT, it[position])
//                                    .putExtra(Constant.EXTRA_IS_ONLINE, !isOnline())
//                            )
//                        }
                    }
                })
            }
        }
    }

//    fun isOnline(): Boolean {
//        val connectivityManager =
//            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            } else {
//                return false
//            }
//        if (capabilities != null) {
//            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                return true
//            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                return true
//            }
//        }
//        return false
//    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

}