package com.example.pinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltandroomtutorial.R
import com.example.pinapp.DeletePinCallback
import com.example.pinapp.db.PinEntity


class PinAdapter (
    private val deletePinCallback: DeletePinCallback
) : RecyclerView.Adapter<PinAdapter.ViewHolder>() {

    lateinit var pinsList: List<PinEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pin_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pinsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pinsList[position]
        holder.pinNameTextView.text = item.name
        holder.pinCodeTextView.text = item.code
        holder.pinTrashButton.setOnClickListener {
            deletePinCallback.onDeleteClick(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pinNameTextView: TextView = itemView.findViewById(R.id.pin_name_tv)
        val pinCodeTextView: TextView = itemView.findViewById(R.id.pin_code_tv)
        val pinTrashButton : ImageView = itemView.findViewById(R.id.pin_trash)
    }
}