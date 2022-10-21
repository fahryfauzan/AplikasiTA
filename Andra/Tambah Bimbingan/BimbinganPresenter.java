package org.d3ifcool.finpro.core.presenters;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.models.manager.BimbinganManager;

public class BimbinganPresenter implements BimbinganContract.Presenter {

    public ObservableField<String> tglBimbingan;
    public ObservableField<String> reviewBimbingan;

    private BimbinganManager bimbinganManager;
    private BimbinganContract.ViewModel viewModel;

    public BimbinganPresenter(BimbinganContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        this.bimbinganManager = new BimbinganManager(viewModel);
        this.bimbinganManager.initContext(App.self());
        initFields();
    }

    private void initFields(){
        this.tglBimbingan = new ObservableField<>();
        this.reviewBimbingan = new ObservableField<>();
    }

    private boolean initValidate(){
        if (TextUtils.isEmpty(tglBimbingan.get())){
            viewModel.onMessage("Tanggal Bimbingan Tidak Boleh Kosong");
            return false;
        }
        if (TextUtils.isEmpty(reviewBimbingan.get())){
            viewModel.onMessage("Tanggal Bimbingan Tidak Boleh Kosong");
            return false;
        }
        return true;
    }

    @Override
    public void onCreate() {
        viewModel.onMessage("onCreate");
    }

    @Override
    public void onAccept() {
        viewModel.onMessage("onAccept");
    }

    @Override
    public void onReject() {
        viewModel.onMessage("onReject");
    }

    @Override
    public void createButton() {
        if (initValidate()){
            viewModel.onMessage("onCreate");
        }
    }

    @Override
    public void getAllBimbingan(String token) {
        bimbinganManager.getAllBimbingan(token);
    }

    @Override
    public void getBimbinganByParameter(String token, String username) {
        bimbinganManager.getBimbinganByParameter(token,username);
    }

    @Override
    public void createBimbingan(String token, String username) {
        bimbinganManager.createBimbingan(token, reviewBimbingan.get(), tglBimbingan.get(), username);
    }

    @Override
    public void deleteBimbingan(String token, int id) {
        bimbinganManager.deleteBimbingan(token, id);
    }

    @Override
    public void updateBimbingan(String token, int id) {
        bimbinganManager.updateBimbingan(token,id,reviewBimbingan.get(),tglBimbingan.get());
    }

    @Override
    public void ubahStatus(String token, int id, String status) {
        bimbinganManager.updateBimbinganStatus(token, id, status);
    }
}
