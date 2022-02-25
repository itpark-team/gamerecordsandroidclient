package com.example.gamerecords.controllers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamerecords.R
import com.example.gamerecords.dtos.entity.RecordResponseDto
import com.example.gamerecords.utils.GlobalVariables
import com.example.gamerecords.views.DetailsActivity

class RvAdapterMainActivity : RecyclerView.Adapter<RvAdapterMainActivity.RecordItemHolder> {

    class RecordItemHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {

        var textViewRecordItemNickname: TextView =
            view.findViewById(R.id.textViewRecordItemNickname)
        var textViewRecordItemScore: TextView = view.findViewById(R.id.textViewRecordItemScore)
        var textViewRecordItemGame: TextView = view.findViewById(R.id.textViewRecordItemGame)
        var buttonRecordItemDetails: Button = view.findViewById(R.id.buttonRecordItemDetails)

    }

    private var recordsList: ArrayList<RecordResponseDto>

    constructor(recordsList: ArrayList<RecordResponseDto>) : super() {
        this.recordsList = recordsList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item, parent, false)

        return RecordItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecordItemHolder, position: Int) {

        var globalVariables = GlobalVariables.instance

        var record = recordsList[position]

        holder.textViewRecordItemNickname.text = record.nickname
        holder.textViewRecordItemGame.text = record.game
        holder.textViewRecordItemScore.text = record.score.toString()

        holder.buttonRecordItemDetails.setOnClickListener {
            globalVariables.currentRecord = record

            var intent = Intent(globalVariables.applicationContext, DetailsActivity::class.java)
            globalVariables.applicationContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recordsList.size
    }
}