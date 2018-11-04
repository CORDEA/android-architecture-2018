package jp.cordea.kompas.presentation.main

interface MainListItemViewModel {
    val eventId: Int
    val title: String
    val catch: String
    val author: String
    val description: String
    val startedAt: String
    val endedAt: String
    val limit: Int
    val accepted: Int
}
