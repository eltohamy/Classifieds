package com.dubizzle.classifieds.domain.presentation.classifieddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dubizzle.classifieds.databinding.ClassifiedDetailsFragmentBinding
import com.dubizzle.classifieds.domain.presentation.base.BaseActivity
import com.dubizzle.classifieds.domain.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.dubizzle.classifieds.R

@AndroidEntryPoint
class ClassifiedDetailsFragment : BaseFragment() {

    private lateinit var binding: ClassifiedDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ClassifiedDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.supportActionBar?.setHomeButtonEnabled(true)
            it.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
            val args = ClassifiedDetailsFragmentArgs.fromBundle(requireArguments())
            binding.classified = args.classified
            it.supportActionBar?.title = args.classified.name
        }
    }
}