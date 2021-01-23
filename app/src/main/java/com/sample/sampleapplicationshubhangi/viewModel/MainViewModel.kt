package com.sample.sampleapplicationshubhangi.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.sample.sampleapplicationshubhangi.model.MainModel
import com.sample.sampleapplicationshubhangi.networking.GetService
import com.sample.sampleapplicationshubhangi.networking.RetrofitClientInstance
import com.sample.sampleapplicationshubhangi.pojo.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : ViewModel() {

    //Retrofit Work
    private val retrofitClientInstance: RetrofitClientInstance = RetrofitClientInstance()
    private val getDataService =
        retrofitClientInstance.getRetrofitInstance()!!.create(GetService::class.java)
    //Retrofit Work

    val mainModel = MainModel()
    fun onClickSearch() {
        mainModel.toShowProgress.postValue(true)

        val url = "?apikey=b9bd48a6&t=" + mainModel.etSearchData.value
        val call = getDataService.getMovieList(url)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieResponse: MovieResponse? = response.body()
                if (movieResponse != null) {
                    mainModel.noDataFound.postValue(false)
                    mainModel.rvVisibility.postValue(true)
                    mainModel.toShowProgress.postValue(false)


                    val movieResponseList = ArrayList<MovieResponse>()
                    if (response.body()!!.getResponse().equals("True")) {
                        movieResponseList.add(response.body()!!)
                        mainModel.movieListResponse.apply {
                            value = movieResponseList
                        }
                    }
                } else {
                    mainModel.noDataFound.postValue(true)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("TAG", t.toString())
                mainModel.rvVisibility.postValue(false)
                mainModel.toShowProgress.postValue(false)

            }
        })
    }
}