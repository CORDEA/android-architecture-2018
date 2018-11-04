package jp.cordea.kompas.main

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import jp.cordea.kompas.presentation.main.MainListItemViewModel
import javax.inject.Inject
import javax.inject.Provider

class MainAdapter @Inject constructor(
        private val item: Provider<MainListItem>
) : GroupAdapter<ViewHolder>() {
    fun add(model: MainListItemViewModel) = add(item.get().update(model))
}
