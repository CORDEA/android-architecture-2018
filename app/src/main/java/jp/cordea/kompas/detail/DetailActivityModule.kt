package jp.cordea.kompas.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.kompas.ActivityScope

@Module
interface DetailActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    fun contributeDetailActivity(): DetailActivity
}
