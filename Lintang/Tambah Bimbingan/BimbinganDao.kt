package com.lintangprayogo.aplikasisidang.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lintangprayogo.aplikasisidang.core.models.Bimbingan
import kotlinx.coroutines.flow.Flow

@Dao
interface BimbinganDao {
    @Query("SELECT * FROM bimbingan WHERE bimbingan_dsn_nip=:dsnNip AND bimbingan_mhs_nim =:mhsNim")
    fun getBimbingan(dsnNip: String, mhsNim: String): Flow<List<Bimbingan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBimbingan(data: List<Bimbingan>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBimbingan(data: Bimbingan)

    @Query("DELETE FROM bimbingan")
    suspend fun deleteAllBimbingan()

    @Query("DELETE FROM bimbingan WHERE bimbingan_dsn_nip=:dsnNip AND bimbingan_mhs_nim =:mhsNim")
    suspend fun deleteAllBimbingan(dsnNip: String, mhsNim: String)

    @Query("UPDATE BIMBINGAN SET bimbingan_status='disetujui' WHERE bimbingan_dsn_nip=:dsnNip AND bimbingan_mhs_nim =:mhsNim")
    suspend fun acceptAll(dsnNip: String, mhsNim: String)


    @Query("DELETE FROM bimbingan where bimbingan_id=:id")
    suspend fun deleteBimbinganByID(id: Int)

    @Query("SELECT * FROM bimbingan where bimbingan_id=:id")
    fun getBimbinganByID(id: Int): Flow<Bimbingan>


}
