package com.sample.sampleapplicationshubhangi.model

import androidx.lifecycle.MutableLiveData

class DetailModel {
    var toShowProgress = MutableLiveData<Boolean>().apply { value = false }
    var movieTitle = MutableLiveData<String>().apply { value = "NA" }
    var movieYear = MutableLiveData<String>().apply { value = "NA" }
    var movieGenre = MutableLiveData<String>().apply { value = "NA" }
    var movieTime = MutableLiveData<String>().apply { value = "NA" }
    var movieRating = MutableLiveData<String>().apply { value = "NA" }
    var movieBanner = MutableLiveData<String>().apply { value = "NA" }
    var movieSynopsis = MutableLiveData<String>().apply { value = "NA" }
    var movieScore = MutableLiveData<String>().apply { value = "NA" }
    var movieReview = MutableLiveData<String>().apply { value = "NA" }
    var movieDirector = MutableLiveData<String>().apply { value = "NA" }
    var movieWriter = MutableLiveData<String>().apply { value = "NA" }
    var movieActor = MutableLiveData<String>().apply { value = "NA" }
}