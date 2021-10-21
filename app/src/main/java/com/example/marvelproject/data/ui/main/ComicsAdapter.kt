package com.example.marvelproject.data.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.R
import com.example.marvelproject.data.model.ComicsItem

class ComicsAdapter(
    private val comics: ArrayList<ComicsItem>,
    private val fragmentManager: FragmentManager?
) :
    RecyclerView.Adapter<ComicsAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View, private val fragmentManager: FragmentManager?) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(comicsItem: ComicsItem) {
            itemView.apply {

                val textComicsName = this.findViewById<TextView>(R.id.comics_name)
                textComicsName.text = comicsItem.comicsName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comics_item, parent, false),
            fragmentManager
        )

    override fun getItemCount(): Int = comics.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(comics[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addComics(comics: List<ComicsItem>?) {
        this.comics.clear()
        if (comics != null) {
            this.comics.addAll(comics)
        }
        notifyDataSetChanged()
    }
}