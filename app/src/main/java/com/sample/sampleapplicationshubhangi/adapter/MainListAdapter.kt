package com.sample.sampleapplicationshubhangi.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.sampleapplicationshubhangi.R
import com.sample.sampleapplicationshubhangi.activity.DetailActivity
import com.sample.sampleapplicationshubhangi.pojo.MovieResponse
import com.sample.sampleapplicationshubhangi.util.CommonApi
import kotlinx.android.synthetic.main.main_list_item_row.view.*

class MainListAdapter(
    var it: ArrayList<MovieResponse>,
    val context: Context
) :
    RecyclerView.Adapter<MainListAdapter.MyViewHolder>() {
    val commonApi = CommonApi()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.main_list_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            holder.tvTitle.text = it[position].getTitle()
            Glide.with(context)
                .load(it[position].getPoster())
                .into(holder.ivBanner)

            holder.rlRoot.setOnClickListener { v ->
                val bundle = Bundle()
                bundle.putString("imdbId", it[holder.adapterPosition].getImdbID())
                commonApi.openNewScreen(context as Activity, DetailActivity::class.java, bundle)
            }

        } catch (e: Exception) {
        }
    }

    override fun getItemCount(): Int {
        return it.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tvTitle
        val ivBanner: ImageView = itemView.ivBanner
        val rlRoot: RelativeLayout = itemView.rlRoot
    }
}
