package com.dubizzle.classifieds.domain.presentation.classifiedslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.classifieds.R
import com.dubizzle.classifieds.data.network.ResultData
import com.dubizzle.classifieds.databinding.ClassifiedsListFragmentBinding
import com.dubizzle.classifieds.domain.models.getclassifieds.ClassifiedsResponse
import com.dubizzle.classifieds.domain.models.getclassifieds.Result
import com.dubizzle.classifieds.domain.presentation.base.BaseActivity
import com.dubizzle.classifieds.domain.presentation.base.BaseFragment
import com.dubizzle.classifieds.domain.presentation.classifiedslist.viewmodels.ClassifiedsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassifiedsListFragment() : BaseFragment() {

    private lateinit var binding: ClassifiedsListFragmentBinding
    private val classifiedsListViewModel: ClassifiedsListViewModel by viewModels()
    private val classifiedsListObserver = Observer<ResultData<ClassifiedsResponse?>> { resultData ->
            when (resultData) {
                is ResultData.Loading -> {
                    showLoading()
                }
                is ResultData.Success -> {
                    hideLoading()
                    val classifiedsListResponse = resultData.data
                    classifiedsListResponse?.let {
                        if (it.results.isNullOrEmpty())
                            showError(R.string.empty_response)
                        else showClassifiedsList(it.results)
                    } ?: run {
                        showError(R.string.empty_response)
                    }
                }
                is ResultData.Failed -> {
                    hideLoading()
                    showError(R.string.error)
                }
                is ResultData.Exception -> {
                    hideLoading()
                    showError(R.string.error)
                }
            }
    }

    private fun showClassifiedsList(results: List<Result>) {
        classifiedsListViewModel.classifiedsListAdapter.updateClassifiedsList(results)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ClassifiedsListFragmentBinding.inflate(inflater)
        binding.viewModel = classifiedsListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        classifiedsListViewModel.classifiedsListResult.observe(
            viewLifecycleOwner,
            classifiedsListObserver
        )
        (activity as BaseActivity).let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            it.supportActionBar?.setHomeButtonEnabled(false)
            it.supportActionBar?.title = getString(R.string.app_name)
        }
    }

    //show progress bar
    private fun showLoading() {
        classifiedsListViewModel.loadingVisibility.value = View.VISIBLE
    }

    //hide progress bar
    private fun hideLoading() {
        classifiedsListViewModel.loadingVisibility.value = View.GONE
    }

    //show error message
    private fun showError(@StringRes errorMessage: Int) {
        classifiedsListViewModel.errorMessage.value = getString(errorMessage)
        classifiedsListViewModel.errorVisibility.value = View.VISIBLE
    }

    //hide error message
    private fun hideError() {
        classifiedsListViewModel.errorVisibility.value = View.GONE
    }
}
