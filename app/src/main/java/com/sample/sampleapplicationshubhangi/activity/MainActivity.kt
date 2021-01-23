package com.sample.sampleapplicationshubhangi.activity

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.sampleapplicationshubhangi.R
import com.sample.sampleapplicationshubhangi.adapter.MainListAdapter
import com.sample.sampleapplicationshubhangi.databinding.ActivityMainBinding
import com.sample.sampleapplicationshubhangi.util.CommonApi
import com.sample.sampleapplicationshubhangi.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private val commonApi = CommonApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel = MainViewModel(application)
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainViewModel = mainViewModel
        activityMainBinding.lifecycleOwner = this

        mainViewModel.mainModel.movieListResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                val mainListAdapter =
                    MainListAdapter(mainViewModel.mainModel.movieListResponse.value!!, this)
                rvList.layoutManager = GridLayoutManager(this, 2)
                rvList.adapter = mainListAdapter
            }
        })

        mainViewModel.mainModel.toShowProgress.observe(this, Observer {
            if (it) {
                commonApi.showProgressDialog("Please wait..", this)
            } else {
                commonApi.dismissProgressDialog()
            }
        })
        mainViewModel.mainModel.noDataFound.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "No Data Found !", Toast.LENGTH_LONG).show()
            }
        })
    }
}