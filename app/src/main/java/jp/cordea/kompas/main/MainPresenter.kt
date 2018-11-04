package jp.cordea.kompas.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.SerialDisposable
import jp.cordea.kompas.infra.ConnpassRepository
import jp.cordea.kompas.presentation.ActivityScope
import javax.inject.Inject

interface MainContract {
    interface View {
        fun startLoading()
        fun endLoading()
        fun addItem(model: MainListItemViewModelImpl)
    }

    interface Presenter {
        val currentQuery: String
        fun create(savedQuery: String)
        fun onQueryTextSubmit(query: String)
        fun destroy()
    }
}

@ActivityScope
class MainPresenter @Inject constructor(
        private val repository: ConnpassRepository,
        private val view: MainContract.View
) : MainContract.Presenter {
    private val serialDisposable = SerialDisposable()

    override var currentQuery: String = ""
        private set

    override fun create(savedQuery: String) {
        view.endLoading()
        onQueryTextSubmit(savedQuery)
    }

    override fun onQueryTextSubmit(query: String) {
        currentQuery = query
        view.startLoading()
        repository.getEvents(query)
                .map { it.events }
                .filter { it.isNotEmpty() }
                .flatMapObservable { list ->
                    Observable.fromIterable(list)
                            .map { MainListItemViewModelImpl.from(it) }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.addItem(it)
                }, {
                    it.printStackTrace()
                    view.endLoading()
                }, {
                    view.endLoading()
                })
                .run(serialDisposable::set)
    }

    override fun destroy() {
        serialDisposable.dispose()
    }
}
