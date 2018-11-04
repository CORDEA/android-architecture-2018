package jp.cordea.kompas.main

import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.SerialDisposable
import jp.cordea.kompas.presentation.ActivityScope
import jp.cordea.kompas.infra.ConnpassRepository
import javax.inject.Inject

interface MainContract {
    interface View {
        fun startLoading()
        fun endLoading()
        fun addItem(model: MainListItemViewModel)
    }

    interface Presenter {
        val currentQuery: String
        fun create(savedInstanceState: Bundle?)
        fun onQueryTextSubmit(query: String)
        fun saveInstanceState(outState: Bundle?)
        fun destroy()
    }
}

@ActivityScope
class MainPresenter @Inject constructor(
        private val repository: ConnpassRepository,
        private val view: MainContract.View
) : MainContract.Presenter {
    private val serialDisposable = SerialDisposable()

    private var _currentQuery: String? = null
    override val currentQuery: String get() = _currentQuery ?: ""

    override fun create(savedInstanceState: Bundle?) {
        view.endLoading()
        val query = savedInstanceState?.getString(QUERY_KEY) ?: return
        onQueryTextSubmit(query)
    }

    override fun onQueryTextSubmit(query: String) {
        _currentQuery = query
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

    override fun saveInstanceState(outState: Bundle?) {
        val state = outState ?: return
        _currentQuery?.let { state.putString(QUERY_KEY, it) }
    }

    override fun destroy() {
        serialDisposable.dispose()
    }

    companion object {
        private const val QUERY_KEY = "QUERY_KEY"
    }
}
