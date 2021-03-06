package com.example.clean_architecture.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.core_android.base.BaseViewModel
import com.example.clean_architecture.core_lib.extension.defaultEmpty
import com.example.clean_architecture.domain.usecase.SearchReposUseCase
import com.example.clean_architecture.presentation.feature.main.mapper.RepoItemMapper
import com.example.clean_architecture.presentation.feature.main.model.RepoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel(
    private val searchReposUseCase: SearchReposUseCase,
    private val repoItemMapper: RepoItemMapper
) : BaseViewModel() {

    private var searchJob: Job? = null

    val repoItem = MutableLiveData<List<RepoItem>>(listOf())
    val isLoading = MutableLiveData<Boolean>(false)
    val query = MutableLiveData<String>("")

    val isNoResults: LiveData<Boolean> = Transformations.map(isLoading) { isLoading ->
        repoItem.value.isNullOrEmpty() && !query.value.isNullOrEmpty() && !isLoading
    }

    fun searchRepos() {
        val query = query.value.defaultEmpty().toLowerCase(Locale.getDefault()).trim()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                isLoading.value = true
                val resultWrapper = withContext(Dispatchers.IO) {
                    searchReposUseCase.execute(SearchReposUseCase.Params(query))
                }
                resultWrapper.subscribe(
                    success = { repos ->
                        repoItem.value = repoItemMapper.mapList(repos)
                        isLoading.value = false
                    },
                    error = { exception ->
                        handleNetworkError(exception)
                        isLoading.value = false
                    }
                )
            }
        }
    }
}