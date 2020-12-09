package io.github.shakilbinkarim.kaimonolist.repos

import androidx.lifecycle.LiveData
import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItem
import io.github.shakilbinkarim.kaimonolist.data.remote.response.ImageResponse
import io.github.shakilbinkarim.kaimonolist.utils.Resource

interface ShoppingRepo {

    suspend fun insertItem(item: ShoppingItem)

    suspend fun deleteItem(item: ShoppingItem)

    fun viewAllItems(): LiveData<List<ShoppingItem>>

    fun viewTotalPrice(): LiveData<Float>

    suspend fun searchImage(query: String): Resource<ImageResponse>

}