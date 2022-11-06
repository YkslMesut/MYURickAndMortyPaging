package com.myu.myurickandmortypaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.myu.myurickandmortypaging.adapter.RickMortyPagerAdapter
import com.myu.myurickandmortypaging.databinding.ActivityMainBinding
import com.myu.myurickandmortypaging.viewmodel.RickAndMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter : RickMortyPagerAdapter
    private val viewModel : RickAndMortyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRv()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv() {
        mAdapter = RickMortyPagerAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}