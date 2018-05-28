package sszymanski.co.uk.myschedule

import android.content.Context
import android.graphics.drawable.Drawable
import com.roomorama.caldroid.CaldroidFragment
import android.os.Bundle
import android.view.View
import com.roomorama.caldroid.CaldroidListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import sszymanski.co.uk.myschedule.dependencyinjection.DaggerDataComponent
import sszymanski.co.uk.myschedule.service.EndpointInterface
import java.time.ZoneOffset
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Created by rex on 27/05/2018.
 */
class CalendarFragmentPresenter(val context: Context, val calendarView: MainMVP.CalendarView) : MainMVP.CalendarPresenter {


    @Inject
    lateinit var endpointInterface: EndpointInterface

    init {
        DaggerDataComponent.builder().build().inject(this)

    }

    override fun initializeCalendar() {

    }

    override fun loadDayDetails(day: Int, month: Int, year: Int) {
        val dayString = if (day < 10) "0$day" else day.toString()
        val monthString = if (month < 10) "0$month" else month.toString()
        val dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy")
        val dateString = LocalDate.parse("$dayString-$monthString-${year.toString()}", dateFormat).toString("yyyy-MM-dd")
        endpointInterface.getCleanings(dateString, dateString).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    calendarView.onDayEventsLoaded(LocalDate.parse("$dayString-$monthString-${year.toString()}", dateFormat),it)
                })
    }

    override fun loadMonth(month: Int, year: Int) {
        val monthString = if (month < 10) "0$month" else month.toString()
        val dateFormat = DateTimeFormat.forPattern("MM-yyyy")
        val date = LocalDate.parse("$monthString-${year.toString()}", dateFormat)
        val from = date.withDayOfMonth(1).toString("yyyy-MM-dd")
        val to = date.withDayOfMonth(date.dayOfMonth().maximumValue).toString("yyyy-MM-dd")
        endpointInterface.getCleanings(from, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ events->
                    val map:Map<Date, Drawable> = events.map { event->
                        val dateLong: Long = LocalDateTime.parse(event.cleaningDate).toDateTime(DateTimeZone.UTC).millis
                        Date(dateLong) to IconGenerator.generateCalendarIcon(context, events.filter { it.cleaningDate.equals(event.cleaningDate) })
                    }.toMap()
                    calendarView.updateMonthView(map)
//
//                    it.forEach({ event ->
//                        val dateLong: Long = LocalDateTime.parse(event.cleaningDate).toDateTime(DateTimeZone.UTC).millis
//                        calendarView.updateCalendarView(dateLong)
//                        println(event.toString())
//                    })
                })
    }


}