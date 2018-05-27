package sszymanski.co.uk.myschedule

import com.roomorama.caldroid.CaldroidFragment
import org.joda.time.LocalDate
import sszymanski.co.uk.myschedule.models.CleaningEvent
import sszymanski.co.uk.myschedule.models.Person
import sszymanski.co.uk.myschedule.models.Room

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

    interface CleaningEventsView{

    }
    interface CleanintEventsPresenter{

    }

    interface AddNewCleaningView{
        fun onPeopleReady(people:List<Person>)
        fun onRoomsReady(room:List<Room>)
        fun onNoteAdded()

    }
    interface AddNewCleaningPresenter{
        fun loadPeople()
        fun loadRooms()
        fun setDate(date:Long)
        fun onPersonSelected(person: Person)
        fun onRoomSelected(room:Room)
        fun onTypeSelected(type:String)
        fun onNotesEdited(note:String)
        fun onSave()
    }
}