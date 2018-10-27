package jp.cordea.kompas.infra

import io.reactivex.Single
import jp.cordea.kompas.infra.events.EventsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ConnpassRepositoryImpl @Inject constructor(
        private val remoteDataSource: ConnpassRemoteDataSource,
        private val localDataSource: ConnpassLocalDataSource
) : ConnpassRepository {
    override fun getEvents(keyword: String): Single<EventsResponse> =
            localDataSource.getEvents(keyword).switchIfEmpty(remoteDataSource.getEvents(keyword))
}
