package com.lintangprayogo.aplikasisidang.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lintangprayogo.aplikasisidang.core.models.NilaiSidang
import kotlinx.coroutines.flow.Flow

@Dao
interface NilaiSidangDao {
    @Query("SELECT * FROM NILAI_SIDANG ")
    fun getNilaiSidang(): Flow<List<NilaiSidang>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserNilaiSidang(data: List<NilaiSidang>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNilaiSidang(data: NilaiSidang)

    @Query("DELETE FROM NILAI_SIDANG")
    suspend fun deleteAllNilaiSidang()
}
