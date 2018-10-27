package jp.cordea.kompas

import dagger.Module
import jp.cordea.kompas.main.MainActivityModule

@Module(includes = [
    MainActivityModule::class
])
interface ActivityModule
