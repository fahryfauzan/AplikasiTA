@file:Suppress("DEPRECATION")

package com.lintangprayogo.aplikasisidang.mahasiswa.fragments.parents


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lintangprayogo.aplikasisidang.R
import com.lintangprayogo.aplikasisidang.core.base.BaseActivity.Companion.USER_SP
import com.lintangprayogo.aplikasisidang.core.base.BaseFragment
import com.lintangprayogo.aplikasisidang.core.models.TugasAkhir
import com.lintangprayogo.aplikasisidang.core.viewmodels.TugasAkhirViewModel
import com.lintangprayogo.aplikasisidang.databinding.FragmentTugasAkhirBinding
import com.lintangprayogo.aplikasisidang.mahasiswa.activities.MahasiswaBimbinganActivity
import com.lintangprayogo.aplikasisidang.mahasiswa.activities.MahasiswaSKActivity
import com.lintangprayogo.aplikasisidang.mahasiswa.activities.MahasiswaSKFormActivity
import com.lintangprayogo.aplikasisidang.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class TugasAkhirFragment : BaseFragment<FragmentTugasAkhirBinding>() {
    private val tugasAkhirViewModel: TugasAkhirViewModel by viewModels()
    private var tugasAkhir: TugasAkhir? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        user = getSession(USER_SP)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tugas_akhir, container, false
        )
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage(getString(R.string.text_progress_dialog))
        binding.lihatSk.setOnClickListener {
            if (tugasAkhir != null)
                baseStartActivity<MahasiswaSKActivity, TugasAkhir>(
                    "tugas_akhir",
                    tugasAkhir!!
                )
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardDsnOne.setOnClickListener {
            if (tugasAkhir != null) {
                startBimbinganDetailActivity(
                    tugasAkhir!!.namaPembimbingSatu,
                    tugasAkhir!!.nipPembimbingSatu,
                    tugasAkhir!!.mhsNim
                )
            }
        }
        binding.cardDsnTwo.setOnClickListener {
            if (tugasAkhir != null) {
                startBimbinganDetailActivity(
                    tugasAkhir!!.namaPembimbingDua ?: "",
                    tugasAkhir!!.nipPembimbingDua ?: "", tugasAkhir!!.mhsNim
                )
            }
        }
        binding.formSk.setOnClickListener {
            if (tugasAkhir != null)
                baseStartActivity<MahasiswaSKFormActivity, TugasAkhir>("tugas_akhir", tugasAkhir!!)
        }
        binding.refresh.setOnRefreshListener {
            observe()
        }
        observe()
    }

    private fun observe() {
        tugasAkhirViewModel.getTugasAkhir(user!!.token).observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    binding.refresh.isRefreshing = true
                    setUpData(it.data)
                }
                is Resource.Success -> {
                    binding.refresh.isRefreshing = false
                    setUpData(it.data)
                }
                is Resource.Error -> {
                    binding.refresh.isRefreshing = false
                }
            }
        })
    }


    private fun startBimbinganDetailActivity(dsnNama: String, dsnNip: String, mhsNim: String) {
        val intent = Intent(context, MahasiswaBimbinganActivity::class.java)
        intent.putExtra("dsn_name", dsnNama)
        intent.putExtra("dsn_nip", dsnNip)
        intent.putExtra("mhs_nim", mhsNim)
        this.startActivity(intent)
    }

    private fun setUpData(tugasAkhir: TugasAkhir?) {
        if (tugasAkhir != null) {
            binding.viewEmptyview.visibility = View.GONE
            binding.enableView.visibility = View.VISIBLE
            binding.tugasAkhir = tugasAkhir
            this.tugasAkhir = tugasAkhir
        } else {
            binding.viewEmptyview.visibility = View.VISIBLE
            binding.enableView.visibility = View.GONE
        }
    }


}
