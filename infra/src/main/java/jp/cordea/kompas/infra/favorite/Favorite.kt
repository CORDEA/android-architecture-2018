package jp.cordea.kompas.infra.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorite(
        @PrimaryKey val id: Int,
        val addedAt: String
)
