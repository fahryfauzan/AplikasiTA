package com.lintangprayogo.aplikasisidang.core.repo

import androidx.room.withTransaction
import com.lintangprayogo.aplikasisidang.core.api.MahasiswaService
import com.lintangprayogo.aplikasisidang.core.db.SidangDatabase
import com.lintangprayogo.aplikasisidang.core.models.Bimbingan
import com.lintangprayogo.aplikasisidang.utils.Resource
import com.lintangprayogo.aplikasisidang.utils.networkBoundResource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MahasiswaBimbinganRepo @Inject constructor(
    private val mahasiswaService: MahasiswaService,
    private val sidangDatabase: SidangDatabase
) {
    private val bimbinganDao = sidangDatabase.bimbinganDao()

    fun postBimbingan(
        authHeader: String,
        bimbinganReview: String,
        bimbinganTanggal: String,
        mhsNim: String,
        dsnNip: String,
    ) = flow<Resource<Bimbingan>> {
        emit(Resource.Loading())
        try {
            val result = mahasiswaService.postBimbingan(
                authHeader,
                bimbinganReview,
                bimbinganTanggal,
                mhsNim,
                dsnNip
            )
            sidangDatabase.withTransaction {
                bimbinganDao.insertBimbingan(result.data)
            }
            emit(Resource.Success(result.data))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))

        }
    }

    fun getBimbingan(token: String, dsnNip: String, mhsNim: String, souldFetch: Boolean = true) =
        networkBoundResource(query = {
            bimbinganDao.getBimbingan(dsnNip, mhsNim)
        }, fetch = {
            mahasiswaService.getBimbingan(token, dsnNip, mhsNim)
        }, saveFetchResult = {
            sidangDatabase.withTransaction {
                bimbinganDao.deleteAllBimbingan(dsnNip, mhsNim)
                bimbinganDao.insertBimbingan(it.data)
            }
        }, shouldFetch = souldFetch)

    fun updateBimbingan(
        authHeader: String,
        id: Int,
        bimbinganReview: String,
        bimbinganTanggal: String,
    ) = flow<Resource<Bimbingan>> {
        emit(Resource.Loading())
        try {
            val result =
                mahasiswaService.updateBimbingan(authHeader, id, bimbinganReview, bimbinganTanggal)
            sidangDatabase.withTransaction {
                bimbinganDao.deleteAllBimbingan()
                bimbinganDao.insertBimbingan(result.data)
            }
            emit(Resource.Success(result.data))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))

        }
    }


    fun deleteBimbingan(
        authHeader: String,
        id: Int
    ) = flow<Resource<Bimbingan>> {
        emit(Resource.Loading())
        try {
            val result = mahasiswaService.deleteBimbingan(authHeader, id)
            sidangDatabase.withTransaction {
                bimbinganDao.deleteBimbinganByID(id)
            }
            emit(Resource.Success(result.data))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))

        }
    }


}
