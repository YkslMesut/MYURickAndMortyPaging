package com.myu.myurickandmortypaging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myu.myurickandmortypaging.api.ApiService
import com.myu.myurickandmortypaging.model.Result
import java.lang.Exception

class RickAndMortyPagingSource(
    private val apiService: ApiService
) : PagingSource<Int,Result>(){
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllCharacters(currentPage)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}