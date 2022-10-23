package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public interface MahasiswaContract {

    interface ViewModel{
        void onGetObjectMahasiswa(Mahasiswa mahasiswa);
        void onGetListMahasiswa(List<Mahasiswa> mahasiswaList);
        void onSuccessGetPlotting(Plotting plotting);
        void onGetBody(ResponseBody body, String filename);
        void onMessage(String message);
    }

    interface Presenter{
        void onCreate();
        void onUpload();
        void onDelete();
        void onPerpanjang();
        void onBimbingan(String dsn_nip);
        void getAllMahasiswa(String token);
        void createMahasiswa(String token);
        void deleteMahasiswa(String token,String nim);
        void updateMahasiswa(String token);
        void getMahasiswaByNIM(String token,String mhs_nim);
        void getPembimbing(String token,int plot_id);
        void addPembimbing(String token, String mhs_nim, int plot_id);
        void deletePembimbing(String token, String mhs_nim);
        void updateSKTA(String token,String mhs_nim);
        void uploadFormPengajuanPerpanjangSK(String token, String username, MultipartBody.Part part);
        void uploadFormSidang(String token, MultipartBody.Part part);
        void downloadSKTA(String token,String mhs_nim);
        void askSidang(String token);
        void konfirmasiSidang(String token, String status);
    }

}
