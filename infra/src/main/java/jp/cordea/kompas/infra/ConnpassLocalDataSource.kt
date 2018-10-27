package jp.cordea.kompas.infra

import io.reactivex.Maybe
import jp.cordea.kompas.infra.events.EventsResponse
import jp.cordea.kompas.infra.favorite.Favorite
import jp.cordea.kompas.infra.favorite.FavoriteDaoProvider
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ConnpassLocalDataSource @Inject constructor(
        private val favoriteDaoProvider: FavoriteDaoProvider
) {
    private var keyword: String? = null
    private var events: EventsResponse? = null

    fun getEvents(keyword: String): Maybe<EventsResponse> =
            if (events == null || this.keyword != keyword) Maybe.empty() else Maybe.just(events)

    fun cacheEvents(keyword: String, events: EventsResponse) {
        this.keyword = keyword
        this.events = events
    }

    fun getFavorite(eventId: Int) = favoriteDaoProvider.favoriteDao.getFavorite(eventId)

    fun favorite(eventId: Int) {
        favoriteDaoProvider.favoriteDao.insertFavorite(
                Favorite(
                        eventId,
                        ISODateTimeFormat.dateTime().print(DateTime())
                )
        )
    }

    fun unfavorite(favorite: Favorite) {
        favoriteDaoProvider.favoriteDao.deleteFavorite(favorite)
    }
}
