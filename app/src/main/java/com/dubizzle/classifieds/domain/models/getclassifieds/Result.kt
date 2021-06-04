package com.dubizzle.classifieds.domain.models.getclassifieds

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.dubizzle.classifieds.utility.Constants
import kotlinx.parcelize.Parcelize
import java.text.ParseException

@Parcelize
data class Result(
    val created_at: String?,
    val image_ids: List<String>?,
    val image_urls: List<String>?,
    val image_urls_thumbnails: List<String>?,
    val name: String?,
    val price: String?,
    val uid: String?
) : Parcelable {
    fun getDate(): MutableLiveData<String> {
        val date = MutableLiveData<String>()
        try {
            date.value = Constants.dateFormat1.format(Constants.dateFormat.parse(created_at!!)!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            date.value = ""
        }
        return date
    }
}