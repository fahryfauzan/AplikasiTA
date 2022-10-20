package org.d3ifcool.finpro.dosen.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.models.TugasAkhir;
import org.d3ifcool.finpro.databinding.ActivityDosenSidangTambahBinding;
import org.d3ifcool.finpro.viewModel.NilaiSidangViewModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class DosenSidangTambahActivity extends AppCompatActivity {

    public static final String EXTRA_TUGAS_AKHIR = "extra_tugas_akhir";

    private ProgressDialog progressDialog;
    private NilaiSidangViewModel nilaiSidangViewModel;

    private String tanggal, pukul, revisi, ruang, string_pemb1_clo1, string_pemb1_clo2, string_pemb1_clo3, string_pemb2_clo1, string_pemb2_clo2, string_pemb2_clo3, string_peng1_clo1, string_peng1_clo2, string_peng1_clo3, string_peng2_clo1, string_peng2_clo2, string_peng2_clo3, indeks, status_sidang, catatan_revisi;
    private double int_pemb1_clo1 = 0.0, int_pemb1_clo2 = 0.0, int_pemb1_clo3 = 0.0, int_pemb2_clo1 = 0.0, int_pemb2_clo2 = 0.0, int_pemb2_clo3 = 0.0, int_peng1_clo1 = 0.0, int_peng1_clo2 = 0.0, int_peng1_clo3 = 0.0, int_peng2_clo1 = 0.0, int_peng2_clo2 = 0.0, int_peng2_clo3 = 0.0;
    private double doubleNilaiTotal;
    private int extraTugasAkhirId;

    ActivityDosenSidangTambahBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        setProgressVisibility();
        setMessageSuccess();
        setMessageFailed();


        setTitle(getString(R.string.title_sidang_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        MethodHelper helper = new MethodHelper();
        SpinnerHelper helper1 = new SpinnerHelper(this);

        final TugasAkhir tugasAkhir = getIntent().getParcelableExtra(EXTRA_TUGAS_AKHIR);
        extraTugasAkhirId = tugasAkhir.getId_tugas_akhir();

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        helper.setDatePickerNoMax(this, binding.actDsnMhsSidangTextviewTanggal);
        helper.setDatePickerNoMax(this, binding.actDsnMhsSidangTextviewBatasRevisi);
        binding.actDsnMhsSidangTextviewPukul.setText(currentTime);
        helper.setTimeDialog(this, binding.actDsnMhsSidangTextviewPukul);
        helper1.initSpinnerStatus(binding.actDsnSidangSpinner);


        binding.actDsnSidangNilaiPemb1Clo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPemb1Clo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPemb1Clo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPemb2Clo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPemb2Clo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPemb2Clo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng1Clo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng1Clo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng1Clo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng2Clo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng2Clo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnSidangNilaiPeng2Clo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setNilaiTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.actDsnInfoButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal = binding.actDsnMhsSidangTextviewTanggal.getText().toString();
                revisi = binding.actDsnMhsSidangTextviewBatasRevisi.getText().toString();
                pukul = binding.actDsnMhsSidangTextviewPukul.getText().toString();
                ruang = binding.actDsnSidangRuangSidang.getText().toString();
                indeks = binding.dsnSidangIndeks.getText().toString();
                status_sidang = binding.actDsnSidangSpinner.getSelectedItem().toString();
                catatan_revisi = binding.actDsnSidangCatatanRevisi.getText().toString();

                if (!string_pemb1_clo1.isEmpty() && !string_pemb1_clo2.isEmpty() && !string_pemb1_clo3.isEmpty() && !string_pemb2_clo1.isEmpty() && !string_pemb2_clo2.isEmpty() && !string_pemb2_clo3.isEmpty() && !string_peng1_clo1.isEmpty() && !string_peng1_clo2.isEmpty() && !string_peng1_clo3.isEmpty() && !string_peng2_clo1.isEmpty() && !string_peng2_clo2.isEmpty() && !string_peng2_clo3.isEmpty()) {
                    setNilaiTotal();
                }
                if (!string_pemb1_clo1.equals("A") && !string_pemb1_clo1.equals("AB") && !string_pemb1_clo1.equals("B") && !string_pemb1_clo1.equals("BC") && !string_pemb1_clo1.equals("C") && !string_pemb1_clo1.equals("D") && !string_pemb1_clo1.equals("E") && string_pemb1_clo1.isEmpty()){
                    binding.actDsnSidangNilaiPemb1Clo1.setError(getString(R.string.validate_indeks));
                }else if (!string_pemb1_clo2.equals("A") && !string_pemb1_clo2.equals("AB") && !string_pemb1_clo2.equals("B") && !string_pemb1_clo2.equals("BC") && !string_pemb1_clo2.equals("C") && !string_pemb1_clo2.equals("D") && !string_pemb1_clo2.equals("E") && string_pemb1_clo2.isEmpty()){
                    binding.actDsnSidangNilaiPemb1Clo2.setError(getString(R.string.validate_indeks));
                }else if (!string_pemb1_clo3.equals("A") && !string_pemb1_clo3.equals("AB") && !string_pemb1_clo3.equals("B") && !string_pemb1_clo3.equals("BC") && !string_pemb1_clo3.equals("C") && !string_pemb1_clo3.equals("D") && !string_pemb1_clo3.equals("E") && string_pemb1_clo3.isEmpty()){
                    binding.actDsnSidangNilaiPemb1Clo3.setError(getString(R.string.validate_indeks));
                }else if (!string_pemb2_clo1.equals("A") && !string_pemb2_clo1.equals("AB") && !string_pemb2_clo1.equals("B") && !string_pemb2_clo1.equals("BC") && !string_pemb2_clo1.equals("C") && !string_pemb2_clo1.equals("D") && !string_pemb2_clo1.equals("E") && string_pemb2_clo1.isEmpty()){
                    binding.actDsnSidangNilaiPemb2Clo1.setError(getString(R.string.validate_indeks));
                }else if (!string_pemb2_clo2.equals("A") && !string_pemb2_clo2.equals("AB") && !string_pemb2_clo2.equals("B") && !string_pemb2_clo2.equals("BC") && !string_pemb2_clo2.equals("C") && !string_pemb2_clo2.equals("D") && !string_pemb2_clo2.equals("E") && string_pemb2_clo2.isEmpty()){
                    binding.actDsnSidangNilaiPemb2Clo2.setError(getString(R.string.validate_indeks));
                }else if (!string_pemb2_clo3.equals("A") && !string_pemb2_clo3.equals("AB") && !string_pemb2_clo3.equals("B") && !string_pemb2_clo3.equals("BC") && !string_pemb2_clo3.equals("C") && !string_pemb2_clo3.equals("D") && !string_pemb2_clo3.equals("E") && string_pemb2_clo3.isEmpty()){
                    binding.actDsnSidangNilaiPemb2Clo3.setError(getString(R.string.validate_indeks));
                }else if (!string_peng1_clo1.equals("A") && !string_peng1_clo1.equals("AB") && !string_peng1_clo1.equals("B") && !string_peng1_clo1.equals("BC") && !string_peng1_clo1.equals("C") && !string_peng1_clo1.equals("D") && !string_peng1_clo1.equals("E") && string_peng1_clo1.isEmpty()){
                    binding.actDsnSidangNilaiPeng1Clo1.setError(getString(R.string.validate_indeks));
                }else if (!string_peng1_clo2.equals("A") && !string_peng1_clo2.equals("AB") && !string_peng1_clo2.equals("B") && !string_peng1_clo2.equals("BC") && !string_peng1_clo2.equals("C") && !string_peng1_clo2.equals("D") && !string_peng1_clo2.equals("E") && string_peng1_clo2.isEmpty()){
                    binding.actDsnSidangNilaiPeng1Clo2.setError(getString(R.string.validate_indeks));
                }else if (!string_peng1_clo3.equals("A") && !string_peng1_clo3.equals("AB") && !string_peng1_clo3.equals("B") && !string_peng1_clo3.equals("BC") && !string_peng1_clo3.equals("C") && !string_peng1_clo3.equals("D") && !string_peng1_clo3.equals("E") && string_peng1_clo3.isEmpty()){
                    binding.actDsnSidangNilaiPeng1Clo3.setError(getString(R.string.validate_indeks));
                }else if (!string_peng2_clo1.equals("A") && !string_peng2_clo1.equals("AB") && !string_peng2_clo1.equals("B") && !string_peng2_clo1.equals("BC") && !string_peng2_clo1.equals("C") && !string_peng2_clo1.equals("D") && !string_peng2_clo1.equals("E") && string_peng2_clo1.isEmpty()){
                    binding.actDsnSidangNilaiPeng2Clo1.setError(getString(R.string.validate_indeks));
                }else if (!string_peng2_clo2.equals("A") && !string_peng2_clo2.equals("AB") && !string_peng2_clo2.equals("B") && !string_peng2_clo2.equals("BC") && !string_peng2_clo2.equals("C") && !string_peng2_clo2.equals("D") && !string_peng2_clo2.equals("E") && string_peng2_clo2.isEmpty()){
                    binding.actDsnSidangNilaiPeng2Clo2.setError(getString(R.string.validate_indeks));
                }else if (!string_peng2_clo3.equals("A") && !string_peng2_clo3.equals("AB") && !string_peng2_clo3.equals("B") && !string_peng2_clo3.equals("BC") && !string_peng2_clo3.equals("C") && !string_peng2_clo3.equals("D") && !string_peng2_clo3.equals("E") && string_peng2_clo3.isEmpty()){
                    binding.actDsnSidangNilaiPeng2Clo3.setError(getString(R.string.validate_indeks));
                }else {
                    nilaiSidangViewModel.createNilaiSidang(tanggal,pukul,ruang, string_pemb1_clo1, string_pemb1_clo2, string_pemb1_clo3, string_pemb2_clo1, string_pemb2_clo2, string_pemb2_clo3, string_peng1_clo1, string_peng1_clo2, string_peng1_clo3, string_peng2_clo1, string_peng2_clo2, string_peng2_clo3, doubleNilaiTotal, indeks, revisi, status_sidang, catatan_revisi,extraTugasAkhirId, DosenSidangTambahActivity.this);
                    //Toast.makeText(DosenSidangTambahActivity.this, tanggal + pukul+ ruang + string_pemb1_clo1 + string_pemb1_clo2 + string_pemb1_clo3+ string_pemb2_clo1 + string_pemb2_clo2 + string_pemb2_clo3 + string_peng1_clo1 + string_peng1_clo2 + string_peng1_clo3 + string_peng2_clo1 + string_peng2_clo2 + string_peng2_clo3 + doubleNilaiTotal + indeks + revisi + status_sidang + extraTugasAkhirId, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setNilaiTotal(){

        string_pemb1_clo1 = binding.actDsnSidangNilaiPemb1Clo1.getText().toString();
        string_pemb1_clo2 = binding.actDsnSidangNilaiPemb1Clo2.getText().toString();
        string_pemb1_clo3 = binding.actDsnSidangNilaiPemb1Clo3.getText().toString();
        string_pemb2_clo1 = binding.actDsnSidangNilaiPemb2Clo1.getText().toString();
        string_pemb2_clo2 = binding.actDsnSidangNilaiPemb2Clo2.getText().toString();
        string_pemb2_clo3 = binding.actDsnSidangNilaiPemb2Clo3.getText().toString();
        string_peng1_clo1 = binding.actDsnSidangNilaiPeng1Clo1.getText().toString();
        string_peng1_clo2 = binding.actDsnSidangNilaiPeng1Clo2.getText().toString();
        string_peng1_clo3 = binding.actDsnSidangNilaiPeng1Clo3.getText().toString();
        string_peng2_clo1 = binding.actDsnSidangNilaiPeng2Clo1.getText().toString();
        string_peng2_clo2 = binding.actDsnSidangNilaiPeng2Clo2.getText().toString();
        string_peng2_clo3 = binding.actDsnSidangNilaiPeng2Clo3.getText().toString();

        if (string_pemb1_clo1.equals("A")) {
            int_pemb1_clo1 = 4.0;
        } else if (string_pemb1_clo1.equals("AB")) {
            int_pemb1_clo1 = 3.5;
        } else if (string_pemb1_clo1.equals("B")) {
            int_pemb1_clo1 = 3;
        } else if (string_pemb1_clo1.equals("BC")) {
            int_pemb1_clo1 = 2.5;
        } else if (string_pemb1_clo1.equals("C")) {
            int_pemb1_clo1 = 2;
        } else if (string_pemb1_clo1.equals("D")) {
            int_pemb1_clo1 = 1;
        } else if (string_pemb1_clo1.equals("E")) {
            int_pemb1_clo1 = 0;
        }

        if (string_pemb1_clo2.equals("A")) {
            int_pemb1_clo2 = 4.0;
        } else if (string_pemb1_clo2.equals("AB")) {
            int_pemb1_clo2 = 3.5;
        } else if (string_pemb1_clo2.equals("B")) {
            int_pemb1_clo2 = 3;
        } else if (string_pemb1_clo2.equals("BC")) {
            int_pemb1_clo2 = 2.5;
        } else if (string_pemb1_clo2.equals("C")) {
            int_pemb1_clo2 = 2;
        } else if (string_pemb1_clo2.equals("D")) {
            int_pemb1_clo2 = 1;
        } else if (string_pemb1_clo2.equals("E")) {
            int_pemb1_clo2 = 0;
        }

        if (string_pemb1_clo3.equals("A")) {
            int_pemb1_clo3 = 4.0;
        } else if (string_pemb1_clo3.equals("AB")) {
            int_pemb1_clo3 = 3.5;
        } else if (string_pemb1_clo3.equals("B")) {
            int_pemb1_clo3 = 3;
        } else if (string_pemb1_clo3.equals("BC")) {
            int_pemb1_clo3 = 2.5;
        } else if (string_pemb1_clo3.equals("C")) {
            int_pemb1_clo3 = 2;
        } else if (string_pemb1_clo3.equals("D")) {
            int_pemb1_clo3 = 1;
        } else if (string_pemb1_clo3.equals("E")) {
            int_pemb1_clo3 = 0;
        }

        if (string_pemb2_clo1.equals("A")) {
            int_pemb2_clo1 = 4.0;
        } else if (string_pemb2_clo1.equals("AB")) {
            int_pemb2_clo1 = 3.5;
        } else if (string_pemb2_clo1.equals("B")) {
            int_pemb2_clo1 = 3;
        } else if (string_pemb2_clo1.equals("BC")) {
            int_pemb2_clo1 = 2.5;
        } else if (string_pemb2_clo1.equals("C")) {
            int_pemb2_clo1 = 2;
        } else if (string_pemb2_clo1.equals("D")) {
            int_pemb2_clo1 = 1;
        } else if (string_pemb2_clo1.equals("E")) {
            int_pemb2_clo1 = 0;
        }

        if (string_pemb2_clo2.equals("A")) {
            int_pemb2_clo2 = 4.0;
        } else if (string_pemb2_clo2.equals("AB")) {
            int_pemb2_clo2 = 3.5;
        } else if (string_pemb2_clo2.equals("B")) {
            int_pemb2_clo2 = 3;
        } else if (string_pemb2_clo2.equals("BC")) {
            int_pemb2_clo2 = 2.5;
        } else if (string_pemb2_clo2.equals("C")) {
            int_pemb2_clo2 = 2;
        } else if (string_pemb2_clo2.equals("D")) {
            int_pemb2_clo2 = 1;
        } else if (string_pemb2_clo2.equals("E")) {
            int_pemb2_clo2 = 0;
        }

        if (string_pemb2_clo3.equals("A")) {
            int_pemb2_clo3 = 4.0;
        } else if (string_pemb2_clo3.equals("AB")) {
            int_pemb2_clo3 = 3.5;
        } else if (string_pemb2_clo3.equals("B")) {
            int_pemb2_clo3 = 3;
        } else if (string_pemb2_clo3.equals("BC")) {
            int_pemb2_clo3 = 2.5;
        } else if (string_pemb2_clo3.equals("C")) {
            int_pemb2_clo3 = 2;
        } else if (string_pemb2_clo3.equals("D")) {
            int_pemb2_clo3 = 1;
        } else if (string_pemb2_clo3.equals("E")) {
            int_pemb2_clo3 = 0;
        }

        if (string_peng1_clo1.equals("A")) {
            int_peng1_clo1 = 4.0;
        } else if (string_peng1_clo1.equals("AB")) {
            int_peng1_clo1 = 3.5;
        } else if (string_peng1_clo1.equals("B")) {
            int_peng1_clo1 = 3;
        } else if (string_peng1_clo1.equals("BC")) {
            int_peng1_clo1 = 2.5;
        } else if (string_peng1_clo1.equals("C")) {
            int_peng1_clo1 = 2;
        } else if (string_peng1_clo1.equals("D")) {
            int_peng1_clo1 = 1;
        } else if (string_peng1_clo1.equals("E")) {
            int_peng1_clo1 = 0;
        }

        if (string_peng1_clo2.equals("A")) {
            int_peng1_clo2 = 4.0;
        } else if (string_peng1_clo2.equals("AB")) {
            int_peng1_clo2 = 3.5;
        } else if (string_peng1_clo2.equals("B")) {
            int_peng1_clo2 = 3;
        } else if (string_peng1_clo2.equals("BC")) {
            int_peng1_clo2 = 2.5;
        } else if (string_peng1_clo2.equals("C")) {
            int_peng1_clo2 = 2;
        } else if (string_peng1_clo2.equals("D")) {
            int_peng1_clo2 = 1;
        } else if (string_peng1_clo2.equals("E")) {
            int_peng1_clo2 = 0;
        }

        if (string_peng1_clo3.equals("A")) {
            int_peng1_clo3 = 4.0;
        } else if (string_peng1_clo3.equals("AB")) {
            int_peng1_clo3 = 3.5;
        } else if (string_peng1_clo3.equals("B")) {
            int_peng1_clo3 = 3;
        } else if (string_peng1_clo3.equals("BC")) {
            int_peng1_clo3 = 2.5;
        } else if (string_peng1_clo3.equals("C")) {
            int_peng1_clo3 = 2;
        } else if (string_peng1_clo3.equals("D")) {
            int_peng1_clo3 = 1;
        } else if (string_peng1_clo3.equals("E")) {
            int_peng1_clo3 = 0;
        }

        if (string_peng2_clo1.equals("A")) {
            int_peng2_clo1 = 4.0;
        } else if (string_peng2_clo1.equals("AB")) {
            int_peng2_clo1 = 3.5;
        } else if (string_peng2_clo1.equals("B")) {
            int_peng2_clo1 = 3;
        } else if (string_peng2_clo1.equals("BC")) {
            int_peng2_clo1 = 2.5;
        } else if (string_peng2_clo1.equals("C")) {
            int_peng2_clo1 = 2;
        } else if (string_peng2_clo1.equals("D")) {
            int_peng2_clo1 = 1;
        } else if (string_peng2_clo1.equals("E")) {
            int_peng2_clo1 = 0;
        }

        if (string_peng2_clo2.equals("A")) {
            int_peng2_clo2 = 4.0;
        } else if (string_peng2_clo2.equals("AB")) {
            int_peng2_clo2 = 3.5;
        } else if (string_peng2_clo2.equals("B")) {
            int_peng2_clo2 = 3;
        } else if (string_peng2_clo2.equals("BC")) {
            int_peng2_clo2 = 2.5;
        } else if (string_peng2_clo2.equals("C")) {
            int_peng2_clo2 = 2;
        } else if (string_peng2_clo2.equals("D")) {
            int_peng2_clo2 = 1;
        } else if (string_peng2_clo2.equals("E")) {
            int_peng2_clo2 = 0;
        }

        if (string_peng2_clo3.equals("A")) {
            int_peng2_clo3 = 4.0;
        } else if (string_peng2_clo3.equals("AB")) {
            int_peng2_clo3 = 3.5;
        } else if (string_peng2_clo3.equals("B")) {
            int_peng2_clo3 = 3;
        } else if (string_peng2_clo3.equals("BC")) {
            int_peng2_clo3 = 2.5;
        } else if (string_peng2_clo3.equals("C")) {
            int_peng2_clo3 = 2;
        } else if (string_peng2_clo3.equals("D")) {
            int_peng2_clo3 = 1;
        } else if (string_peng2_clo3.equals("E")) {
            int_peng2_clo3 = 0;
        }

//        Nilai Rata2 Pembimbing
        double ra_clo1 = (int_pemb1_clo1 + int_pemb2_clo1) * 0.5;
        double ra_clo2 = (int_pemb1_clo2 + int_pemb2_clo2) * 0.5;
        double ra_clo3 = (int_pemb1_clo3 + int_pemb2_clo3) * 0.5;

//        Nilai Rata2 Pembimbing

        double rb_clo1 = (int_peng1_clo1 + int_peng2_clo1) * 0.5;
        double rb_clo2 = (int_peng1_clo2 + int_peng2_clo2) * 0.5;
        double rb_clo3 = (int_peng1_clo3 + int_peng2_clo3) * 0.5;

//        Nilai Hasil Perhitungan

        double n1 = (0.6 * ra_clo1 + 0.4 * rb_clo1);
        double n2 = (0.6 * ra_clo2 + 0.4 * rb_clo2);
        double n3 = (0.6 * ra_clo3 + 0.4 * rb_clo3);

        doubleNilaiTotal = (n1 * 0.35) + (n2 * 0.3) + (n3 * 0.35);
        if (doubleNilaiTotal >= 1.0) {
            DecimalFormat df = new DecimalFormat("#.00");
            String newTotal = df.format(doubleNilaiTotal);
            binding.dsnSidangNilaiTotal.setText(newTotal);
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            String newTotal = df.format(doubleNilaiTotal);
            binding.dsnSidangNilaiTotal.setText(newTotal);
        }

        if (doubleNilaiTotal <= 4 && doubleNilaiTotal > 3.5){
            binding.dsnSidangIndeks.setText("A");
        }else if (doubleNilaiTotal <= 3.5 && doubleNilaiTotal > 3.25){
            binding.dsnSidangIndeks.setText(("AB"));
        }else if (doubleNilaiTotal <= 3.25 && doubleNilaiTotal > 2.75){
            binding.dsnSidangIndeks.setText("B");
        }else if (doubleNilaiTotal <= 2.75 && doubleNilaiTotal > 2.25){
            binding.dsnSidangIndeks.setText(("BC"));
        }else if (doubleNilaiTotal <= 2.25 && doubleNilaiTotal > 1.75){
            binding.dsnSidangIndeks.setText("C");
        }else if (doubleNilaiTotal <= 1.75 && doubleNilaiTotal > 1){
            binding.dsnSidangIndeks.setText("D");
        }else {
            binding.dsnSidangIndeks.setText("E");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViewModel(){
        nilaiSidangViewModel = new ViewModelProvider(this).get(NilaiSidangViewModel.class);
    }

    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(DosenSidangTambahActivity.this, R.layout.activity_dosen_sidang_tambah);
        binding.setLifecycleOwner(this);
    }

    private void setProgressVisibility() {
        nilaiSidangViewModel.isProgressVisible().observe(this, isProgressVisible -> {
            if(isProgressVisible){
                progressDialog.show();
            }else{
                progressDialog.dismiss();
            }
        });
    }

    private void setMessageSuccess(){
        nilaiSidangViewModel.isSuccess().observe(this, isMessageSuccess -> {
            if(isMessageSuccess){
                finish();
            }
        });
    }

    private void setMessageFailed(){
        nilaiSidangViewModel.messageFailedEditor().observe(this, messageFailedEditor -> {
            Toast.makeText(this, messageFailedEditor, Toast.LENGTH_SHORT).show();
        });
    }
}
