package com.lintangprayogo.aplikasisidang.core.api

import com.lintangprayogo.aplikasisidang.core.models.BaseModel
import com.lintangprayogo.aplikasisidang.core.models.NilaiSidang
import com.lintangprayogo.aplikasisidang.core.models.Sidang
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface DosenService {
    @Headers("Accept: application/json")
    @GET(DosenURl.URL_DOSEN_SIDANG)
    suspend fun getDsnDaftarSidang(
        @Header("Authorization") authHeader: String,
    ): BaseModel<List<Sidang>>

    @Headers("Accept: application/json")
    @GET(DosenURl.URL_DOSEN_SIDANG + "/accept/{id}")
    suspend fun dosenSidangAccept(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): BaseModel<Sidang>


    @Headers("Accept: application/json")
    @GET(DosenURl.URL_DOSEN_SIDANG + "/reject/{id}")
    suspend fun dosenSidangReject(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): BaseModel<Sidang>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(DosenURl.URL_DOSEN_SIDANG + "/nilai/{id}")
    suspend fun dosenNilaiSidang(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
        @Field("nilai_laporan")
        nilaiLaporan: Int,
        @Field("nilai_presentasi")
        nilaiPresentasi: Int,
        @Field("nilai_produk")
        nilaiProduk: Int,
        @Field("catatan_revisi")
        catatanRevisi:String
    ): BaseModel<NilaiSidang>


    @Headers("Accept: application/json")
    @GET(DosenURl.URL_DOSEN_SIDANG + "/{id}")
    suspend fun dosenSidangById(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): BaseModel<Sidang>

    @Streaming
    @GET(DosenURl.URL_DOSEN_SIDANG + "/{id}")
    suspend fun dsnBeritaAcara(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @Streaming
    @GET(DosenURl.URL_DOSEN_SIDANG + "/jurnal/{id}")
    suspend fun dsnDownloadJurnal(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @Streaming
    @GET(DosenURl.URL_DOSEN_SIDANG + "/revisi/{id}")
    suspend fun dsnDownloadRevisi(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

}
