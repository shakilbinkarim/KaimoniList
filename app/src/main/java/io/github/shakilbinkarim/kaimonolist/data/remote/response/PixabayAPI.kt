package io.github.shakilbinkarim.kaimonolist.data.remote.response

import io.github.shakilbinkarim.kaimonolist.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ) : Response<ImageResponse>

}