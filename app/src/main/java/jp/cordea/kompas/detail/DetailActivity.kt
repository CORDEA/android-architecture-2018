package jp.cordea.kompas.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import jp.cordea.kompas.R
import jp.cordea.kompas.databinding.ActivityDetailBinding
import jp.cordea.kompas.main.MainListItemViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: DetailAdapter

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setSupportActionBar(binding.toolbar)

        binding.recyclerView.adapter = adapter
        val model = intent.getParcelableExtra<MainListItemViewModel>(MODEL_KEY)
        supportActionBar!!.title = model.title

        adapter.updateDescription(DescriptionListItemViewModel.from(model))
        adapter.updateInfo(InfoListItemViewModel.from(model))
    }

    companion object {
        private const val MODEL_KEY = "MODEL_KEY"

        fun newIntent(context: Context, model: MainListItemViewModel) =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(MODEL_KEY, model)
                }
    }
}
