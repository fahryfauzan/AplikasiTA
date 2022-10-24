package com.lintangprayogo.aplikasisidang.core.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lintangprayogo.aplikasisidang.core.repo.MahasiswaTugasAkhirRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TugasAkhirViewModel @Inject constructor(private val repo: MahasiswaTugasAkhirRepo) :
    ViewModel() {

    fun getTugasAkhir(token: String, shouldFetch: Boolean = true) =
        repo.getTugasAkhir(token).asLiveData()

    fun downloadSK(
        token: String
    ) = repo.downloadSK(token).asLiveData()


}
