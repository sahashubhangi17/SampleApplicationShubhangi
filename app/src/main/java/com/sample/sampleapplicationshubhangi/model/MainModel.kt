package com.sample.sampleapplicationshubhangi.model

import androidx.lifecycle.MutableLiveData
import com.sample.sampleapplicationshubhangi.pojo.MovieResponse

class MainModel {

    var noDataFound = MutableLiveData<Boolean>().apply { value = false }
    var rvVisibility = MutableLiveData<Boolean>().apply { value = false }
    var toShowProgress = MutableLiveData<Boolean>().apply { value = false }
    var etSearchData = MutableLiveData<String>().apply { value = "" }
    val movieListResponse = MutableLiveData<ArrayList<MovieResponse>>().apply {
        value = ArrayList()
    }
}