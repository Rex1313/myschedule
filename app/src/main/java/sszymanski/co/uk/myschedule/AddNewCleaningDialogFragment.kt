package sszymanski.co.uk.myschedule

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_fragment_add_new_cleaning.*
import org.joda.time.LocalDate
import sszymanski.co.uk.myschedule.Values.Companion.ARG_DATE
import sszymanski.co.uk.myschedule.models.Person
import sszymanski.co.uk.myschedule.models.Room
import java.util.*


/**
 * Created by rex on 27/05/2018.
 */
class AddNewCleaningDialogFragment : DialogFragment(), MainMVP.AddNewCleaningView {
    lateinit var callback: AddNewCleaningDialogInteractions

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as AddNewCleaningDialogInteractions
    }

    lateinit var presenter: MainMVP.AddNewCleaningPresenter
    var date: Long = 0
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_fragment_add_new_cleaning, container, false)
        return view;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AddNewCleaningPresenter(this)
        presenter.loadPeople()
        presenter.loadRooms()
        date = arguments.getLong(ARG_DATE, 0)
        presenter.setDate(date)
        textview_date_header.text = LocalDate.fromDateFields(Date(date)).toString("dd MMM")
        radiogroup_cleaning_type.setOnCheckedChangeListener({ group, checkedId ->
            when (checkedId) {
                R.id.radiobutton_big -> presenter.onTypeSelected("BIG")
                R.id.radiobutton_small -> presenter.onTypeSelected("SMALL")
            }
        })

        edittext_notes.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onNotesEdited(s.toString())
            }
        })
        button_save.setOnClickListener { presenter.onSave() }
    }

    override fun onPeopleReady(people: List<Person>) {
        spinner_person.adapter = ArrayAdapter<Person>(activity, android.R.layout.simple_spinner_dropdown_item, people)
        spinner_person.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onPersonSelected(parent?.getItemAtPosition(position) as Person)
            }

        }
    }

    override fun onRoomsReady(rooms: List<Room>) {
        spinner_room.adapter = ArrayAdapter<Room>(activity, android.R.layout.simple_spinner_dropdown_item, rooms)
        spinner_room.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onRoomSelected(parent?.getItemAtPosition(position) as Room)
            }

        }
    }

    override fun onNoteAdded() {
        dismiss()
    }

    interface  AddNewCleaningDialogInteractions {
        fun onNoteAdded()
    }
}