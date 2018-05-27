package sszymanski.co.uk.myschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cardview_cleaning_event_item.view.*
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 27/05/2018.
 */
class CleaningEventsAdapter(val cleaningEvents: List<CleaningEvent>) : RecyclerView.Adapter<CleaningEventsAdapter.CleaningEventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CleaningEventViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.cardview_cleaning_event_item, parent, false)
        return CleaningEventViewHolder(itemView)
    }

    override fun getItemCount() = cleaningEvents.size


    override fun onBindViewHolder(holder: CleaningEventViewHolder?, position: Int) {
        holder?.bind(cleaningEvents[position])
    }


    inner class CleaningEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cleaningEvent: CleaningEvent) {
                itemView.textview_name.text=cleaningEvent.personName
                itemView.textview_room_name.text = cleaningEvent.roomName
        }
    }
}