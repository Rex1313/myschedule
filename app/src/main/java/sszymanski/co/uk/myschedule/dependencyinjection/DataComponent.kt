package sszymanski.co.uk.myschedule.dependencyinjection

import dagger.Component
import sszymanski.co.uk.myschedule.CalendarFragmentPresenter
import sszymanski.co.uk.myschedule.MainPresenter
import javax.inject.Singleton

/**
 * Created by rex on 27/05/2018.
 */
@Singleton
@Component(modules = arrayOf(DataModule::class))
interface DataComponent {
    fun inject(calendarFragmentPresenter: CalendarFragmentPresenter)
    fun inject(mainPresenter: MainPresenter)

}