package com.dedicated407.favoriteLiterature.Presentation.Repository.Network

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Writer
import retrofit2.Call
import retrofit2.Response


public class WriterAnalysis {
    private var api: IOpenLibraryAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(IOpenLibraryAPI::class.java)
    }

    fun getWriters(writerName: String): LiveData<List<Writer>> {
        val writers = MutableLiveData<List<Writer>>()

        api.getWritersByName(writerName).enqueue(object : Callback<WriterResponse> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<WriterResponse>,
                    response: Response<WriterResponse>,
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        writers.value = response.body()!!.docs
                            ?.asSequence()
                            ?.filter {
                                it.author_name != null &&
                                        it.author_name?.get(0)!!.contains(writerName)
                            }
                            ?.map {
                                    writer -> writer.author_name?.get(0)!!
                            }
                            ?.distinct()
                            ?.map {
                                    name -> Writer(name)
                            }
                            ?.toList()
                    }
                }

                override fun onFailure(
                    call: Call<WriterResponse>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }
            })

        return writers
    }

    fun getApi(): IOpenLibraryAPI {
        return api
    }
}