package org.d3ifcool.finpro.repository;

import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.models.Bimbingan;

import java.util.List;

import retrofit2.Call;

public class BimbinganRepository {

    private ApiService apiInterface;

    public BimbinganRepository(ApiService apiInterface){
        this.apiInterface = apiInterface;
    }

    public List<Bimbingan> searchBimbinganAllBy(String parameter, String query) throws Exception {
        Call<List<Bimbingan>> call = apiInterface.searchBimbinganAllBy(parameter, query);
        return call.execute().body();
    }

    public List<Bimbingan> searchBimbinganAllByTwo(String parameter1, String query1, String parameter2, String query2) throws Exception {
        Call<List<Bimbingan>> call = apiInterface.searchBimbinganAllByTwo(parameter1, query1, parameter2, query2);
        return call.execute().body();
    }

    public Bimbingan createBimbingan(String dsn_nip, String bimbingan_review, String bimbingan_tanggal, String bimbingan_status, int plotting_id) throws Exception {
        Call<Bimbingan> call = apiInterface.createBimbingan(dsn_nip, bimbingan_review, bimbingan_tanggal, bimbingan_status, plotting_id);
        return call.execute().body();
    }

    public Bimbingan updateBimbingan(String bimbingan_id, String dsn_nip, String bimbingan_review, String bimbingan_tanggal, int plotting_id) throws Exception {
        Call<Bimbingan> call = apiInterface.updateBimbingan(bimbingan_id, dsn_nip,bimbingan_review, bimbingan_tanggal, plotting_id);
        return call.execute().body();
    }

    public Bimbingan updateBimbinganStatus(String bimbingan_id, String bimbingan_status) throws Exception {
        Call<Bimbingan> call = apiInterface.updateBimbinganStatus(bimbingan_id, bimbingan_status);
        return call.execute().body();
    }
}
