package org.d3ifcool.finpro.mahasiswa.fragments.parent;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_DISETUJUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.adapters.DsnPembimbingAdapter;
import org.d3ifcool.finpro.core.adapters.MhsTaViewAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.helpers.ViewAdapterHelper;
import org.d3ifcool.finpro.core.helpers.ViewPembimbingAdapterHelper;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.NilaiSidang;
import org.d3ifcool.finpro.core.models.TugasAkhir;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaTaBimbinganActivity;
import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaPaSidangDetailActivity;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaJadwalSidangActivity;
import org.d3ifcool.finpro.prodi.activities.ProdiJadwalPengujiActivity;
import org.d3ifcool.finpro.viewModel.BimbinganViewModel;
import org.d3ifcool.finpro.viewModel.DosenViewModel;
import org.d3ifcool.finpro.viewModel.NilaiSidangViewModel;
import org.d3ifcool.finpro.viewModel.TugasAkhirViewModel;

import java.util.ArrayList;


public class MahasiswaTugasAkhirFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PARAM_TUGAS_AKHIR = "tugas_akhir.id_tugas_akhir";
    private static final String PARAM_MAHASISWA = "mahasiswa.mhs_nim";
    private static final String PARAM_TUGAS_AKHIR_2 = "mhs_nim";
    private static final String PARAM_BIMBINGAN_ID = "plotting.tugas_akhir_id";
    private static final String PARAM_BIMBINGAN_STATUS = "bimbingan.bimbingan_status";

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    private View disable_view;
    private Dosen parcelDosenPembimbing;

    private int jumlahBimbingan;

    private ViewPembimbingAdapterHelper viewPembimbingAdapterHelper;
    private DsnPembimbingAdapter dsnPembimbingAdapter;

    private TugasAkhir parcelTugasAkhir;
    private ArrayList<TugasAkhir> arrayListTugasAkhir = new ArrayList<>();
    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<NilaiSidang> arrayListNilaiSidang = new ArrayList<>();

    private TextView tv_judul_ta_indo, tv_judul_ta_inggris, tv_mhs_nim, tv_mhs_nama, tv_jumlah_bimbingan, tv_nomor_sk, tv_tanggal_terbit, tv_tanggal_berakhir, tv_status_sk, tv_status_sidang_pa;

    private TugasAkhirViewModel tugasAkhirViewModel;
    private NilaiSidangViewModel nilaiSidangViewModel;
    private BimbinganViewModel bimbinganViewModel;

    public MahasiswaTugasAkhirFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_mahasiswa_tugas_akhir, container, false);
        initViewModel();
        setProgressVisibility();
        setListBimbingan();
        setListTugasAkhir();
        setMessageFailed();
        setStatusSidang();

        sessionManager = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());

        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        disable_view = rootView.findViewById(R.id.disable_view);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_judul_ta_indo = rootView.findViewById(R.id.ctn_all_ta_textview_judul_indo);
        tv_judul_ta_inggris = rootView.findViewById(R.id.ctn_all_ta_textview_judul_inggris);
        tv_mhs_nim = rootView.findViewById(R.id.frg_mhs_ta_textview_mhs_nim);
        tv_mhs_nama = rootView.findViewById(R.id.frg_mhs_ta_textview_mhs_nama);
        tv_jumlah_bimbingan = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_bimbingan1);
        tv_status_sk = rootView.findViewById(R.id.frg_mhs_ta_textview_status_sk);
        tv_nomor_sk = rootView.findViewById(R.id.frg_mhs_ta_textview_nomor_sk);
        tv_tanggal_terbit = rootView.findViewById(R.id.frg_mhs_ta_textview_tgl_terbit);
        tv_tanggal_berakhir = rootView.findViewById(R.id.frg_mhs_ta_textview_tgl_berakhir);
        tv_status_sidang_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_sidang);


        CardView cardViewBimbingan1 = rootView.findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewSidang = rootView.findViewById(R.id.frg_mhs_pa_cardview_sidang);
        CardView cardViewJadwal = rootView.findViewById(R.id.frg_mhs_pa_cardview_jadwal_sidang);


        RecyclerView pembRecyclerView = rootView.findViewById(R.id.all_recyclerview_pembimbing);


        dsnPembimbingAdapter = new DsnPembimbingAdapter(getContext());
        viewPembimbingAdapterHelper = new ViewPembimbingAdapterHelper(getContext());
        viewPembimbingAdapterHelper.setRecyclerView(pembRecyclerView);

        tugasAkhirViewModel.getSearchTugasAkhirBy(PARAM_MAHASISWA, sessionManager.getSessionMahasiswaNim(),getActivity());
        nilaiSidangViewModel.searchAllNilaiSidangBy(PARAM_TUGAS_AKHIR_2, sessionManager.getSessionMahasiswaNim(), getActivity());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tugasAkhirViewModel.getSearchTugasAkhirBy(PARAM_MAHASISWA,sessionManager.getSessionMahasiswaNim(),getActivity());
                nilaiSidangViewModel.searchAllNilaiSidangBy(PARAM_TUGAS_AKHIR_2, sessionManager.getSessionMahasiswaNim(), getActivity());

            }
        });

        cardViewBimbingan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaTaBimbinganActivity.class);
                ArrayList<TugasAkhir> extraArrayTugasAkhir = arrayListTugasAkhir;
                i.putExtra(MahasiswaTaBimbinganActivity.EXTRA_DOSEN_PEMBIMBING, parcelDosenPembimbing);
                i.putParcelableArrayListExtra(MahasiswaTaBimbinganActivity.EXTRA_TUGAS_AKHIR, extraArrayTugasAkhir);
                i.putExtra(MahasiswaTaBimbinganActivity.EXTRA_BIMBINGAN_JUMLAH, jumlahBimbingan);
                startActivity(i);
            }
        });

        cardViewJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MahasiswaJadwalSidangActivity.class);
                ArrayList<TugasAkhir> extraArrayTugasAkhir = arrayListTugasAkhir;
                i.putExtra(ProdiJadwalPengujiActivity.EXTRA_TUGAS_AKHIR, parcelTugasAkhir);
                i.putParcelableArrayListExtra(MahasiswaJadwalSidangActivity.EXTRA_TUGAS_AKHIR, extraArrayTugasAkhir);
                startActivity(i);
            }
        });

        cardViewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MahasiswaPaSidangDetailActivity.class);
                i.putExtra(MahasiswaPaSidangDetailActivity.EXTRA_TUGAS_AKHIR, parcelTugasAkhir);
                startActivity(i);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tugasAkhirViewModel.getSearchTugasAkhirBy(PARAM_MAHASISWA,sessionManager.getSessionMahasiswaNim(),getActivity());
    }

    private void initViewModel(){
        tugasAkhirViewModel = new ViewModelProvider(this).get(TugasAkhirViewModel.class);
        bimbinganViewModel = new ViewModelProvider(this).get(BimbinganViewModel.class);
        nilaiSidangViewModel = new ViewModelProvider(this).get(NilaiSidangViewModel.class);

    }

    private void setProgressVisibility() {

        tugasAkhirViewModel.isProgressVisible().observe(getViewLifecycleOwner(), isProgressVisible -> {
            if(isProgressVisible){
                progressDialog.show();
            }else{
                progressDialog.dismiss();
            }
        });

        bimbinganViewModel.isProgressVisible().observe(getViewLifecycleOwner(), isProgressVisible -> {
            if(isProgressVisible){
                progressDialog.show();
            }else{
                progressDialog.dismiss();
            }
        });

        nilaiSidangViewModel.isProgressVisible().observe(getViewLifecycleOwner(), isProgressVisible -> {
            if(isProgressVisible){
                progressDialog.show();
            }else{
                progressDialog.dismiss();
            }
        });
    }

    private void setListTugasAkhir(){
        tugasAkhirViewModel.getListTugasAkhir().observe(getViewLifecycleOwner(), getListTugasAkhir -> {
            arrayListTugasAkhir.clear();
            arrayListTugasAkhir.addAll(getListTugasAkhir);
            swipeRefreshLayout.setRefreshing(false);

            if (arrayListTugasAkhir.size() != 0) {
                disable_view.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);

                parcelTugasAkhir = arrayListTugasAkhir.get(0);
                String stringTugasAkhirId = String.valueOf(arrayListTugasAkhir.get(0).getId_tugas_akhir());

                tv_judul_ta_indo.setText(parcelTugasAkhir.getJudul_ta_indo());
                tv_judul_ta_inggris.setText(parcelTugasAkhir.getJudul_ta_inggris());
                tv_mhs_nim.setText(parcelTugasAkhir.getMhs_nim());
                tv_mhs_nama.setText(parcelTugasAkhir.getMhs_nama());
                tv_nomor_sk.setText(parcelTugasAkhir.getNomor_sk());
                tv_status_sk.setText(parcelTugasAkhir.getStatus_sk());
                tv_tanggal_terbit.setText(parcelTugasAkhir.getTanggal_terbit());
                tv_tanggal_berakhir.setText(parcelTugasAkhir.getTanggal_berakhir());
                bimbinganViewModel.searchBimbinganAllByTwo(PARAM_BIMBINGAN_ID, stringTugasAkhirId, PARAM_BIMBINGAN_STATUS, STATUS_BIMBINGAN_DISETUJUI, getActivity());

                dsnPembimbingAdapter.addItem(arrayListTugasAkhir);
                viewPembimbingAdapterHelper.setAdapterDsn(dsnPembimbingAdapter);


            } else {
                swipeRefreshLayout.setVisibility(View.GONE);
                disable_view.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setListBimbingan(){
        bimbinganViewModel.getListBimbingan().observe(getViewLifecycleOwner(), getListBimbingan -> {
            arrayListBimbingan.clear();
            arrayListBimbingan.addAll(getListBimbingan);

            if (arrayListBimbingan.size() != 0){
                jumlahBimbingan = arrayListBimbingan.size();
                tv_jumlah_bimbingan.setText(String.valueOf(jumlahBimbingan));

            } else {
                jumlahBimbingan = 0;
                tv_jumlah_bimbingan.setText(String.valueOf(jumlahBimbingan));
            }
        });
    }

    private void setStatusSidang(){
        nilaiSidangViewModel.getListNilaiSidang().observe(getViewLifecycleOwner(), getNilaiSidang -> {
            arrayListNilaiSidang.clear();
            arrayListNilaiSidang.addAll(getNilaiSidang);

            if (arrayListNilaiSidang.size() == 0){
                tv_status_sidang_pa.setText(getString(R.string.text_belum_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextRed));
            } else {
                tv_status_sidang_pa.setText(getString(R.string.text_sudah_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextGreen));
            }
        });
    }


    private void setMessageFailed(){
        tugasAkhirViewModel.messageFailedEditor().observe(getViewLifecycleOwner(), messageFailedEditor -> {
            Toast.makeText(getActivity(), messageFailedEditor, Toast.LENGTH_SHORT).show();
        });
        bimbinganViewModel.messageFailedEditor().observe(getViewLifecycleOwner(), messageFailedEditor -> {
            Toast.makeText(getActivity(), messageFailedEditor, Toast.LENGTH_SHORT).show();
        });
        nilaiSidangViewModel.messageFailedEditor().observe(getViewLifecycleOwner(), messageFailedEditor -> {
            Toast.makeText(getActivity(), messageFailedEditor, Toast.LENGTH_SHORT).show();
        });
    }
}
