package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.manager.DosenManager;
import org.d3ifcool.finpro.core.models.manager.SidangManager;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiDosenEditorActivity;

import okhttp3.MultipartBody;

public class SidangPresenter implements SidangContract.Presenter {

    public ObservableField<String> clo1;
    public ObservableField<String> clo2;
    public ObservableField<String> clo3;
    public ObservableField<String> review;

    private SidangContract.ViewModel viewModel;
    private SidangManager sidangManager;

    public SidangPresenter(SidangContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        sidangManager = new SidangManager(viewModel);
        sidangManager.initContext(App.self());
        initFields();
    }

    private void initFields(){
        clo1 = new ObservableField<>();
        clo2 = new ObservableField<>();
        clo3 = new ObservableField<>();
        review = new ObservableField<>();
    }

    private boolean isNilaiValid(){
        if (TextUtils.isEmpty(clo1.get())){
            viewModel.onMessage("Nilai CLO 1 tidak boleh kosong");
            return false;
        }else{
            if (!clo1.get().matches("(A|AB|B|BC|C|D|E)")){
                viewModel.onMessage("Nilai CLO 1 tidak valid");
                return false;
            }
        }
        if (TextUtils.isEmpty(clo2.get())){
            viewModel.onMessage("Nilai CLO 2 tidak boleh kosong");
            return false;
        }else{
            if (!clo2.get().matches("(A|AB|B|BC|C|D|E)")){
                viewModel.onMessage("Nilai CLO 2 tidak valid");
                return false;
            }
        }
        if (TextUtils.isEmpty(clo3.get())){
            viewModel.onMessage("Nilai CLO 3 tidak boleh kosong");
            return false;
        }else{
            if (!clo3.get().matches("(A|AB|B|BC|C|D|E)")){
                viewModel.onMessage("Nilai CLO 3 tidak valid");
                return false;
            }
        }
        return true;
    }

    private boolean isReviewValid(){
        if (TextUtils.isEmpty(review.get())){
            viewModel.onMessage("Review sidang tidak boleh kosong");
            return false;
        }else{
            if (review.get().length() < 30){
                viewModel.onMessage("Review sidang terlalu pendek");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onSave() {
        if (isNilaiValid()){
            viewModel.onMessage("saveNilai");
        }
    }

    @Override
    public void onSaveReview() {
        if (isReviewValid()){
            viewModel.onMessage("saveReview");
        }
    }

    @Override
    public void onUploadRevisi() {
        viewModel.onMessage("onUploadRevisi");
    }

    @Override
    public void checkFormRevisi(String token, String mhs_nim) {
        sidangManager.checkFormRevisi(token,mhs_nim);
    }

    @Override
    public void uploadRevisi(String token, MultipartBody.Part part) {
        sidangManager.uploadFormRevisi(token,part);
    }

    @Override
    public void uploadDraftJurnal(String token, String mhs_nim, MultipartBody.Part part) {
        sidangManager.uploadDraftJurnal(token, mhs_nim, part);
    }

    @Override
    public void downloadRevisi(String token, String mhs_nim) {
        sidangManager.downloadFormRevisi(token, mhs_nim);
    }

    @Override
    public void saveNilai(String token, String mhs_nim) {
        sidangManager.saveNilaiSidang(token,mhs_nim,clo1.get(),clo2.get(),clo3.get());
    }

    @Override
    public void saveReview(String token, String mhs_nim, String status) {
        sidangManager.saveReviewSidang(token,mhs_nim,review.get(), status);
    }

    @Override
    public void getSidangByNIM(String token, String mhs_nim) {
        sidangManager.getSidangByNIM(token,mhs_nim);
    }

    @Override
    public void getMahasiswaSidangByUsername(String token, String username) {
        sidangManager.getMahasiswaSidangByUsername(token, username);
    }

    @Override
    public void updateStatusSidang(String token, String mhs_nim, String status) {
        sidangManager.updateStatusSidang(token, mhs_nim, status);
    }

    public void floatButton(){
        viewModel.onMessage("FloatButton");
    }
}
