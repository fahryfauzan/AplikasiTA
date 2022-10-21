package org.d3ifcool.finpro.core.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl
import org.d3ifcool.finpro.core.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Bimbingan
    // ---------------------------------------------------------------------------------------------
    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN)
    fun createBimbingan(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Field("bimbingan_review") bimbingan_review: String?,
        @Field("bimbingan_tanggal") bimbingan_tanggal: String?,
        @Field("dsn_nip") dsn_nip: String?,
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_BIMBINGAN)
    fun updateBimbingan(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: Int?,
        @Field("bimbingan_review") bimbingan_review: String?,
        @Field("bimbingan_tanggal") bimbingan_tanggal: String?
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_BIMBINGAN)
    fun getAllBimbingan(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    @GET(FinproUrl.URL_BIMBINGAN+FinproUrl.PARAMETER_BIMBINGAN)
    fun getBimbinganByParameter(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_BIMBINGAN) username: String?
    ): Call<ResponseBody>?

    @GET(FinproUrl.URL_BIMBINGAN)
    fun getBimbingan(): Call<List<Bimbingan?>?>?

    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_BIMBINGAN)
    fun deleteBimbingan(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: Int?
    ): Call<Bimbingan?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_BIMBINGAN + FinproUrl.PATH_UPDATE + FinproUrl.PATH_STATUS + FinproUrl.PARAMETER_BIMBINGAN)
    fun updateBimbinganStatus(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_BIMBINGAN) bimbingan_id: Int?,
        @Field("bimbingan_status") bimbingan_status: String?
    ): Call<Bimbingan?>?

    // Dosen
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_DOSEN)
    fun createDosen(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("dsn_nip") dsn_nip: String?,
            @Field("dsn_nama") dsn_nama: String?,
            @Field("dsn_kode") dsn_kode: String?
    ): Call<Dosen?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_DOSEN + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_DOSEN)
    fun updateDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?,
        @Field("dsn_nama") dsn_nama: String?,
        @Field("dsn_kode") dsn_kode: String?,
        @Field("dsn_kontak") dsn_kontak: String?,
        @Field("dsn_email") dsn_email: String?,
        @Field("kuota_bimbingan") kuota_bimbingan: Int?,
        @Field("kuota_reviewer")kuota_reviewer: Int?
    ): Call<Dosen?>?

    @POST(FinproUrl.URL_DOSEN + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_DOSEN)
    fun deleteDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?
    ): Call<Dosen?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_DOSEN + FinproUrl.PATH_ALL)
    fun getAllDosen(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Dosen?>?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_DOSEN)
    fun getDosen(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<Dosen?>?

    @GET(FinproUrl.URL_DOSEN + FinproUrl.PARAMETER_DOSEN)
    fun getDosenByParameter(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) dsn_nip: String?
    ): Call<Dosen?>?

    @GET(FinproUrl.URL_DOSEN + FinproUrl.PATH_MAHASISWA + FinproUrl.PATH_SIDANG)
    fun getMahasiswaSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Mahasiswa?>?>?


    @GET(FinproUrl.URL_DOSEN + FinproUrl.PATH_MAHASISWA + FinproUrl.PATH_SIDANG + FinproUrl.PARAMETER_DOSEN)
    fun getMahasiswaSidangByUsername(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_DOSEN) mhs_nim: String?
    ): Call<Mahasiswa?>?

    // Plotting
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_PLOTTING)
    fun createPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("nip_pembimbing_1") nip_pembimbing_1: String?,
            @Field("nip_pembimbing_2") nip_pembimbing_2: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_PLOTTING)
    fun updatePlotting(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_PLOTTING) plot_id: Int,
        @Field("nip_pembimbing_1") nip_pembimbing_1: String?,
        @Field("nip_pembimbing_2") nip_pembimbing_2: String?
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_PLOTTING)
    fun getPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Plotting?>?>?

    @GET(FinproUrl.URL_PLOTTING + FinproUrl.PATH_PEMBIMBING + FinproUrl.PARAMETER_PLOTTING)
    fun getPembimbing(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_PLOTTING) id: Int?
    ): Call<Plotting?>?

    @GET(FinproUrl.URL_PLOTTING + FinproUrl.PARAMETER_PLOTTING)
    fun getPlottingByParamter(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_PLOTTING) plot_id: Int?
    ): Call<Plotting?>?

    @Headers("Connection:close")
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_PLOTTING)
    fun deletePlotting(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_PLOTTING) plotting_id: Int
    ): Call<ResponseBody?>?

    @Multipart
    @POST(FinproUrl.URL_PLOTTING + FinproUrl.PATH_UPLOAD)
    fun uploadFormPlotting(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Multipart
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPLOAD + FinproUrl.PATH_PENGAJUAN + FinproUrl.PATH_PERPANJANGSK + FinproUrl.PARAMETER_MAHASISWA)
    fun uploadFormPengajuanPerpanjangSK(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) username: String?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Multipart
    @POST(FinproUrl.URL_UPLOADFORMSIDANG)
    fun uploadFormSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_CHECKFORMPLOT)
    fun checkFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    @Headers("Connection:close")
    @Streaming
    @GET(FinproUrl.URL_DOWNLOADFORMPLOT)
    fun downloadFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_DELETEFORMPLOT)
    fun deleteFormPlot(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
    ): Call<ResponseBody>?

    // Informasi
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_INFORMASI)
    fun createInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("informasi_judul") informasi_judul: String?,
            @Field("informasi_isi") informasi_isi: String?,
            @Field("penerbit") penerbit: String?
    ): Call<Informasi?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_INFORMASI + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_INFORMASI)
    fun updateInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_INFORMASI) informasi_id: Int,
            @Field("informasi_judul") informasi_judul: String?,
            @Field("informasi_isi") informasi_isi: String?
    ): Call<Informasi?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_INFORMASI)
    fun getInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Informasi?>?>?

    // Ga perlu pake @FormUrlEncoded karena tidak menggunakan field
    @POST(FinproUrl.URL_INFORMASI + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_INFORMASI)
    fun deleteInformasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_INFORMASI) informasi_id: Int
    ): Call<Informasi?>?

    // Jadwal
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_JADWAL)
    fun createJadwal(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Field("nama_kegiatan") nama_kegiatan: String?,
        @Field("tanggal_mulai") tanggal_mulai: String?,
        @Field("tanggal_berakhir") tanggal_berakhir: String?
    ): Call<JadwalKegiatan?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_JADWAL + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_JADWAL)
    fun updateJadwal(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_JADWAL) jadwal_id: Int,
        @Field("nama_kegiatan") nama_kegiatan: String?,
        @Field("tanggal_mulai") tanggal_mulai: String?,
        @Field("tanggal_akhir") tanggal_akhir: String?
    ): Call<JadwalKegiatan?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_JADWAL)
    fun getJadwal(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<JadwalKegiatan?>?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_JADWAL + FinproUrl.PARAMETER_JADWAL)
    fun getJadwalByLike(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_JADWAL) like: String
    ): Call<List<JadwalKegiatan?>?>?

    // Ga perlu pake @FormUrlEncoded karena tidak menggunakan field
    @POST(FinproUrl.URL_JADWAL + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_JADWAL)
    fun deleteJadwal(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_JADWAL) jadwal_id: Int
    ): Call<JadwalKegiatan?>?

     // Koor
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_KOORDINATOR_PA)
    fun createKoor(
            @Field("koor_nip") koor_nip: String?,
            @Field("koor_nama") koor_nama: String?,
            @Field("koor_kontak") koor_kontak: String?,
            @Field("koor_foto") koor_foto: String?,
            @Field("koor_email") koor_email: String?
    ): Call<Koordinator?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_KOOR)
    fun updateKoor(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_KOORDINATOR_PA) username: String?,
        @Field("koor_nip") koor_nip: String?,
        @Field("koor_nama") koor_nama: String?,
        @Field("koor_kode") koor_kode: String?,
        @Field("koor_kontak") koor_kontak: String?,
        @Field("koor_email") koor_email: String?
    ): Call<Koordinator?>?

    @GET(FinproUrl.URL_KOORDINATOR_PA)
    fun getKoor(): Call<List<Koordinator?>?>?

    @POST(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_KOOR)
    fun deleteKoor(@Path(FinproUrl.VAR_KOORDINATOR_PA) koor_nip: String?): Call<Koordinator?>?

    @GET(FinproUrl.URL_KOORDINATOR_PA + FinproUrl.PARAMETER_KOOR)
    fun getKoorByParameter(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_KOORDINATOR_PA) username_koor: String?
    ): Call<Koordinator?>?

    // Mahasiswa
    // ---------------------------------------------------------------------------------------------

    @Headers("Connection: close")
    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA)
    fun createMahasiswa(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("mhs_nim") mhs_nim: String?,
            @Field("mhs_nama") mhs_nama: String?
    ): Call<Mahasiswa?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_MAHASISWA)
    fun updateMahasiswa(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_MAHASISWA) nim_mhs: String?,
            @Field("mhs_nama") mhs_nama: String?,
            @Field("mhs_angkatan") angkatan: String?,
            @Field("mhs_kontak") mhs_kontak: String?,
            @Field("mhs_email") mhs_email: String?,
            @Field("judul") judul: String?,
            @Field("judul_inggris") judul_inggris: String?
    ): Call<Mahasiswa?>?

    @Headers("Connection: close")
    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + "/" + FinproUrl.VAR_PEMBIMBING + FinproUrl.PARAMETER_MAHASISWA)
    fun addPembimbing(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("plot_id") plot_id: Int?,
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
    ): Call<Mahasiswa?>?

    @Headers("Connection: close")
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_DELETE + "/" + FinproUrl.VAR_PEMBIMBING + FinproUrl.PARAMETER_MAHASISWA)
    fun deletePembimbing(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
    ): Call<ResponseBody?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_MAHASISWA)
    fun getMahasiswa(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<List<Mahasiswa?>?>?

    @Headers("Connection: close")
    @GET(FinproUrl.URL_ASKSIDANG)
    fun askSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ): Call<ResponseBody>?

    @Headers("Connection: close")
    @FormUrlEncoded
    @POST(FinproUrl.URL_KONFIRMASISIDANG)
    fun konfirmasiSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Field("konfirmasi") konfirmasi: String?
    ): Call<ResponseBody>?

    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_MAHASISWA)
    fun deleteMahasiswa(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?
    ): Call<Mahasiswa?>?

    @GET(FinproUrl.URL_MAHASISWA + FinproUrl.PARAMETER_MAHASISWA)
    fun getMahasiswaByParameter(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) username_mhs: String?
    ): Call<Mahasiswa?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PATH_JUDUL + FinproUrl.PARAMETER_MAHASISWA)
    fun updateJudulMahasiswa(
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?,
            @Field("judul_id") judul: Int
    ): Call<Mahasiswa?>?

    @POST(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_UPDATE + FinproUrl.PATH_SKTA + FinproUrl.PARAMETER_MAHASISWA)
    fun updateSKTA(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_MAHASISWA + FinproUrl.PATH_DOWNLOAD + FinproUrl.PATH_SKTA + FinproUrl.PARAMETER_MAHASISWA)
    fun downloadSKTA(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_MAHASISWA) mhs_nim: String?
    ): Call<ResponseBody?>?

    // Notifikasi
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_NOTIFIKASI)
    fun createNotifikasi(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
            @Field("notifikasi_kategori") notifikasi_kategori: String?,
            @Field("notifikasi_deskripsi") notifikasi_deskripsi: String?,
            @Field("notifikasi_dari") notifikasi_dari: String?,
            @Field("notifikasi_untuk") notifikasi_untuk: String?
    ): Call<Notifikasi?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_NOTIFIKASI)
    fun updateNotifikasi(
            @Path(FinproUrl.VAR_NOTIFIKASI) notifikasi_id: Int,
            @Field("notifikasi_baca") notifikasi_baca: Int
    ): Call<Notifikasi?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchNotifikasiBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchNotifikasiBy2(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_SORT + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.PARAMETER_QUERY_2)
    fun sortNotifikasi(
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Notifikasi?>?>?

    @GET(FinproUrl.URL_NOTIFIKASI)
    fun getNotifikasi(): Call<List<Notifikasi?>?>?

    @POST(FinproUrl.URL_NOTIFIKASI + FinproUrl.PATH_DELETE + FinproUrl.PARAMETER_NOTIFIKASI)
    fun deleteNotifikasi(@Path(FinproUrl.VAR_NOTIFIKASI) notifikasi_id: Int): Call<Notifikasi?>?

    // Sidang
    // ---------------------------------------------------------------------------------------------

    @GET(FinproUrl.URL_SIDANG)
    fun getSidang(): Call<List<Sidang?>?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_NILAI + FinproUrl.PARAMETER_SIDANG)
    fun saveNilaiSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?,
        @Field("clo1") clo1: String?,
        @Field("clo2") clo2: String,
        @Field("clo3") clo3: String,
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_REVIEW + FinproUrl.PARAMETER_SIDANG)
    fun saveReviewSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?,
        @Field("review") review: String?,
        @Field("status") status: String?,
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_UPDATE + FinproUrl.PATH_STATUS + FinproUrl.PARAMETER_SIDANG)
    fun updateStatusSidang(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?,
        @Field("status") status: String?,
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_NILAI + FinproUrl.PARAMETER_SIDANG)
    fun getSidangByNIM(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?
    ): Call<Sidang?>?

    @Multipart
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_UPLOAD + FinproUrl.PATH_REVISI + FinproUrl.PARAMETER_SIDANG)
    fun uploadFormRevisi(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Multipart
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_UPLOAD + FinproUrl.PATH_JURNAL + FinproUrl.PARAMETER_SIDANG)
    fun uploadDraftJurnal(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @Headers("Connection:close")
    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_CHECK + FinproUrl.PATH_REVISI + FinproUrl.PARAMETER_SIDANG)
    fun checkFormRevisi(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?
    ): Call<ResponseBody>?

    @Headers("Connection:close")
    @Streaming
    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_DOWNLOAD + FinproUrl.PATH_REVISI + FinproUrl.PARAMETER_SIDANG)
    fun downloadFormRevisi(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?,
        @Path(FinproUrl.VAR_SIDANG) mhs_nim: String?
    ): Call<ResponseBody>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG + FinproUrl.PATH_UPDATE + FinproUrl.PARAMETER_SIDANG)
    fun updateSidang(
            @Path(FinproUrl.VAR_SIDANG) sidang_id: String?,
            @Field("sidang_review") sidang_review: String?,
            @Field("sidang_tanggal") sidang_tanggal: String?,
            @Field("nilai_proposal") nilai_proposal: Int,
            @Field("nilai_penguji_1") nilai_penguji_1: Int,
            @Field("nilai_penguji_2") nilai_penguji_2: Int,
            @Field("nilai_pembimbing") nilai_pembimbing: Int,
            @Field("nilai_total") nilai_total: Double,
            @Field("sidang_status") sidang_status: String?
    ): Call<Sidang?>?

    @FormUrlEncoded
    @POST(FinproUrl.URL_SIDANG)
    fun deleteSidang(
            @Path("sidang") sidang_id: String?
    ): Call<Sidang?>?

    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER + FinproUrl.PARAMETER_QUERY)
    fun searchAllSidangBy(
            @Path(FinproUrl.VAR_PARAMS) parameter: String?,
            @Path(FinproUrl.VAR_QUERY) query: String?
    ): Call<List<Sidang?>?>?

    @GET(FinproUrl.URL_SIDANG + FinproUrl.PATH_SEARCH + FinproUrl.PATH_ALL + FinproUrl.BASE_PARAMETER_1 + FinproUrl.PARAMETER_QUERY_1 + FinproUrl.BASE_PARAMETER_2 + FinproUrl.PARAMETER_QUERY_2)
    fun searchAllSidangByTwo(
            @Path(FinproUrl.VAR_PARAMS + "1") parameter1: String?,
            @Path(FinproUrl.VAR_QUERY + "1") query1: String?,
            @Path(FinproUrl.VAR_PARAMS + "2") parameter2: String?,
            @Path(FinproUrl.VAR_QUERY + "2") query2: String?
    ): Call<List<Sidang?>?>?

    // User
    // ---------------------------------------------------------------------------------------------

    @FormUrlEncoded
    @POST(FinproUrl.URL_LOGIN)
    fun setLogin(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Call<ResponseBody?>?

    @GET(FinproUrl.URL_USER)
    fun getCurrentUser(
        @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ):Call<ResponseBody>?

    @POST(FinproUrl.URL_LOGOUT)
    fun logout(
            @Header(FinproUrl.VAR_AUTHORIZATION) token: String?
    ):Call<ResponseBody>?
}
