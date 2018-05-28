package sszymanski.co.uk.myschedule

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import sszymanski.co.uk.myschedule.models.CleaningEvent
import java.util.*

/**
 * Created by rex on 27/05/2018.
 */
class CalendarFragment : CaldroidFragment(), MainMVP.CalendarView {

    lateinit var callback: CalendarFragment.ClaendarFragmentInteractions
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as ClaendarFragmentInteractions
    }

    override fun onDayEventsLoaded(localDate: LocalDate, cleaningEvents: List<CleaningEvent>) {
        callback.onDaySelected(localDate, cleaningEvents)
    }

    override fun updateMonthView(mapOfDates: Map<Date, Drawable>) {
        setBackgroundDrawableForDates(mapOfDates)
        refreshView()
    }

    override fun updateCalendarView(dateLong: Long) {
        val date = Date(dateLong)
        val drawable: Drawable = activity.getDrawable(android.R.drawable.btn_star)
        setBackgroundDrawableForDate(drawable, date)
        refreshView()
    }

    lateinit var presenter: MainMVP.CalendarPresenter
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CalendarFragmentPresenter(activity, this)
        caldroidListener = listener
    }

    var listener = object : CaldroidListener() {
        override fun onLongClickDate(date: Date?, view: View?) {
            super.onLongClickDate(date, view)
            callback.onCalendarFieldLongClicked(date)
        }

        override fun onSelectDate(date: Date?, view: View?) {
            val localDate = LocalDateTime.fromDateFields(date)
            presenter.loadDayDetails(localDate.dayOfMonth, localDate.monthOfYear, localDate.year)
        }

        override fun onChangeMonth(month: Int, year: Int) {
            super.onChangeMonth(month, year)
            presenter.loadMonth(month, year)
        }
    }

    interface ClaendarFragmentInteractions {
        fun onDaySelected(localDateTime: LocalDate, cleaningEvents: List<CleaningEvent>)
        fun onCalendarFieldLongClicked(date: Date?)
    }
}