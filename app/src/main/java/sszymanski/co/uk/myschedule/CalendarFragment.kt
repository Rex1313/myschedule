package sszymanski.co.uk.myschedule

import android.os.Bundle
import android.view.View
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import java.util.*

/**
 * Created by rex on 27/05/2018.
 */
class CalendarFragment: CaldroidFragment(), MainMVP.CalendarView {
lateinit var presenter:MainMVP.CalendarPresenter
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CalendarFragmentPresenter(this)
        caldroidListener = listener
    }


    var listener = object : CaldroidListener() {
        override fun onSelectDate(date: Date?, view: View?) {
           println("date ${date.toString()} clicked")
        }

        override fun onChangeMonth(month: Int, year: Int) {
            super.onChangeMonth(month, year)
        }
    }
}