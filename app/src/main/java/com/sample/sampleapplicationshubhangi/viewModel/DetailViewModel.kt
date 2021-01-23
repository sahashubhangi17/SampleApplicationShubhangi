package com.sample.sampleapplicationshubhangi.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.sample.sampleapplicationshubhangi.model.DetailModel
import com.sample.sampleapplicationshubhangi.model.MainModel
import com.sample.sampleapplicationshubhangi.networking.GetService
import com.sample.sampleapplicationshubhangi.networking.RetrofitClientInstance
import com.sample.sampleapplicationshubhangi.pojo.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {

    //Retrofit Work
    private val retrofitClientInstance: RetrofitClientInstance = RetrofitClientInstance()
    private val getDataService =
        retrofitClientInstance.getRetrofitInstance()!!.create(GetService::class.java)
    //Retrofit Work

    val detailModel = DetailModel()

    fun getMovieDetail(imdbId: String) {
        detailModel.toShowProgress.postValue(true)

        val url = "?apikey=b9bd48a6&i=" + imdbId
        val call = getDataService.getMovieList(url)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieResponse: MovieResponse? = response.body()
                if (movieResponse != null) {
                    detailModel.toShowProgress.postValue(false)
                    detailModel.movieTitle.postValue(movieResponse.getTitle())
                    detailModel.movieBanner.postValue(movieResponse.getPoster())
                    detailModel.movieYear.postValue(movieResponse.getYear())

                    detailModel.movieGenre.postValue("Genre - " + movieResponse.getGenre())
                    detailModel.movieTime.postValue("Time - " + movieResponse.getRuntime())
                    detailModel.movieRating.postValue("IMDB Rating - " + movieResponse.getImdbRating())
                    detailModel.movieSynopsis.postValue(movieResponse.getPlot())
                    detailModel.movieScore.postValue("Score - " + movieResponse.getMetascore())
                    detailModel.movieReview.postValue("IMDB Votes - " + movieResponse.getImdbVotes())
                    detailModel.movieDirector.postValue("Director - " + movieResponse.getDirector())
                    detailModel.movieWriter.postValue("Writer - " + movieResponse.getWriter())
                    detailModel.movieActor.postValue("Actors - " + movieResponse.getActors())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("TAG", t.toString())
                detailModel.toShowProgress.postValue(false)
            }
        })
    }
}