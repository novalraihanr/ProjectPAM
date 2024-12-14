package com.example.projectpam.viewModel

import coil3.network.HttpException
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.HTTP
import java.io.File
import java.io.IOException

class FileRepository {
    suspend fun uploadImage(file: File) : Boolean {
        return try {
            Fileapi.instance.uploadImage(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            )
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e : HttpException) {
            e.printStackTrace()
            false
        }
    }
}