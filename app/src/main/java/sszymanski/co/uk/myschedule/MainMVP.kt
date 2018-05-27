package sszymanski.co.uk.myschedule

import com.roomorama.caldroid.CaldroidFragment

/**
 * Created by rex on 27/05/2018.
 */
interface MainMVP {
    interface MainPresenter{

    }
    interface MainView{

    }
    interface CalendarPresenter{
    fun initializeCalendar()
    }
    interface CalendarView{

    }
}