package jp.cordea.kompas.infra

import io.reactivex.Maybe
import io.reactivex.Single
import jp.cordea.kompas.infra.events.EventsResponse
import jp.cordea.kompas.infra.favorite.Favorite

interface ConnpassRepository {
    fun getEvents(keyword: String): Single<EventsResponse>
    fun getFavorite(eventId: Int): Maybe<Favorite>
    fun favorite(eventId: Int)
    fun unfavorite(favorite: Favorite)
}
