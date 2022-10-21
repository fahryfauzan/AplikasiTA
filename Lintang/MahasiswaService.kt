package com.lintangprayogo.aplikasisidang.core.api


import com.lintangprayogo.aplikasisidang.core.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface MahasiswaService {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @PUT(MahasiswaUrl.URL_MAHASISWA_PROFILE)
    suspend fun updateProfileMahasiswa(
        @Header("Authorization") authHeader: String,
        @Field("mhs_nama") nama: String,
        @Field("mhs_email") email: String,
        @Field("mhs_kontak") kontak: String,


        ): BaseModel<Mahasiswa>

    @Headers("Accept: application/json")
    @Multipart
    @POST(MahasiswaUrl.URL_MAHASISWA_PHOTO)
    suspend fun updateProfileMahasiswa(
        @Header("Authorization") authHeader: String,
        @Part photoFile: MultipartBody.Part
    ): BaseModel<Mahasiswa>


    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_PREDIKSI_SIDANG)
    suspend fun getPrediksiSidangMahasiswa(
        @Header("Authorization") authHeader: String,
    ): BaseModel<List<PrediksiSidang>>

    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_FORM_SK)
    suspend fun getMhsFormSk(
        @Header("Authorization") authHeader: String,
    ): BaseModel<List<FormSK>>

    @Headers("Accept: application/json")
    @POST(MahasiswaUrl.URL_MAHASISWA_FORM_SK + "/extend")
    @Multipart
    suspend fun mhsFormSkExtend(
        @Header("Authorization") authHeader: String,
        @Part("judul_indonesia") judulIndo: RequestBody,
        @Part("judul_inggris") judulInggris: RequestBody,
        @Part("alasan") alasan: RequestBody,
        @Part("mhs_nim") nim: RequestBody,
        @Part("sk_dsn_nama_1") skDsnNama1: RequestBody,
        @Part("sk_dsn_nama_2") skDsnNama2: RequestBody,
        @Part("sk_nip_1") formSkNip1: RequestBody,
        @Part("sk_nip_2") formSkNip2: RequestBody,
        @Part skFile: MultipartBody.Part
    ): BaseModel<FormSK>

    @Headers("Accept: application/json")
    @POST(MahasiswaUrl.URL_MAHASISWA_FORM_SK + "/change")
    @Multipart
    suspend fun mhsFormRubahSk(
        @Header("Authorization") authHeader: String,
        @Part("judul_indonesia") judulIndo: RequestBody,
        @Part("judul_inggris") judulInggris: RequestBody,
        @Part("judul_indonesia_new") judulIndoNew: RequestBody,
        @Part("judul_inggris_new") judulInggrisNew: RequestBody,
        @Part("alasan") alasan: RequestBody,
        @Part("mhs_nim") nim: RequestBody,
        @Part("sk_dsn_nama_1") skDsnNama1: RequestBody,
        @Part("sk_dsn_nama_2") skDsnNama2: RequestBody,
        @Part("sk_nip_1") formSkNip1: RequestBody,
        @Part("sk_nip_2") formSkNip2: RequestBody,
        @Part("sk_dsn_nama_new_1") skDsnNamaNew1: RequestBody,
        @Part("sk_dsn_nama_new_2") skDsnNamaNew2: RequestBody,
        @Part("sk_nip_new_1") formSkNipNew1: RequestBody,
        @Part("sk_nip_new_2") formSkNipNew2: RequestBody,
        @Part skFile: MultipartBody.Part
    ): BaseModel<FormSK>


    @Streaming
    @GET(MahasiswaUrl.URL_MAHASISWA_TUGAS_AKHIR + "/download")
    suspend fun downloadMhsSK(
        @Header("Authorization") authHeader: String,
    ): ResponseBody


    @Headers("Accept: application/json")
    @POST(MahasiswaUrl.URL_MAHASISWA_DAFTAR_SIDANG)
    @Multipart
    suspend fun mhsDaftarSidang(
        @Header("Authorization") authHeader: String,
        @Part("periode_id") judulIndo: RequestBody,
        @Part draftFile: MultipartBody.Part
    ): BaseModel<Sidang>

    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_DAFTAR_SIDANG)
    suspend fun getMhsDaftarSidang(
        @Header("Authorization") authHeader: String,
    ): BaseModel<List<Sidang>>

    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_DAFTAR_SIDANG + "/create")
    suspend fun getMhsCreateSidang(
        @Header("Authorization") authHeader: String,
    ): BaseModel<SidangMahasiswaCreate>

    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_TUGAS_AKHIR)
    suspend fun getTugasAkhir(@Header("Authorization") authHeader: String): BaseModel<TugasAkhir>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(ApiURl.URL_BIMBINGAN)
    suspend fun postBimbingan(
        @Header("Authorization") authHeader: String,
        @Field("bimbingan_review") bimbinganReview: String,
        @Field("bimbingan_tanggal") bimbinganTanggal: String,
        @Field("mhs_nim") mhsNim: String,
        @Field("dsn_nip") dsnNip: String,
    ): BaseModel<Bimbingan>


    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT(ApiURl.URL_BIMBINGAN + "/{id}")
    suspend fun updateBimbingan(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
        @Field("bimbingan_review") bimbinganReview: String,
        @Field("bimbingan_tanggal") bimbinganTanggal: String,

        ): BaseModel<Bimbingan>


    @Headers("Accept: application/json")
    @DELETE(ApiURl.URL_BIMBINGAN + "/{id}")
    suspend fun deleteBimbingan(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
    ): BaseModel<Bimbingan>


    @Headers("Accept: application/json")
    @GET(ApiURl.URL_BIMBINGAN)
    suspend fun getBimbingan(
        @Header("Authorization") authHeader: String,
        @Query("dsn_nip") dsnNip: String,
        @Query("mhs_nim") mhsNim: String
    ): BaseModel<List<Bimbingan>>


    @Headers("Accept: application/json")
    @POST(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/revisi/{id}")
    @Multipart
    suspend fun uploadRevisi(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
        @Part skFile: MultipartBody.Part
    ): BaseModel<Sidang>


    @Headers("Accept: application/json")
    @GET(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/{id}")
    suspend fun mhsSidangById(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): BaseModel<Sidang>

    @Streaming
    @GET(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/{id}")
    suspend fun mhsBeritaAcara(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @Streaming
    @GET(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/jurnal/{id}")
    suspend fun mhsDownloadJurnal(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @Streaming
    @GET(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/revisi/{id}")
    suspend fun mhsDownloadRevisi(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>


    @Streaming
    @GET(MahasiswaUrl.URL_MAHASISWA_SIDANG + "/catatan_revisi/{id}")
    suspend fun mhsCatatanRevisi(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

}
