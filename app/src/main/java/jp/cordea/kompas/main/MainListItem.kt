package jp.cordea.kompas.main

import android.os.Parcelable
import com.xwray.groupie.databinding.BindableItem
import jp.cordea.kompas.R
import jp.cordea.kompas.databinding.ListItemMainBinding
import jp.cordea.kompas.infra.events.EventResponse
import jp.cordea.kompas.presentation.main.MainListItemViewModel
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
class MainListItemViewModelImpl(
        override val eventId: Int,
        override val title: String,
        override val catch: String,
        override val author: String,
        override val description: String,
        override val startedAt: String,
        override val endedAt: String,
        override val limit: Int,
        override val accepted: Int
) : Parcelable, MainListItemViewModel {
    companion object {
        fun from(response: EventResponse) =
                MainListItemViewModelImpl(
                        response.eventId,
                        response.title,
                        response.catch,
                        response.ownerNickname,
                        response.description,
                        response.startedAt,
                        response.endedAt,
                        response.limit,
                        response.accepted
                )
    }
}

class MainListItem @Inject constructor(
        private val navigator: MainNavigator
) : BindableItem<ListItemMainBinding>() {
    private lateinit var model: MainListItemViewModelImpl

    fun update(model: MainListItemViewModelImpl) = apply { this.model = model }

    override fun getLayout(): Int = R.layout.list_item_main

    override fun bind(binding: ListItemMainBinding, position: Int) {
        binding.vm = model
        binding.root.setOnClickListener {
            navigator.navigateToDetail(model)
        }
    }
}
