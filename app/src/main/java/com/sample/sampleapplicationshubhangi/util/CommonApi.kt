package com.sample.sampleapplicationshubhangi.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.sample.sampleapplicationshubhangi.R

open class CommonApi {
    lateinit var progressDialog: ProgressDialog

    fun openNewScreen(activity: Activity, cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(
            activity.applicationContext, cls
        )
        if (bundle != null)
            intent.putExtras(bundle)
        activity.startActivity(intent)
        activity.overridePendingTransition(
            android.R.anim.slide_in_left, android.R.anim.slide_out_right
        )
    }


    fun showProgressDialog(message: String, activity: Activity) {
        progressDialog = ProgressDialog(activity, R.style.AppCompatAlertDialogStyle)
        progressDialog.setMessage(message)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
    }


    fun dismissProgressDialog() {
        try {
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
                progressDialog.cancel()
                progressDialog.hide()
            }
        } catch (e: Exception) {
        }
    }
}