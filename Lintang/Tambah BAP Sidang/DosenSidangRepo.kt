package com.lintangprayogo.aplikasisidang.core.repo

import android.util.Log
import androidx.room.withTransaction
import com.lintangprayogo.aplikasisidang.core.api.DosenService
import com.lintangprayogo.aplikasisidang.core.db.SidangDatabase
import com.lintangprayogo.aplikasisidang.core.models.NilaiSidang
import com.lintangprayogo.aplikasisidang.core.models.Sidang
import com.lintangprayogo.aplikasisidang.utils.Resource
import com.lintangprayogo.aplikasisidang.utils.networkBoundResource
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class DosenSidangRepo @Inject constructor(
    private val dosenService: DosenService,
    private val sidangDatabase: SidangDatabase
) {

    private val sidangDao = sidangDatabase.sidangDao()
    private val nilaiSidangDao = sidangDatabase.nilaiSidangDao()


    fun getDaftarSidang(token: String) = networkBoundResource(query = {
        sidangDao.getSidang()
    }, fetch = {
        dosenService.getDsnDaftarSidang(token)
    }, saveFetchResult = {
        sidangDatabase.withTransaction {
            sidangDao.deleteAllSidang()
            sidangDao.insertSidang(it.data)
        }
    })

    fun acceptSidang(token: String, id: Int) = networkBoundResource(query = {
        sidangDao.getSidang()
    }, fetch = {
        dosenService.dosenSidangAccept(token, id)
    }, saveFetchResult = {
        sidangDatabase.withTransaction {
            sidangDao.insertSidang(it.data)
        }
    })

    fun rejectSidang(token: String, id: Int) = networkBoundResource(query = {
        sidangDao.getSidang()
    }, fetch = {
        dosenService.dosenSidangReject(token, id)
    }, saveFetchResult = {
        sidangDatabase.withTransaction {
            sidangDao.insertSidang(it.data)
        }
    })


    fun nilaiSidang(
        token: String, id: Int, nilaiLaporan: Int,
        nilaiPresentasi: Int, nilaiProduk: Int,catatan:String
    ) =
        flow<Resource<NilaiSidang>> {
            emit(Resource.Loading())
            try {
                val result =
                    dosenService.dosenNilaiSidang(
                        token,
                        id,
                        nilaiLaporan,
                        nilaiPresentasi,
                        nilaiProduk,
                        catatan
                    )
                sidangDatabase.withTransaction {
                    nilaiSidangDao.insertNilaiSidang(result.data)
                }
                emit(Resource.Success(result.data))
            } catch (throwable: Throwable) {
                emit(Resource.Error(throwable = throwable))

            }
        }

    fun getSidangById(token: String, id: Int) =
        flow<Resource<Sidang>> {
            emit(Resource.Loading())
            try {
                val result =
                    dosenService.dosenSidangById(token, id)
                sidangDatabase.withTransaction {
                    sidangDao.insertSidang(result.data)
                }
                Log.d("TAG", "getSidangById: " + result.toString())
                emit(Resource.Success(result.data))
            } catch (throwable: Throwable) {
                emit(Resource.Error(throwable = throwable))

            }
        }


    fun downloadBeritaAcara(token: String, id: Int) = flow<Resource<ResponseBody>> {
        emit(Resource.Loading())

        try {
            val result = dosenService.dsnBeritaAcara(token, id)

            if (result.body() != null && result.isSuccessful) {
                emit(Resource.Success(result.body()!!))
            } else {
                emit(Resource.Error(throwable = Throwable(message = result.message())))
            }

        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))
        }

    }

    fun downloadJurnal(token: String, id: Int) = flow<Resource<ResponseBody>> {
        emit(Resource.Loading())

        try {
            val result = dosenService.dsnDownloadJurnal(token, id)

            if (result.body() != null && result.isSuccessful) {
                emit(Resource.Success(result.body()!!))
            } else {
                emit(Resource.Error(throwable = Throwable(message = result.message())))
            }

        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))
        }

    }

    fun downloadRevisi(token: String, id: Int) = flow<Resource<ResponseBody>> {
        emit(Resource.Loading())

        try {
            val result = dosenService.dsnDownloadRevisi(token, id)

            if (result.body() != null && result.isSuccessful) {
                emit(Resource.Success(result.body()!!))
            } else {
                emit(Resource.Error(throwable = Throwable(message = result.message())))
            }

        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable = throwable))
        }

    }

}
