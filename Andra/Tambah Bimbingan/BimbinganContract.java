package org.d3ifcool.finpro.core.interfaces;

import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.models.Mahasiswa;

import java.util.List;

public interface BimbinganContract {

    interface ViewModel{
        void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa);
        void onGetListMahasiswa(List<Mahasiswa> mahasiswaList);
        void onMessage(String message);
    }

    interface Presenter{
        void onCreate();
        void onAccept();
        void onReject();
        void createButton();
        void getAllBimbingan(String token);
        void getBimbinganByParameter(String token, String username);
        void createBimbingan(String token, String username);
        void deleteBimbingan(String token, int id);
        void updateBimbingan(String token, int id);
        void ubahStatus(String token, int id, String status);
    }

}
