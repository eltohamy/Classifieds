package com.dubizzle.classifieds.domain.presentation.classifiedslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.classifieds.R
import com.dubizzle.classifieds.databinding.ItemClassifiedBinding
import com.dubizzle.classifieds.domain.models.getclassifieds.Result
import com.dubizzle.classifieds.domain.presentation.classifiedslist.fragments.ClassifiedsListFragmentDirections
import com.dubizzle.classifieds.domain.presentation.classifiedslist.viewmodels.ClassifiedViewModel
import com.dubizzle.classifieds.domain.presentation.interfaces.OnClassifiedClickListener

class ClassifiedsListAdapter : RecyclerView.Adapter<ClassifiedsListAdapter.ViewHolder>() {
    private lateinit var classifiedList: ArrayList<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemClassifiedBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_classified,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(classifiedList[position])
    }

    override fun getItemCount(): Int {
        return if (::classifiedList.isInitialized) classifiedList.size else 0
    }

    fun updateClassifiedsList(classifides: List<Result>) {
        this.classifiedList = ArrayList()
        this.classifiedList.addAll(classifides)
        notifyDataSetChanged()
    }

    fun addClassifiedsList(classifides: List<Result>) {
        this.classifiedList.addAll(classifides)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemClassifiedBinding) :
        RecyclerView.ViewHolder(binding.root),
        OnClassifiedClickListener {
        private val viewModel = ClassifiedViewModel()

        fun bind(classified: Result) {
            viewModel.bind(classified)
            binding.viewModel = viewModel
            binding.listener = this
        }

        override fun onClassifiedClick(view: View, viewModel: ClassifiedViewModel) {
            val directions =
                ClassifiedsListFragmentDirections.actionClassifiedsListFragmentToClassifiedDetailsFragment(
                    viewModel.getClassified()
                )
            view.findNavController().navigate(directions)
        }
    }
}