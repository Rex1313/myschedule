package sszymanski.co.uk.myschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cleaning_events.*
import sszymanski.co.uk.myschedule.models.CleaningEvent

/**
 * Created by rex on 27/05/2018.
 */
class CleaningEventsFragment: Fragment(), MainMVP.CleaningEventsView {
    lateinit var presenter: MainMVP.CleanintEventsPresenter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_cleaning_events, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CleaningEventsFragmentPresenter(this)
    }

    fun loadEvents(eventList: List<CleaningEvent>){
        recyclerview_cleaning.adapter = CleaningEventsAdapter(eventList)
    }

    interface CleaningEventsInteractions{

    }
}