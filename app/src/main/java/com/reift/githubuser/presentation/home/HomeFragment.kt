package com.reift.githubuser.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reift.core.constant.Constant
import com.reift.githubuser.databinding.FragmentHomeBinding
import com.reift.githubuser.presentation.detail.DetailActivity
import com.reift.githubuser.presentation.home.adapter.UserAdapter
import com.reift.githubuser.utils.OnItemClickCallback
import com.reift.githubuser.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()
    private val themeViewModel: ThemeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        setUpSearchView()
        setUpRecyclerView()
        setUpThemeSettings()

        return binding.root
    }

    private fun setUpThemeSettings() {
        binding.switchTheme.apply {
            themeViewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
                isChecked = if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    false
                }
            }

            setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                themeViewModel.saveThemeSetting(isChecked)
            }
        }
    }

    private fun setUpSearchView() {
        val randomName = Utils.listRandomName
        var searchResultMsg = "Search result for \"$randomName\""

        viewModel.searchByUsername(randomName)
        binding.tvSearchResult.text = searchResultMsg

        binding.svGithubUser.apply{
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.searchByUsername(query)
                        clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        showLoading(true)
                        searchResultMsg = "Search result for \"$newText\""
                        binding.tvSearchResult.text = searchResultMsg
                    }
                    return true
                }
            })
        }
    }

    private fun setUpRecyclerView() {
        viewModel.searchResponse.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                showLoading(false)
                binding.rvGithubUser.apply {
                    val mAdapter = UserAdapter()
                    mAdapter.setData(it)
                    layoutManager = LinearLayoutManager(context)
                    adapter = mAdapter
                    setHasFixedSize(true)

                    mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                        override fun onItemClicked(username: String, position: Int) {
                            startActivity(
                                Intent(context, DetailActivity::class.java)
                                    .putExtra(Constant.EXTRA_DETAIL, username)
                                    .putExtra(Constant.EXTRA_IS_ONLINE, true)
                            )
                        }
                    })

                }
            } else {
                showLoading(true)
                val errorSearchMsg = "No result for \"${binding.svGithubUser.query}\""
                binding.tvSearchResult.text = errorSearchMsg
            }
        }
    }

    private fun showLoading(loading: Boolean){
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvGithubUser.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvGithubUser.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}