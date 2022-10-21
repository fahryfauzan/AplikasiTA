@file:Suppress("DEPRECATION")

package com.lintangprayogo.aplikasisidang.mahasiswa.activities.editor.create


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.lintangprayogo.aplikasisidang.R
import com.lintangprayogo.aplikasisidang.core.base.BaseActivity
import com.lintangprayogo.aplikasisidang.core.viewmodels.MahasiswaBimbinganViewModel
import com.lintangprayogo.aplikasisidang.databinding.ActivityMahasiswaTaBimbinganTambahBinding
import com.lintangprayogo.aplikasisidang.utils.Constant
import com.lintangprayogo.aplikasisidang.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MahasiswaTaBimbinganTambahActivity :
    BaseActivity<ActivityMahasiswaTaBimbinganTambahBinding>() {
    private var dsnNip = ""
    private var mhsNim = ""
    private var tanggalBimbingan: String? = null
    private val bimbinganViewModel: MahasiswaBimbinganViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_mahasiswa_ta_bimbingan_tambah)
        dsnNip = getExtraData("dsn_nip")
        mhsNim = getExtraData("mhs_nim")
        title = getString(R.string.title_bimbingan_tambah)
        user = getSession(USER_SP)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.text_progress_dialog))
        binding.actMhsBimbinganTextviewTanggal.setOnClickListener {
            showDatePicker()
        }
        binding.actMhsInfoButtonSimpan.setOnClickListener {
            postBimbingan()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            { _, y, m, d ->
                binding.actMhsBimbinganTextviewTanggal.text = "$d ${Constant.MONTHS[m]} $y"
                tanggalBimbingan = "$y-$m-$day"
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    @SuppressLint("SetTextI18n")
    private fun postBimbingan() {

        var isValid = true
        val review = binding.bimbinganReviewEt.text.toString()
        if (tanggalBimbingan.isNullOrEmpty()) {
            binding.actMhsBimbinganTextviewTanggal.text = "Harap Isi !!!"
            isValid = false
        }
        if (review.isEmpty()) {
            binding.bimbinganReviewEt.error = "Harap Isi !!!"
            isValid = false
        }

        if (isValid) {
            bimbinganViewModel.postBimbingan(
                user!!.token,
                review,
                tanggalBimbingan!!,
                mhsNim,
                dsnNip
            )
                .observe(this, {
                    when (it) {
                        is Resource.Error -> {
                            progressDialog.dismiss()
                            if (it.throwable != null) {
                                showToast(it.throwable.localizedMessage ?: "unknown")
                            }
                        }
                        is Resource.Success -> {
                            progressDialog.dismiss()
                        }
                        is Resource.Loading -> {
                            progressDialog.show()
                        }
                    }
                })
        }


    }


}
