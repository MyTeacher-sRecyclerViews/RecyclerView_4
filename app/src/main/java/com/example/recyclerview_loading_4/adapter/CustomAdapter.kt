package com.example.recyclerview_loading_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_loading_4.R
import com.example.recyclerview_loading_4.listener.OnBottomClickListener
import com.example.recyclerview_loading_4.model.Member


class CustomAdapter(private val members: List<Member>, private val listener: OnBottomClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val YES = 1
    private val NO = 2

    override fun getItemViewType(position: Int): Int {
        val member = members[position]
        return if (member.available) YES else NO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == YES) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_yes_layout, parent, false)
            return CustomYesViewHolder(view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_no_layout, parent, false)
        return CustomNoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == members.size -1) {
            listener.onBottomListener(position)
        }
        val member = members[position]

        if (holder is CustomYesViewHolder) {
            holder.textOne.text = member.firstName
            holder.textTwo.text = member.lastName
        }
        if (holder is CustomNoViewHolder) {
            holder.textOne.setText(R.string.firNo)
            holder.textTwo.text = member.lastName
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

    class CustomYesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textOne: TextView = itemView.findViewById(R.id.text_one)
        val textTwo: TextView = itemView.findViewById(R.id.text_two)
    }

    class CustomNoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textOne: TextView = itemView.findViewById(R.id.text_one)
        val textTwo: TextView = itemView.findViewById(R.id.text_two)
    }
}