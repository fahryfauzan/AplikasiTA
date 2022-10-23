package org.d3ifcool.finpro.mahasiswa.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.databinding.FragmentMahasiswaTugasAkhirBinding;

import java.util.List;

import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class MahasiswaTugasAkhirFragment extends Fragment implements MahasiswaContract.ViewModel {

    private Message message = new Message();
    private FragmentMahasiswaTugasAkhirBinding mBinding;
    private Mediator mediator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_mahasiswa_tugas_akhir, container, false);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator((AppCompatActivity) getActivity());
        mediator.setMahasiswaPresenter(this);
        mBinding.setPresenter(mediator.getMahasiswaPresenter());

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("FileHelper").setEvent("set"));
        mediator.setRefreshLayout(mBinding.swipeRefresh);
        mBinding.setToken(mediator.getSessionToken());
        mBinding.setMhsNim(mediator.getSessionUsername());

        mBinding.layoutTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("MahasiswaPresenter").setEvent("konfirmasiSidang").setText("ditolak"));
            }
        });
        mBinding.layoutAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("MahasiswaPresenter").setEvent("konfirmasiSidang").setText("diterima"));
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getMahasiswaBySession"));
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        message.setMahasiswa(mahasiswa);
        mBinding.setMahasiswa(mahasiswa);
        mediator.message(message.setComponent("RefreshLayout").setEvent("setRefreshing").setEnabled(false));
        mediator.message(message.setComponent("MahasiswaPresenter").setEvent("getPembimbing"));
    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    private void intentPickFile(){
        if (mediator.getPermissionFile()){;
            startActivityForResult(Intent.createChooser(mediator.findFileIntent("PDF"), "Pilih file"), PICK_PDF_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            mediator.message(message.setComponent("MahasiswaPresenter").setEvent("uploadFormPengajuanPerpanjangSK").setUri(data.getData()));
        }
    }

    @Override
    public void onSuccessGetPlotting(Plotting plotting) {
        message.setPlotting(plotting);
        mBinding.setPlot(plotting);
        mediator.setTextView(mBinding.actKoorProfilStatusSk);
        mediator.setButton(mBinding.btnDownloadStatusSk);
        if (message.getMahasiswa().getSk_status() == 1 || message.getMahasiswa().getSk_status() == 3){
            mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.VISIBLE));
            mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF0000"));
            mediator.message(message.setComponent("Button").setEvent("setText").setText("Perpanjang SK"));
            mediator.message(message.setComponent("Button").setEvent("setOnClick").setText("perpanjangSK"));
        }else{
            if(message.getMahasiswa().getSk_status() == 2){
                mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.VISIBLE));
                mediator.message(message.setComponent("Button").setEvent("setText").setText("Download SK"));
                mediator.message(message.setComponent("Button").setEvent("setOnClick").setText("downloadSK"));
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ff4CAF50"));
            }else{
                mediator.message(message.setComponent("Button").setEvent("setVisibility").setVisibility(View.GONE));
                mediator.message(message.setComponent("TextView").setEvent("setTextColor").setColor("#ffFF9800"));
            }
        }
    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {
        mediator.message(message.setComponent("FileHelper").setEvent("openFilePDF").setResponseBody(body).setText(filename));
    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "onSuccessConfirm":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil konfirmasi kehadiran!"));
                onResume();
                break;
            case "onSuccess":
                onResume();
                break;
            case "perpanjangSK":
                intentPickFile();
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
