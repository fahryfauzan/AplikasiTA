package com.lintangprayogo.aplikasisidang.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lintangprayogo.aplikasisidang.core.models.TugasAkhir
import kotlinx.coroutines.flow.Flow

@Dao
interface TugasAkhirDao {
    @Query("SELECT * FROM tugas_akhir ORDER BY id DESC ")
    fun getTugasAkhir(): Flow<List<TugasAkhir>>

    @Query("SELECT * FROM  tugas_akhir WHERE id=:skId")
    fun getById(skId: Int): Flow<TugasAkhir?>

    @Query("SELECT * FROM tugas_akhir ORDER BY id DESC limit 1")
    fun getSingle(): Flow<TugasAkhir?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTugasAkhir(data: List<TugasAkhir>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTugasAkhir(data: TugasAkhir)

    @Query("DELETE FROM tugas_akhir")
    suspend fun deleteAllSK()

    @Query("DELETE FROM tugas_akhir WHERE id=:id")
    suspend fun deleteSK(id: Int): Int
}
