package jp.cordea.kompas.main

import com.xwray.groupie.databinding.BindableItem
import jp.cordea.kompas.R
import jp.cordea.kompas.databinding.ListItemMainBinding
import jp.cordea.kompas.infra.events.EventResponse
import javax.inject.Inject

class MainListItemViewModel(
        val title: String,
        val description: String,
        val author: String
) {
    companion object {
        fun from(eventResponse: EventResponse) =
                MainListItemViewModel(
                        eventResponse.title,
                        eventResponse.catch,
                        eventResponse.ownerNickname
                )
    }
}

class MainListItem @Inject constructor() : BindableItem<ListItemMainBinding>() {
    private lateinit var model: MainListItemViewModel

    fun update(model: MainListItemViewModel) = apply { this.model = model }

    override fun getLayout(): Int = R.layout.list_item_main

    override fun bind(binding: ListItemMainBinding, position: Int) {
        binding.vm = model
    }
}
