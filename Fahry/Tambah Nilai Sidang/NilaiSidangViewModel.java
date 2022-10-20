package org.d3ifcool.finpro.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.models.NilaiSidang;
import org.d3ifcool.finpro.repository.NilaiSidangRepository;

import java.util.List;

public class NilaiSidangViewModel extends ViewModel {
    private ConnectionHelper connectionHelper = new ConnectionHelper();

    private final MutableLiveData<List<NilaiSidang>> _getListNilaiSidang = new MutableLiveData<List<NilaiSidang>>();
    public LiveData<List<NilaiSidang>> getListNilaiSidang() { return _getListNilaiSidang; }

    private final MutableLiveData<Boolean> _isProgressVisible = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isProgressVisible() { return _isProgressVisible; }

    private final MutableLiveData<Boolean> _isSuccess = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isSuccess() { return _isSuccess; }

    private final MutableLiveData<String> _messageFailedEditor = new MutableLiveData<String>();
    public LiveData<String> messageFailedEditor() { return _messageFailedEditor; }

    private NilaiSidangRepository nilaiSidangRepository;

    public NilaiSidangViewModel() {
        ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
        nilaiSidangRepository = new NilaiSidangRepository(apiInterface);
    }

    public void createNilaiSidang(String tanggal, String pukul, String ruang, String pemb1_clo1, String pemb1_clo2, String pemb1_clo3, String pemb2_clo1, String pemb2_clo2, String pemb2_clo3, String peng1_clo1, String peng1_clo2, String peng1_clo3, String peng2_clo1, String peng2_clo2, String peng2_clo3, double total, String indeks, String batas_revisi, String status, String catatan_revisi, int tugas_akhir_id, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    nilaiSidangRepository.createNilaiSidang(tanggal, pukul, ruang, pemb1_clo1, pemb1_clo2, pemb1_clo3, pemb2_clo1, pemb2_clo2, pemb2_clo3, peng1_clo1, peng1_clo2, peng1_clo3, peng2_clo1, peng2_clo2, peng2_clo3, total, indeks, batas_revisi, status, catatan_revisi,tugas_akhir_id);
                    _isProgressVisible.postValue(false);
                    _isSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void updateNilaiSidang(int sidang_nilai_id, String tanggal, String pukul, String ruang, String pemb1_clo1, String pemb1_clo2, String pemb1_clo3, String pemb2_clo1, String pemb2_clo2, String pemb2_clo3, String peng1_clo1, String peng1_clo2, String peng1_clo3, String peng2_clo1, String peng2_clo2, String peng2_clo3, double total, String indeks, String batas_revisi, String justifikasi, String status, String catatan_revisi, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    nilaiSidangRepository.updateNilaiSidang(sidang_nilai_id, tanggal, pukul, ruang, pemb1_clo1, pemb1_clo2, pemb1_clo3, pemb2_clo1, pemb2_clo2, pemb2_clo3, peng1_clo1, peng1_clo2, peng1_clo3, peng2_clo1, peng2_clo2, peng2_clo3, total, indeks, batas_revisi, justifikasi, status, catatan_revisi);
                    _isProgressVisible.postValue(false);
                    _isSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }


    public void searchAllNilaiSidangBy(String parameter, String query, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListNilaiSidang.postValue((List<NilaiSidang>) nilaiSidangRepository.searchAllNilaiSidangBy(parameter, query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }
}
