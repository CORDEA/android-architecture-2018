package jp.cordea.kompas

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import jp.cordea.kompas.infra.ConnpassApiUrlProvider
import jp.cordea.kompas.infra.favorite.FavoriteDao
import jp.cordea.kompas.infra.favorite.FavoriteDaoProvider

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideAppDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "kompas").build()

    @Provides
    fun provideConnpassApiUrlProvider() = object : ConnpassApiUrlProvider {
        override val url: String get() = BuildConfig.API_BASE_URL
    }

    @Provides
    fun provideFavoriteDaoProvider(appDatabase: AppDatabase) = object : FavoriteDaoProvider {
        override val favoriteDao: FavoriteDao get() = appDatabase.favoriteDao()
    }
}
