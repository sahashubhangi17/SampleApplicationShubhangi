package com.sample.sampleapplicationshubhangi.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Rating {
    @SerializedName("Source")
    @Expose
    private var source: String? = null

    @SerializedName("Value")
    @Expose
    private var value: String? = null

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String?) {
        this.source = source
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String?) {
        this.value = value
    }

}