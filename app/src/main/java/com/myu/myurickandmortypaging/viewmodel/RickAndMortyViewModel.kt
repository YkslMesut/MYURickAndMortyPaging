package com.myu.myurickandmortypaging.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.myu.myurickandmortypaging.api.ApiService
import com.myu.myurickandmortypaging.paging.RickAndMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel
    @Inject constructor(
        private val api : ApiService
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)){
        RickAndMortyPagingSource(api)
    }.flow.cachedIn(viewModelScope)
}