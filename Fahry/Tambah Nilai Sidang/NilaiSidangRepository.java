package org.d3ifcool.finpro.repository;

import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.models.NilaiSidang;

import java.util.List;
import retrofit2.Call;

public class NilaiSidangRepository {
    private ApiService apiInterface;

    public NilaiSidangRepository(ApiService apiInterface) {
        this.apiInterface = apiInterface;
    }

    public NilaiSidang createNilaiSidang(String tanggal, String pukul, String ruang, String pemb1_clo1, String pemb1_clo2, String pemb1_clo3, String pemb2_clo1, String pemb2_clo2, String pemb2_clo3, String peng1_clo1, String peng1_clo2, String peng1_clo3, String peng2_clo1, String peng2_clo2, String peng2_clo3, double total, String indeks, String batas_revisi, String status, String catatan_revisi, int tugas_akhir_id) throws Exception {
        Call<NilaiSidang> call = apiInterface.createNilaiSidang(tanggal, pukul, ruang, pemb1_clo1, pemb1_clo2, pemb1_clo3, pemb2_clo1, pemb2_clo2, pemb2_clo3, peng1_clo1, peng1_clo2, peng1_clo3, peng2_clo1, peng2_clo2, peng2_clo3, total, indeks, batas_revisi, status, catatan_revisi, tugas_akhir_id);
        return call.execute().body();
    }

    public NilaiSidang updateNilaiSidang(int sidang_nilai_id, String tanggal, String pukul, String ruang, String pemb1_clo1, String pemb1_clo2, String pemb1_clo3, String pemb2_clo1, String pemb2_clo2, String pemb2_clo3, String peng1_clo1, String peng1_clo2, String peng1_clo3, String peng2_clo1, String peng2_clo2, String peng2_clo3, double total, String indeks, String batas_revisi, String justifikasi, String status, String catatan_revisi) throws Exception {
        Call<NilaiSidang> call = apiInterface.updateNilaiSidang(sidang_nilai_id, tanggal,pukul, ruang, pemb1_clo1, pemb1_clo2, pemb1_clo3, pemb2_clo1, pemb2_clo2, pemb2_clo3, peng1_clo1, peng1_clo2, peng1_clo3, peng2_clo1, peng2_clo2, peng2_clo3, total, indeks, batas_revisi, justifikasi,status, catatan_revisi);
        return call.execute().body();
    }

    public List<NilaiSidang> searchAllNilaiSidangBy(String parameter, String query) throws Exception{
        Call<List<NilaiSidang>> call = apiInterface.searchAllNilaiSidangBy(parameter, query);
        return call.execute().body();
    }
}
