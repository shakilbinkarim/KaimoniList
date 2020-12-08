package io.github.shakilbinkarim.kaimonolist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.shakilbinkarim.kaimonolist.data.local.ShoppingItemDatabase
import io.github.shakilbinkarim.kaimonolist.data.remote.response.PixabayAPI
import io.github.shakilbinkarim.kaimonolist.utils.Constants.DATABASE_NAME
import io.github.shakilbinkarim.kaimonolist.utils.Constants.PIXABAY_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingItemDao(database: ShoppingItemDatabase) = database.shoppingItemDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(PIXABAY_BASE_URL)
        .build()
        .create(PixabayAPI::class.java)

}