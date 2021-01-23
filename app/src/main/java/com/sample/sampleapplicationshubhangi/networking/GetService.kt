package com.sample.sampleapplicationshubhangi.networking

import com.sample.sampleapplicationshubhangi.pojo.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface GetService {

    @GET
    fun getMovieList(@Url url: String?): Call<MovieResponse>


}