package org.d3ifcool.finpro.repository;

import org.d3ifcool.finpro.core.api.ApiService;

import org.d3ifcool.finpro.core.models.Plotting;

import java.util.List;

import retrofit2.Call;

public class PlottingRepository {

    private ApiService apiInterface;

    public PlottingRepository(ApiService apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Plotting createPlotting(String dsn_nip, int tugas_akhir_id, String plotting_status, String plotting_tanggal, String plotting_waktu, String plotting_ruang) throws Exception {
        Call<Plotting> call = apiInterface.createPlotting(dsn_nip, tugas_akhir_id, plotting_status, plotting_tanggal, plotting_waktu, plotting_ruang);
        return  call.execute().body();
    }

    public List<Plotting> searchDosenBimbinganAllBy(String parameter, String query) throws Exception {
        Call<List<Plotting>> call = apiInterface.searchDosenBimbinganBy(parameter, query);
        return call.execute().body();
    }

    public List<Plotting> searchDosenPengujiAllBy(String parameter, String query) throws Exception {
        Call<List<Plotting>> call = apiInterface.searchDosenPengujiBy(parameter, query);
        return call.execute().body();
    }
}
