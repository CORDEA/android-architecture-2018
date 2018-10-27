package jp.cordea.kompas.infra

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import jp.cordea.kompas.infra.events.EventsResponse
import jp.cordea.kompas.infra.favorite.Favorite
import jp.cordea.kompas.infra.favorite.FavoriteDaoProvider
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ConnpassLocalDataSource @Inject constructor(
        private val daoProvider: FavoriteDaoProvider
) {
    private var keyword: String? = null
    private var events: EventsResponse? = null

    fun getEvents(keyword: String): Maybe<EventsResponse> =
            if (events == null || this.keyword != keyword) Maybe.empty() else Maybe.just(events)

    fun cacheEvents(keyword: String, events: EventsResponse) {
        this.keyword = keyword
        this.events = events
    }

    fun getFavorite(eventId: Int) =
            daoProvider.favoriteDao.getFavorite(eventId)
                    .subscribeOn(Schedulers.io())

    fun favorite(eventId: Int) =
            Completable.create {
                daoProvider.favoriteDao.insertFavorite(
                        Favorite(
                                eventId,
                                ISODateTimeFormat.dateTime().print(DateTime())
                        )
                )
                it.onComplete()
            }.subscribeOn(Schedulers.io())

    fun unfavorite(eventId: Int) =
            Completable.create {
                daoProvider.favoriteDao.deleteFavorite(eventId)
                it.onComplete()
            }.subscribeOn(Schedulers.io())
}
