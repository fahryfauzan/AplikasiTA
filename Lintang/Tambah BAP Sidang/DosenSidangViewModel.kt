package com.lintangprayogo.aplikasisidang.core.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lintangprayogo.aplikasisidang.core.repo.DosenSidangRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DosenSidangViewModel @Inject constructor(private val repo: DosenSidangRepo) :
    ViewModel() {

    fun getDaftarSidang(token: String) = repo.getDaftarSidang(token).asLiveData()
    fun rejectSidang(token: String, id: Int) = repo.rejectSidang(token, id).asLiveData()
    fun acceptSidang(token: String, id: Int) = repo.acceptSidang(token, id).asLiveData()
    fun nilaiSidang(
        token: String, id: Int, nilaiLaporan: Int,
        nilaiPresentasi: Int, nilaiProduk: Int,catatan:String
    ) = repo.nilaiSidang(token, id, nilaiLaporan, nilaiPresentasi, nilaiProduk,catatan).asLiveData()


    fun downloadRevisi(token: String, id: Int) =
        repo.downloadRevisi(token, id).asLiveData()

    fun downloadJurnal(token: String, id: Int) =
        repo.downloadJurnal(token, id).asLiveData()

    fun downloadBeritaAcara(token: String, id: Int) =
        repo.downloadBeritaAcara(token, id).asLiveData()

    fun getSidangById(token: String, id: Int) = repo.getSidangById(token, id).asLiveData()

}
