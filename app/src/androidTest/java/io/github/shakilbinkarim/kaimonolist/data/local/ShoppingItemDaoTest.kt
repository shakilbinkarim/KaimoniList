package io.github.shakilbinkarim.kaimonolist.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.shakilbinkarim.kaimonolist.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingItemDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingItemDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ShoppingItemDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.shoppingItemDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("fish", 1, 300f, "unavailable", 1)
        dao.insertItem(shoppingItem)
        val allShoppingItems = dao.viewAllItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("Slippers", 3, 100f, "unavailable, 2")
        dao.insertItem(shoppingItem)
        dao.deleteItem(shoppingItem)
        val allShoppingItems = dao.viewAllItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun viewTotalPrice() = runBlockingTest {
        val slipperUnitPrice = 100f
        val slipperQty = 3
        val appleUnitPrice = 200f
        val appleQty = 2
        val shoppingItem1 = ShoppingItem("Slippers", slipperQty, slipperUnitPrice, "unavailable", 2)
        val shoppingItem2 = ShoppingItem("Apples", appleQty, appleUnitPrice, "unavailable", 3)
        dao.insertItem(shoppingItem1)
        dao.insertItem(shoppingItem2)
        val result = dao.viewTotalPrice().getOrAwaitValue()
        val expectedPrice = (slipperUnitPrice * slipperQty) + (appleUnitPrice * appleQty)
        assertThat(result).isEqualTo(expectedPrice)
    }

}