package jp.cordea.kompas.detail

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.kompas.ActivityScope

@Module
interface DetailActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        DetailActivityBindModule::class
    ])
    fun contributeDetailActivity(): DetailActivity
}

@Module
interface DetailActivityBindModule {
    @Binds
    fun bindPresenter(presenter: DetailPresenter): DetailContract.Presenter

    @Binds
    fun bindView(activity: DetailActivity): DetailContract.View
}
