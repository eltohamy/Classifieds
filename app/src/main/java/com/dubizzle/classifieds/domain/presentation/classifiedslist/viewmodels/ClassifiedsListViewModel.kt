package com.dubizzle.classifieds.domain.presentation.classifiedslist.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dubizzle.classifieds.data.network.ResultData
import com.dubizzle.classifieds.domain.models.getclassifieds.ClassifiedsResponse
import com.dubizzle.classifieds.domain.presentation.base.BaseViewModel
import com.dubizzle.classifieds.domain.presentation.classifiedslist.adapters.ClassifiedsListAdapter
import com.dubizzle.classifieds.domain.usecases.GetClassifiedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassifiedsListViewModel @Inject constructor(private val getClassifiedsUseCase: GetClassifiedsUseCase) :
    BaseViewModel() {

    val classifiedsListAdapter = ClassifiedsListAdapter()
    private var classifiedsListResultMutable = MutableLiveData<ResultData<ClassifiedsResponse?>>()
    val classifiedsListResult: LiveData<ResultData<ClassifiedsResponse?>>
        get() = classifiedsListResultMutable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadClassifiedsList()
    }

    init {
        loadClassifiedsList()
    }

    private fun loadClassifiedsList() {
        errorVisibility.value = View.GONE
        classifiedsListResultMutable.value = ResultData.Loading()
        viewModelScope.launch {
            classifiedsListResultMutable.value = getClassifiedsUseCase.getClassifiedsList()
        }
    }
}