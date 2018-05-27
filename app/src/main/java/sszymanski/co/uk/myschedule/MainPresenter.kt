package sszymanski.co.uk.myschedule

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sszymanski.co.uk.myschedule.dependencyinjection.DaggerDataComponent
import sszymanski.co.uk.myschedule.service.EndpointInterface
import javax.inject.Inject

/**
 * Created by rex on 27/05/2018.
 */
class MainPresenter : MainMVP.MainPresenter {
    @Inject
    lateinit var endpointInterface: EndpointInterface

    init {
        DaggerDataComponent.builder().build().inject(this)
        endpointInterface.getCleanings("2012-04-12", "2018-04-30")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach({ event ->
                        println(event.toString())
                    })
                })
    }
}