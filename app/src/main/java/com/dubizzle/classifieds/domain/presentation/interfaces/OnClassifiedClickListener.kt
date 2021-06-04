package com.dubizzle.classifieds.domain.presentation.interfaces

import android.view.View
import com.dubizzle.classifieds.domain.presentation.classifiedslist.viewmodels.ClassifiedViewModel

interface OnClassifiedClickListener {
    fun onClassifiedClick(view: View, viewModel: ClassifiedViewModel)
}