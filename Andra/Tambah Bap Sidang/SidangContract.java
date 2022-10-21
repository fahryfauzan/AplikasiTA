package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public interface SidangContract {

    interface ViewModel{
        void onGetObjectSidang(Sidang sidang);
        void onGetObjectMahasiswa(Mahasiswa mahasiswa);
        void onGetBody(ResponseBody body, String filename);
        void onMessage(String message);
    }

    interface Presenter{
        void onSave();
        void onSaveReview();
        void onUploadRevisi();
        void checkFormRevisi(String token, String mhs_nim);
        void uploadRevisi(String token, MultipartBody.Part part);
        void uploadDraftJurnal(String token, String mhs_nim, MultipartBody.Part part);
        void downloadRevisi(String token,String mhs_nim);
        void saveNilai(String token, String mhs_nim);
        void saveReview(String token, String mhs_nim, String status);
        void getSidangByNIM(String token, String mhs_nim);
        void getMahasiswaSidangByUsername(String token, String mhs_nim);
        void updateStatusSidang(String token, String mhs_nim, String status);
    }

}
