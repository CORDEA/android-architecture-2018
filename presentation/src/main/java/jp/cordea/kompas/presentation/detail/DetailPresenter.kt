package jp.cordea.kompas.presentation.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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
        fun clickedFavorite()
        fun clickedUnfavorite()
        fun destroy()
    }
}

@ActivityScope
class DetailPresenter @Inject constructor(
        private val repository: ConnpassRepository,
        private val view: DetailContract.View
) : DetailContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

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
                .addTo(compositeDisposable)
    }

    override fun clickedFavorite() {
        repository.unfavorite(model.eventId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavorite = false
                }, {
                    it.printStackTrace()
                })
                .addTo(compositeDisposable)
    }

    override fun clickedUnfavorite() {
        repository.favorite(model.eventId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavorite = true
                }, {
                    it.printStackTrace()
                })
                .addTo(compositeDisposable)
    }

    override fun destroy() {
        compositeDisposable.dispose()
    }
}
