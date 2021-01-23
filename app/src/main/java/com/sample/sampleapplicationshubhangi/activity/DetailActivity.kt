package com.sample.sampleapplicationshubhangi.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.sample.sampleapplicationshubhangi.R
import com.sample.sampleapplicationshubhangi.databinding.ActivityDetailBinding
import com.sample.sampleapplicationshubhangi.util.CommonApi
import com.sample.sampleapplicationshubhangi.viewModel.DetailViewModel
import com.sample.sampleapplicationshubhangi.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private val commonApi = CommonApi()
    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        detailViewModel = DetailViewModel(application)
        super.onCreate(savedInstanceState)
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        activityDetailBinding.detailViewModel = detailViewModel
        activityDetailBinding.lifecycleOwner = this

        try {
            val getImdbId = intent.getStringExtra("imdbId")
            detailViewModel.getMovieDetail(getImdbId)
        } catch (e: Exception) {
        }

        detailViewModel.detailModel.toShowProgress.observe(this, Observer {
            if (it) {
                commonApi.showProgressDialog("Loading..", this)
            } else {
                commonApi.dismissProgressDialog()
            }
        })

        detailViewModel.detailModel.movieBanner.observe(this, Observer {
            if (it.isNotEmpty() && it != "NA") {
                Glide.with(this)
                    .load(detailViewModel.detailModel.movieBanner.value)
                    .into(ivImage)
            }
        })


    }
}