package com.suit.techtest1.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suit.techtest1.Extension.ctx
import com.suit.techtest1.Model.Person
import com.suit.techtest1.R
import kotlinx.android.synthetic.main.recyclerview_guest.view.*
import android.support.v7.widget.GridLayoutManager
import com.suit.techtest1.Extension.setSize


/**
 * Created by Daniel on 1/27/2018.
 */

class RecyclerGuestAdapter(val listPerson: List<Person>,
                    val itemClick: (Person) -> Unit) :
        RecyclerView.Adapter<RecyclerGuestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.recyclerview_guest, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(listPerson[position])
    }

    override fun getItemCount() = listPerson.size

    class ViewHolder(itemView: View, val itemClick: (Person) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bindForecast(person: Person) {
            with(person) {
                itemView.txtName.text = name
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}