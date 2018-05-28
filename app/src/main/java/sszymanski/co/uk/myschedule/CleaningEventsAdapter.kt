package sszymanski.co.uk.myschedule

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_cleaning_event_item.view.*
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 27/05/2018.
 */
class CleaningEventsAdapter(val context: Context, val cleaningEvents: List<CleaningEvent>) : RecyclerView.Adapter<CleaningEventsAdapter.CleaningEventViewHolder>() {
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
            itemView.textview_name.text = cleaningEvent.personName
            itemView.textview_room_name.text = if (cleaningEvent.cleaningType.equals("BIG")) "Big " + cleaningEvent.roomName else "Small " + cleaningEvent.roomName
            itemView.textview_notes.text = cleaningEvent.notes
            if (cleaningEvent.avatarUrl.length > 0)
                Picasso.get().load(cleaningEvent.avatarUrl).into(itemView.imageview_person_icon)

            if (cleaningEvent.isPassed > 0) {
                val colour = if (cleaningEvent.isPassed == 1) context.getColor(R.color.cleaning_passed) else context.getColor(R.color.cleaning_failed)
                itemView.imageview_status_icon.background = ColorDrawable(colour)
            }

        }
    }
}