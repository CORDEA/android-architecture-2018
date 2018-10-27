package jp.cordea.kompas.detail

import com.xwray.groupie.databinding.BindableItem
import jp.cordea.kompas.R
import jp.cordea.kompas.databinding.ListItemInfoBinding
import jp.cordea.kompas.main.MainListItemViewModel
import javax.inject.Inject

class InfoListItemViewModel(
        private val startedAt: String,
        private val endedAt: String,
        private val limit: Int,
        private val accepted: Int
) {
    companion object {
        fun from(model: MainListItemViewModel) =
                InfoListItemViewModel(
                        model.startedAt,
                        model.endedAt,
                        model.limit,
                        model.accepted
                )
    }
}

class InfoListItem @Inject constructor() : BindableItem<ListItemInfoBinding>() {
    private lateinit var model: InfoListItemViewModel

    fun update(model: InfoListItemViewModel) = apply { this.model = model }

    override fun getLayout(): Int = R.layout.list_item_info

    override fun bind(binding: ListItemInfoBinding, position: Int) {
    }
}
