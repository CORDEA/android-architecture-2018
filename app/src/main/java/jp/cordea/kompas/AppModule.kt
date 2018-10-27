package jp.cordea.kompas

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import jp.cordea.kompas.infra.ConnpassApiUrlProvider

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideConnpassApiUrlProvider() = object : ConnpassApiUrlProvider {
        override val url: String get() = BuildConfig.API_BASE_URL
    }
}
