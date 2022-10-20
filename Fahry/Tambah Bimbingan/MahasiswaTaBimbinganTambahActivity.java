package org.d3ifcool.finpro.mahasiswa.activities.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;


import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.core.models.TugasAkhir;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaPaBimbinganTambahBinding;
import org.d3ifcool.finpro.viewModel.BimbinganViewModel;
import org.d3ifcool.finpro.viewModel.PlottingViewModel;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

public class MahasiswaTaBimbinganTambahActivity extends AppCompatActivity {

    public static final String EXTRA_TUGAS_AKHIR = "extra_tugas_akhir";
    private static final String PARAM_TA_ID = "plotting.tugas_akhir_id";

    private ProgressDialog progressDialog;
    private BimbinganViewModel bimbinganViewModel;
    private ActivityMahasiswaPaBimbinganTambahBinding binding;
    private SpinnerHelper spinnerHelper;
    private PlottingViewModel plottingViewModel;
    private ArrayList<Plotting> arrayListPlotting = new ArrayList<>();
    private String getNipDosen;
    private int getPlottingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        setProgressVisibility();
        setListPembimbing();
        setMessageSuccess();
        setMessageFailed();

        setTitle(getString(R.string.title_bimbingan_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        MethodHelper methodHelper = new MethodHelper();
        SessionManager sessionManager = new SessionManager(this);
        spinnerHelper = new SpinnerHelper(this);

        final ArrayList<TugasAkhir> extraArrayTugasAkhir = getIntent().getParcelableArrayListExtra(EXTRA_TUGAS_AKHIR);

        binding.spinnerDsnNama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getNipDosen = arrayListPlotting.get(position).getDsn_nip();
                getPlottingId = arrayListPlotting.get(position).getId_plotting();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i < extraArrayTugasAkhir.size(); i++) {
            if (extraArrayTugasAkhir.get(i).getMhs_nim().equals(sessionManager.getSessionMahasiswaNim())  ) {
                plottingViewModel.searchDosenBimbinganAllBy(PARAM_TA_ID, String.valueOf(extraArrayTugasAkhir.get(i).getId_tugas_akhir()), this);
            }
        }

        methodHelper.setDatePicker(this, binding.actMhsBimbinganTextviewTanggal);

        binding.actMhsInfoButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review = binding.actMhsInfoEdittextKonten.getText().toString();
                String tanggal = binding.actMhsBimbinganTextviewTanggal.getText().toString();
                String nipDosen = getNipDosen;
                int id_plotting = getPlottingId;

                if (review.isEmpty()){
                    binding.actMhsInfoEdittextKonten.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{
                    bimbinganViewModel.createBimbingan(nipDosen, review, tanggal, STATUS_BIMBINGAN_PENDING, id_plotting, MahasiswaTaBimbinganTambahActivity.this);
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViewModel(){
        bimbinganViewModel = new ViewModelProvider(this).get(BimbinganViewModel.class);
        plottingViewModel = new ViewModelProvider(this).get(PlottingViewModel.class);

    }

    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(MahasiswaTaBimbinganTambahActivity.this, R.layout.activity_mahasiswa_pa_bimbingan_tambah);
        binding.setLifecycleOwner(this);
    }

    private void setProgressVisibility() {
        bimbinganViewModel.isProgressVisible().observe(this, isProgressVisible -> {
            if(isProgressVisible){
                progressDialog.show();
            }else{
                progressDialog.dismiss();
            }
        });
    }

    private void setListPembimbing(){
        plottingViewModel.getListPlotting().observe(this, getListPembimbing -> {
            arrayListPlotting.clear();
            arrayListPlotting.addAll(getListPembimbing);
            spinnerHelper.initSpinnerPlotting(arrayListPlotting, binding.spinnerDsnNama);
        });
    }

    private void setMessageSuccess(){
        bimbinganViewModel.isMessageSuccess().observe(this, isMessageSuccess -> {
            if(isMessageSuccess){
                finish();
            }
        });
    }

    private void setMessageFailed(){
        bimbinganViewModel.messageFailedEditor().observe(this, messageFailedEditor -> {
            Toast.makeText(this, messageFailedEditor, Toast.LENGTH_SHORT).show();
        });
    }
}
