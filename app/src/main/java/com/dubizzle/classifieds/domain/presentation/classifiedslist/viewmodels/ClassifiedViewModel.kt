package com.dubizzle.classifieds.domain.presentation.classifiedslist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dubizzle.classifieds.domain.models.getclassifieds.Result
import com.dubizzle.classifieds.utility.Constants
import java.text.ParseException

class ClassifiedViewModel : ViewModel() {
    private val imageUrl = MutableLiveData<String>()
    private val name = MutableLiveData<String>()
    private val price = MutableLiveData<String>()
    private val date = MutableLiveData<String>()
    private lateinit var classified: Result

    fun bind(classified: Result) {
        this.classified = classified
        this.imageUrl.value = classified.image_urls_thumbnails!![0]
        this.name.value = classified.name
        this.price.value = classified.price
        try {
            this.date.value =
                Constants.dateFormat1.format(Constants.dateFormat.parse(classified.created_at!!)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getPrice(): MutableLiveData<String> {
        return price
    }

    fun getDate(): MutableLiveData<String> {
        return date
    }

    fun getClassified(): Result {
        return classified
    }
}