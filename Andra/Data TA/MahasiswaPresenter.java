package org.d3ifcool.finpro.core.presenters;

import android.content.Intent;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import org.d3ifcool.finpro.App;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Constant;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.manager.MahasiswaManager;
import org.d3ifcool.finpro.dosen.activities.editor.DosenProfilUbahActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaBimbinganActivity;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;

import okhttp3.MultipartBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DOSEN;

public class MahasiswaPresenter implements MahasiswaContract.Presenter {

    public ObservableField<String> nama;
    public ObservableField<String> nim;
    public ObservableField<String> judul;
    public ObservableField<String> judulInggris;
    public ObservableField<String> angkatan;
    public ObservableField<String> telp;
    public ObservableField<String> email;
    private MahasiswaContract.ViewModel viewModel;
    private MahasiswaManager mahasiswaManager;

    public MahasiswaPresenter(MahasiswaContract.ViewModel viewModel) {
        this.viewModel = viewModel;
        mahasiswaManager = new MahasiswaManager(viewModel);
        mahasiswaManager.initContext(App.self());
        intFields();
    }

    private void intFields(){
        nama = new ObservableField<>();
        nim = new ObservableField<>();
        judul = new ObservableField<>();
        judulInggris = new ObservableField<>();
        angkatan = new ObservableField<>();
        telp = new ObservableField<>();
        email = new ObservableField<>();
    }

    private boolean isValidateUpdate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nim.get())){
            viewModel.onMessage("NIM "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(angkatan.get())){
            viewModel.onMessage("Angkatan "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(judul.get())){
            viewModel.onMessage("Judul "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(judulInggris.get())){
            viewModel.onMessage("Judul Inggris "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    private boolean isValidateCreate(){
        if (TextUtils.isEmpty(nama.get())){
            viewModel.onMessage("Nama "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(nim.get())){
            viewModel.onMessage("NIM "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }

        if (TextUtils.isEmpty(angkatan.get())){
            viewModel.onMessage("Angkatan "+App.self().getString(R.string.text_tidak_boleh_kosong));
            return false;
        }
        return true;
    }

    @Override
    public void onCreate() {
        if (isValidateCreate()){
            viewModel.onMessage("AddButton");
        }
    }

    @Override
    public void onUpload() {
        viewModel.onMessage("onUploadForm");
    }

    @Override
    public void onDelete() {
        viewModel.onMessage("onDelete");
    }

    @Override
    public void onPerpanjang() {
        viewModel.onMessage("perpanjangSK");
    }

    @Override
    public void onBimbingan(String dsn_nip) {
        Intent intent = new Intent(App.self(), MahasiswaBimbinganActivity.class);
        intent.putExtra(EXTRA_DOSEN,dsn_nip);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.self().startActivity(intent);
    }

    public void btnSKUpdate(int plot_id) {
        if (plot_id > 0){
            viewModel.onMessage("btnSkUpdate");
        }else{
            viewModel.onMessage("Mahasiswa belum memiliki pembimbing");
        }
    }

    public void btnAddPembimbing(){
        viewModel.onMessage("AddPembimbing");
    }

    public void floatButton(){
        viewModel.onMessage("FloatButton");
    }

    @Override
    public void getAllMahasiswa(String token) {
        mahasiswaManager.getMahasiswa(token);
    }

    @Override
    public void createMahasiswa(String token) {
        if (isValidateCreate()){
            mahasiswaManager.createMahasiswa(token,nim.get(),nama.get());
        }
    }

    @Override
    public void deleteMahasiswa(String token, String nim) {
        mahasiswaManager.deleteMahasiswa(token,nim);
    }

    @Override
    public void updateMahasiswa(String token) {
        if (isValidateUpdate()) {
            mahasiswaManager.updateMahasiswa(token, nim.get(),nama.get(), angkatan.get(), telp.get(), email.get(), judul.get(), judulInggris.get());
        }
    }

    @Override
    public void getMahasiswaByNIM(String token, String mhs_nim) {
        mahasiswaManager.getMahasiswaByParameter(token,mhs_nim);
    }

    @Override
    public void getPembimbing(String token, int plot_id) {
        mahasiswaManager.getPembimbing(token,plot_id);
    }

    @Override
    public void addPembimbing(String token, String mhs_nim, int plot_id) {
        mahasiswaManager.addPembimbing(token, mhs_nim, plot_id);
    }

    @Override
    public void deletePembimbing(String token, String mhs_nim) {
        mahasiswaManager.deletePembimbing(token,mhs_nim);
    }

    @Override
    public void updateSKTA(String token, String mhs_nim) {
        mahasiswaManager.updateSKTA(token, mhs_nim);
    }

    @Override
    public void uploadFormPengajuanPerpanjangSK(String token, String username, MultipartBody.Part part) {
        mahasiswaManager.uploadFormPengajuanPerpanjangSK(token,username,part);
    }

    @Override
    public void uploadFormSidang(String token, MultipartBody.Part part) {
        mahasiswaManager.uploadFormSidang(token,part);
    }

    @Override
    public void downloadSKTA(String token, String mhs_nim) {
        mahasiswaManager.downloadSKTA(token,mhs_nim);
    }

    @Override
    public void askSidang(String token) {
        mahasiswaManager.askSidang(token);
    }

    @Override
    public void konfirmasiSidang(String token, String status) {
        mahasiswaManager.konfirmasi(token,status);
    }

    public Intent toolbarIntent(Message message){
        Intent intent = new Intent(App.self(), message.getaClass());
        intent.putExtra(Constant.ObjectConstanta.EXTRA_MAHASISWA,message.getMahasiswa());
        return intent;
    }
}
