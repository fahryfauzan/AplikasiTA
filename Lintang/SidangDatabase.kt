package com.lintangprayogo.aplikasisidang.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lintangprayogo.aplikasisidang.core.models.*


@Database(
    entities = [NilaiSidang::class, SK::class, TugasAkhir::class,
        Bimbingan::class, PrediksiSidang::class, FormSK::class, PeriodeSidang::class,
        Sidang::class],
    version = 4
)
abstract class SidangDatabase : RoomDatabase() {
    abstract fun nilaiSidangDao(): NilaiSidangDao
    abstract fun skDao(): SKDao
    abstract fun tugasAkhirDao(): TugasAkhirDao
    abstract fun bimbinganDao(): BimbinganDao
    abstract fun prediksiDao(): PrediksiSidangDao
    abstract fun formSKDao(): FormSKDao
    abstract fun periodeSidangDao(): PeriodeSidangDao
    abstract fun sidangDao(): SidangDao

}
