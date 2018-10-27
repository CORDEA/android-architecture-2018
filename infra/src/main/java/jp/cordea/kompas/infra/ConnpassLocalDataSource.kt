package jp.cordea.kompas.infra

import io.reactivex.Maybe
import jp.cordea.kompas.infra.events.EventsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ConnpassLocalDataSource @Inject constructor() {
    private var events: EventsResponse? = null

    fun getEvents(): Maybe<EventsResponse> =
            if (events == null) Maybe.empty() else Maybe.just(events)

    fun cacheEvents(events: EventsResponse) {
        this.events = events
    }
}
