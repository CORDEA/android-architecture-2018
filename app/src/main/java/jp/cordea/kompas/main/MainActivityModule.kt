package jp.cordea.kompas.main

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.kompas.ActivityScope

@Module
interface MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainActivityBindModule::class
    ])
    fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityBindModule {
    @Binds
    fun bindPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    fun bindView(activity: MainActivity): MainContract.View
}
