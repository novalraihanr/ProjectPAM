package com.example.projectpam.viewModel

import android.media.Image
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.http.Multipart
import retrofit2.http.Part

interface Fileapi {

    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )

    companion object{
        val instance by lazy {
            Retrofit.Builder()
                .baseUrl("http://192.168.1.7")
                .build()
                .create(Fileapi::class.java)
        }
    }
}