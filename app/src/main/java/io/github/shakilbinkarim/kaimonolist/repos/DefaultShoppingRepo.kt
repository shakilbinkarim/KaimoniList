package io.github.shakilbinkarim.kaimonolist.repos

import androidx.lifecycle.LiveData
import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItem
import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItemDao
import io.github.shakilbinkarim.kaimonolist.data.remote.response.ImageResponse
import io.github.shakilbinkarim.kaimonolist.data.remote.response.PixabayAPI
import io.github.shakilbinkarim.kaimonolist.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepo @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
    private val pixabayAPI: PixabayAPI
): ShoppingRepo {

    companion object {
        private const val UNKNOWN_ERROR_MESSAGE = "Unknown Error Occurred!"
    }

    override suspend fun insertItem(item: ShoppingItem) = shoppingItemDao.insertItem(item)

    override suspend fun deleteItem(item: ShoppingItem) = shoppingItemDao.deleteItem(item)

    override fun viewAllItems(): LiveData<List<ShoppingItem>> = shoppingItemDao.viewAllItems()

    override fun viewTotalPrice(): LiveData<Float> = shoppingItemDao.viewTotalPrice()

    override suspend fun searchImage(query: String): Resource<ImageResponse> = try {
        val response = pixabayAPI.searchImage(query)
        if (response.isSuccessful){
            response.body()?.let{
                return@let Resource.onSuccess(it)
            } ?: Resource.onError(UNKNOWN_ERROR_MESSAGE, null)
        } else {
            Resource.onError(UNKNOWN_ERROR_MESSAGE, null)
        }
    } catch (e:Exception) {
        Resource.onError(e.toString(), null)
    }

}