package io.github.shakilbinkarim.kaimonolist.repos

import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItemDao
import io.github.shakilbinkarim.kaimonolist.data.remote.response.PixabayAPI
import javax.inject.Inject

class DefaultShoppingRepo @Inject constructor(
    private val shoppingItemDao: ShoppingItemDao,
    private val pixabayAPI: PixabayAPI
) {

}