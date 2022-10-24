package com.lintangprayogo.aplikasisidang.core.repo

import androidx.room.withTransaction
import com.lintangprayogo.aplikasisidang.core.api.MahasiswaService
import com.lintangprayogo.aplikasisidang.core.db.SidangDatabase
import com.lintangprayogo.aplikasisidang.utils.Resource
import com.lintangprayogo.aplikasisidang.utils.networkBoundResource
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class MahasiswaTugasAkhirRepo @Inject constructor(
    private val mahasiswaService: MahasiswaService,
    private val sidangDatabase: SidangDatabase
) {
    private val tugasAkhirDao = sidangDatabase.tugasAkhirDao()
    fun getTugasAkhir(token: String, shouldFetch: Boolean = true) = networkBoundResource(query = {
        tugasAkhirDao.getSingle()
    }, fetch = { mahasiswaService.getTugasAkhir(token) },
        saveFetchResult = {
            sidangDatabase.withTransaction {
                tugasAkhirDao.deleteAllSK()
                tugasAkhirDao.insertTugasAkhir(it.data)
            }
        }, shouldFetch = shouldFetch
    )

    fun downloadSK(token: String) = flow<Resource<ResponseBody>> {
        emit(Resource.Loading())

        try {
            val result = mahasiswaService.downloadMhsSK(token)
            emit(Resource.Success(result))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))
        }

    }


}
