package sszymanski.co.uk.myschedule

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sszymanski.co.uk.myschedule.dependencyinjection.DaggerDataComponent
import sszymanski.co.uk.myschedule.models.Person
import sszymanski.co.uk.myschedule.models.PostCleaningEvent
import sszymanski.co.uk.myschedule.models.Room
import sszymanski.co.uk.myschedule.service.EndpointInterface
import javax.inject.Inject

/**
 * Created by rex on 27/05/2018.
 */
class AddNewCleaningPresenter(val view: MainMVP.AddNewCleaningView) : MainMVP.AddNewCleaningPresenter {
    @Inject
    lateinit var endpointInterface: EndpointInterface
    val postCleaningEvent = PostCleaningEvent()


    init {
        DaggerDataComponent.builder().build().inject(this)
    }

    override fun loadPeople() {
        endpointInterface.getAllPeople().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onPeopleReady(it)
                })
    }

    override fun loadRooms() {
        endpointInterface.getAllRooms().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onRoomsReady(it)
                })
    }

    override fun setDate(date: Long) {
        postCleaningEvent.cleaningDate = date
    }

    override fun onPersonSelected(person: Person) {
        postCleaningEvent.personId = person.personId
    }

    override fun onRoomSelected(room: Room) {
        postCleaningEvent.roomId = room.roomId
    }

    override fun onTypeSelected(type: String) {
        postCleaningEvent.cleaningType = type
    }

    override fun onNotesEdited(note: String) {
        postCleaningEvent.notes = note
    }

    override fun onSave() {
        endpointInterface.postNewCleaning(postCleaningEvent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onNoteAdded()
                }, Throwable::printStackTrace)
    }


}