package jp.cordea.kompas.presentation.main

import jp.cordea.kompas.infra.events.EventResponse

class MainListItemViewModel(
        val eventId: Int,
        val title: String,
        val catch: String,
        val author: String,
        val description: String,
        val startedAt: String,
        val endedAt: String,
        val limit: Int,
        val accepted: Int
) {
    companion object {
        fun from(response: EventResponse) =
                MainListItemViewModel(
                        response.eventId,
                        response.title,
                        response.catch,
                        response.ownerNickname,
                        response.description,
                        response.startedAt,
                        response.endedAt,
                        response.limit,
                        response.accepted
                )
    }
}
