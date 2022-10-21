package com.lintangprayogo.aplikasisidang.core.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lintangprayogo.aplikasisidang.core.repo.MahasiswaBimbinganRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MahasiswaBimbinganViewModel @Inject constructor(private val repo: MahasiswaBimbinganRepo) :
    ViewModel() {

    fun postBimbingan(
        token: String,
        bimbinganReview: String,
        bimbinganTanggal: String,
        mhsNim: String,
        dsnNip: String,
    ) = repo.postBimbingan(token, bimbinganReview, bimbinganTanggal, mhsNim, dsnNip).asLiveData()

    fun getBimbingan(token: String, dsnNip: String, mhsNim: String) =
        repo.getBimbingan(token, dsnNip, mhsNim).asLiveData()


    fun updateBimbingan(
        token: String,
        id: Int,
        bimbinganReview: String,
        bimbinganTanggal: String,

        ) = repo.updateBimbingan(token, id, bimbinganReview, bimbinganTanggal).asLiveData()


    fun deleteBimbingan(
        token: String,
        id: Int

    ) = repo.deleteBimbingan(token, id).asLiveData()


}
