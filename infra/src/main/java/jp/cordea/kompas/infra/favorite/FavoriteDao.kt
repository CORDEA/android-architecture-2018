package jp.cordea.kompas.infra.favorite

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE id = :id LIMIT 1")
    fun getFavorite(id: Int): Maybe<Favorite>
}
