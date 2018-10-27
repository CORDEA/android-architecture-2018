package jp.cordea.kompas.infra

import io.reactivex.Single
import jp.cordea.kompas.infra.events.EventsResponse

interface ConnpassRepository {
    fun getEvents(keyword: String): Single<EventsResponse>
}
