package sszymanski.co.uk.myschedule

import com.roomorama.caldroid.CaldroidFragment
import org.joda.time.LocalDate
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 27/05/2018.
 */
interface MainMVP {
    interface MainPresenter {

    }

    interface MainView {
    }

    interface CalendarPresenter {
        fun initializeCalendar()
        fun loadMonth(month:Int, year:Int)
        fun loadDayDetails(day:Int, month:Int, year:Int)

    }

    interface CalendarView {
        fun updateCalendarView(dateLong:Long)
        fun onDayEventsLoaded(cleaningEvents: List<CleaningEvent>)
    }
}