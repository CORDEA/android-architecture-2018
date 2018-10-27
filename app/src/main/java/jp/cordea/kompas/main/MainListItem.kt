package jp.cordea.kompas.main

import android.os.Parcelable
import com.xwray.groupie.databinding.BindableItem
import jp.cordea.kompas.R
import jp.cordea.kompas.databinding.ListItemMainBinding
import jp.cordea.kompas.infra.events.EventResponse
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
class MainListItemViewModel(
        val eventId: Int,
        val title: String,
        val catch: String,
        val author: String,
        val description: String,
        val startedAt: String,
        val endedAt: String,
        val limit: Int,
        val accepted: Int
) : Parcelable {
    companion object {
        fun from(response: EventResponse) =
                MainListItemViewModel(
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
    private lateinit var model: MainListItemViewModel

    fun update(model: MainListItemViewModel) = apply { this.model = model }

    override fun getLayout(): Int = R.layout.list_item_main

    override fun bind(binding: ListItemMainBinding, position: Int) {
        binding.vm = model
        binding.root.setOnClickListener {
            navigator.navigateToDetail(model)
        }
    }
}
