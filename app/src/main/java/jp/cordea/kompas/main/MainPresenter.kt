package jp.cordea.kompas.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.SerialDisposable
import jp.cordea.kompas.infra.ConnpassRepository
import javax.inject.Inject

interface MainContract {
    interface View {
        fun startLoading()
        fun endLoading()
        fun addItem(model: MainListItemViewModel)
    }

    interface Presenter {
        fun create()
        fun onQueryTextSubmit(query: String)
        fun destroy()
    }
}

class MainPresenter @Inject constructor(
        private val repository: ConnpassRepository,
        private val view: MainContract.View
) : MainContract.Presenter {
    private val serialDisposable = SerialDisposable()

    override fun create() {
        view.endLoading()
    }

    override fun onQueryTextSubmit(query: String) {
        view.startLoading()
        repository.getEvents(query)
                .map { it.events }
                .filter { it.isNotEmpty() }
                .flatMapObservable { list ->
                    Observable.fromIterable(list)
                            .map { MainListItemViewModel.from(it) }
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
