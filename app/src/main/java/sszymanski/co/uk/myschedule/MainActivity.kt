package sszymanski.co.uk.myschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.roomorama.caldroid.CaldroidFragment
import sszymanski.co.uk.myschedule.models.CleaningEvent

class MainActivity : AppCompatActivity(), MainMVP.MainView, CalendarFragment.ClaendarFragmentInteractions {


    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        val args = Bundle()
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY)
        val calendarFragment = CalendarFragment()
        calendarFragment.arguments = args
        supportFragmentManager.beginTransaction()?.replace(R.id.calendar_container, calendarFragment)?.commit()
    }

    override fun onDaySelected(cleaningEvents: List<CleaningEvent>) {

        cleaningEvents.forEach({
            println("${it.personName}, ${it.roomName}")
        })
    }
}
