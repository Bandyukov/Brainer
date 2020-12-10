package com.example.brainer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.brainer.R
import com.example.brainer.helperObjects.Record

class RecordAdapter(private var records: List<Record>) :
    RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = records[position]
        holder.bind(record)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    class RecordViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewLevel: TextView = itemView.findViewById(R.id.textViewLevel)
        var textViewScore: TextView = itemView.findViewById(R.id.textViewScore)

        fun bind(record: Record) {
            val level: String
            val colorId: Int

            when (record.mod) {
                1 -> {
                    colorId = itemView.resources.getColor(android.R.color.holo_green_light)
                    level = itemView.resources.getString(R.string.easy)
                }
                2 -> {
                    colorId = itemView.resources.getColor(android.R.color.holo_orange_light)
                    level = itemView.resources.getString(R.string.middle)
                }
                else -> {
                    colorId = itemView.resources.getColor(android.R.color.holo_red_light)
                    level = itemView.resources.getString(R.string.hard)
                }
            }

            textViewScore.text = record.score.toString()
            textViewLevel.text = level
            itemView.setBackgroundColor(colorId)
        }

        companion object {
            fun from(parent: ViewGroup): RecordViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
                return RecordViewHolder(view)
            }
        }
    }
}