package io.github.shakilbinkarim.kaimonolist.repos

import androidx.lifecycle.LiveData
import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItem
import io.github.shakilbinkarim.kaimonolist.data.remote.response.ImageResponse
import retrofit2.Response

interface ShoppingRepo {

    suspend fun insertItem(item: ShoppingItem)

    suspend fun deleteItem(item: ShoppingItem)

    fun viewAllItems(): LiveData<List<ShoppingItem>>

    fun viewTotalPrice(): LiveData<Float>

    suspend fun searchImage(query: String): Response<ImageResponse>

}