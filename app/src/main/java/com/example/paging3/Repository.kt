package com.example.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3.api.GitHubService
import com.example.paging3.model.Repo
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 10

    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(gitHubService) },
        ).flow
    }
}