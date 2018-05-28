package sszymanski.co.uk.myschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.roomorama.caldroid.CaldroidFragment
import org.joda.time.LocalDate
import sszymanski.co.uk.myschedule.Values.Companion.ARG_DATE
import sszymanski.co.uk.myschedule.models.CleaningEvent
import java.time.chrono.ChronoLocalDate
import java.util.*

class MainActivity : AppCompatActivity(), MainMVP.MainView, AddNewCleaningDialogFragment.AddNewCleaningDialogInteractions, CalendarFragment.ClaendarFragmentInteractions {


    lateinit var calendarFragment: CalendarFragment
    lateinit var cleaningEventsFragment: CleaningEventsFragment

    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        val args = Bundle()
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY)
        calendarFragment = CalendarFragment()
        cleaningEventsFragment = CleaningEventsFragment()
        calendarFragment.arguments = args
        supportFragmentManager.beginTransaction()?.replace(R.id.calendar_container, calendarFragment)?.commit()
        supportFragmentManager.beginTransaction()?.replace(R.id.details_container, cleaningEventsFragment)?.commit()
    }

    override fun onDaySelected(localDate: LocalDate, cleaningEvents: List<CleaningEvent>) {
        cleaningEventsFragment.loadEvents(localDate, cleaningEvents)
    }

    override fun onCalendarFieldLongClicked(date: Date?) {
        val addNewCleaningDialogFragment = AddNewCleaningDialogFragment()
        val args = Bundle()
        args.putLong(ARG_DATE, date!!.time)
        addNewCleaningDialogFragment.arguments = args
        addNewCleaningDialogFragment.show(supportFragmentManager, "add_new")

    }

    override fun onNoteAdded() {

    }

}
