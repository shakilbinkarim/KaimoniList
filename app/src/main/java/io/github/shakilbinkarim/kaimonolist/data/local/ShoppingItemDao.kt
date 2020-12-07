package io.github.shakilbinkarim.kaimonolist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun viewAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM  shopping_items")
    fun viewTotalPrice() : LiveData<Float>

}