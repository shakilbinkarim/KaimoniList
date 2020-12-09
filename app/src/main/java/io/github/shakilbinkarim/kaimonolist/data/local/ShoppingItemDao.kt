package io.github.shakilbinkarim.kaimonolist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteItem(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun viewAllItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(price * amount) FROM  shopping_items")
    fun viewTotalPrice() : LiveData<Float>

}