package jp.cordea.kompas.main

import android.app.Activity
import jp.cordea.kompas.ActivityScope
import jp.cordea.kompas.detail.DetailActivity
import javax.inject.Inject

@ActivityScope
class MainNavigator @Inject constructor(
        private val activity: Activity
) {
    fun navigateToDetail(model: MainListItemViewModel) {
        activity.startActivity(DetailActivity.newIntent(activity, model))
    }
}