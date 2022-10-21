@file:Suppress("DEPRECATION")

package com.lintangprayogo.aplikasisidang.dosen.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.lintangprayogo.aplikasisidang.R
import com.lintangprayogo.aplikasisidang.core.base.BaseActivity
import com.lintangprayogo.aplikasisidang.core.models.Dosen
import com.lintangprayogo.aplikasisidang.core.models.Sidang
import com.lintangprayogo.aplikasisidang.core.viewmodels.DosenSidangViewModel
import com.lintangprayogo.aplikasisidang.databinding.ActivityDosenNilaiBinding
import com.lintangprayogo.aplikasisidang.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DosenNilaiActivity : BaseActivity<ActivityDosenNilaiBinding>() {
    private val dosenSidangViewModel: DosenSidangViewModel by viewModels()
    private lateinit var sidang: Sidang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dosen_nilai)
        title = getString(R.string.text_nilai_sidang)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
        sidang = getExtraData("sidang")
        user = getSession(USER_SP)
        binding.isPembimbing1=sidang.pembimbing_nip_1==getSession<Dosen>(DOSEN_SP)?.dsnNip
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.text_progress_dialog))
        binding.submitNilai.setOnClickListener {
            nilaiSidang()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validate(nilaiLaporan: Int?, nilaiPresentasi: Int?, nilaiProduk: Int?): Boolean {
        var isValidate = true
        if (nilaiLaporan != null) {
            if (nilaiLaporan < 0 || nilaiLaporan > 100) {
                binding.nilaiLaporanEt.error = "Nilai Tidak Valid"
                isValidate = false
            }
        } else {
            binding.nilaiLaporanEt.error = "Harap Isi !!!"
            isValidate = false
        }

        if (nilaiPresentasi != null) {
            if (nilaiPresentasi < 0 || nilaiPresentasi > 100) {
                binding.nilaiPresentasiEt.error = "Nilai Tidak Valid"
                isValidate = false
            }
        } else {
            binding.nilaiPresentasiEt.error = "Harap Isi !!!"
            isValidate = false
        }
        if (nilaiProduk != null) {
            if (nilaiProduk < 0 || nilaiProduk > 100) {
                binding.nilaiPresentasiEt.error = "Nilai Tidak Valid"
                isValidate = false
            }
        } else {
            binding.nilaiProdukEt.error = "Harap Isi !!!"
            isValidate = false
        }
        return isValidate
    }


    private fun nilaiSidang() {
        val nilaiLaporan: Int? = binding.nilaiLaporanEt.text.toString().toIntOrNull()
        val nilaiPresentasi: Int? = binding.nilaiPresentasiEt.text.toString().toIntOrNull()
        val nilaiProduk: Int? = binding.nilaiProdukEt.text.toString().toIntOrNull()
        val catatan:String = binding.catatanEt.text.toString()
        if (validate(nilaiLaporan, nilaiPresentasi, nilaiProduk)) {
            postNilaiSidang(nilaiLaporan ?: 0,
                nilaiPresentasi ?: 0,
                nilaiProduk ?: 0,
                catatan)
        }
    }


    private fun postNilaiSidang(nilaiLaporan: Int, nilaiPresentasi: Int, nilaiProduk: Int,catatan:String) {
        dosenSidangViewModel.nilaiSidang(
            user!!.token,
            sidang.id,
            nilaiLaporan,
            nilaiPresentasi,
            nilaiProduk,
            catatan
        ).observe(
            this, {
                when (it) {
                    is Resource.Error -> {
                        progressDialog.dismiss()
                        showToast("Gagal Menilai Sidang !!!")
                    }
                    is Resource.Success -> {
                        progressDialog.dismiss()
                        showToast("Berhasil Minilai  Sidang !!!")
                    }
                    is Resource.Loading -> {
                        progressDialog.show()
                    }
                }
            }
        )
    }


}

