package jp.cordea.kompas.main

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject
import javax.inject.Provider

class MainAdapter @Inject constructor(
        private val item: Provider<MainListItem>
) : GroupAdapter<ViewHolder>() {
    fun add(model: MainListItemViewModelImpl) = add(item.get().update(model))
}
