package jp.cordea.kompas.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.SerialDisposable
import jp.cordea.kompas.ActivityScope
import jp.cordea.kompas.infra.ConnpassRepository
import jp.cordea.kompas.main.MainListItemViewModel
import javax.inject.Inject

interface DetailContract {
    interface View {
        fun favorite()
        fun unfavorite()
    }

    interface Presenter {
        fun create(model: MainListItemViewModel)
        fun clickedFab()
        fun destroy()
    }
}

@ActivityScope
class DetailPresenter @Inject constructor(
        private val repository: ConnpassRepository,
        private val view: DetailContract.View
) : DetailContract.Presenter {
    private val serialDisposable = SerialDisposable()

    private lateinit var model: MainListItemViewModel

    private var isFavorite: Boolean = false
        set(value) {
            field = value
            if (value) {
                view.favorite()
            } else {
                view.unfavorite()
            }
        }

    override fun create(model: MainListItemViewModel) {
        this.model = model

        repository.getFavorite(model.eventId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavorite = true
                }, {
                    isFavorite = false
                }, {
                    isFavorite = false
                })
                .run(serialDisposable::set)
    }

    override fun clickedFab() {
        repository.favorite(model.eventId)
        isFavorite = !isFavorite
    }

    override fun destroy() {
        serialDisposable.dispose()
    }
}
