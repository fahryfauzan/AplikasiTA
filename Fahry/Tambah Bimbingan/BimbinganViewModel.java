package org.d3ifcool.finpro.viewModel;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.SiapSidang;
import org.d3ifcool.finpro.repository.BimbinganRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BimbinganViewModel extends ViewModel {

    private ConnectionHelper connectionHelper = new ConnectionHelper();

    private final MutableLiveData<List<Bimbingan>> _getListBimbingan = new MutableLiveData<List<Bimbingan>>();
    public LiveData<List<Bimbingan>> getListBimbingan() { return _getListBimbingan; }

    private final MutableLiveData<List<Bimbingan>> _getListBimbinganSearch = new MutableLiveData<List<Bimbingan>>();
    public LiveData<List<Bimbingan>> getListBimbinganSearch() { return _getListBimbinganSearch; }

    private final MutableLiveData<Boolean> _isEmptyListBimbingan = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isEmptyListBimbingan() { return _isEmptyListBimbingan; }

    private final MutableLiveData<List<SiapSidang>> _getListSiapSidang = new MutableLiveData<List<SiapSidang>>();
    public LiveData<List<SiapSidang>> getListSiapSidang() { return _getListSiapSidang; }

    private final MutableLiveData<Boolean> _isEmptyListSiapSidang = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isEmptyListSiapSidang() { return _isEmptyListSiapSidang; }

    private final MutableLiveData<Boolean> _isProgressVisible = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isProgressVisible() { return _isProgressVisible; }

    private final MutableLiveData<Boolean> _isMessageSuccess = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isMessageSuccess() { return _isMessageSuccess; }

    private final MutableLiveData<String> _messageFailedEditor = new MutableLiveData<String>();
    public LiveData<String> messageFailedEditor() { return _messageFailedEditor; }

    private BimbinganRepository bimbinganRepository;

    public BimbinganViewModel(){
        ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
        bimbinganRepository = new BimbinganRepository(apiInterface);
    }

    public void searchBimbinganAllBy(String parameter, String query, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListBimbingan.postValue((List<Bimbingan>) bimbinganRepository.searchBimbinganAllBy(parameter, query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _isEmptyListBimbingan.postValue(true);
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void searchBimbinganAllByTwo(String parameter1, String query1, String parameter2, String query2, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListBimbingan.postValue((List<Bimbingan>) bimbinganRepository.searchBimbinganAllByTwo(parameter1, query1, parameter2, query2));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _isEmptyListBimbingan.postValue(true);
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void createBimbingan(String dsn_nip, String bimbingan_review, String bimbingan_tanggal, String bimbingan_status, int plotting_id, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    bimbinganRepository.createBimbingan(dsn_nip, bimbingan_review, bimbingan_tanggal, bimbingan_status, plotting_id);
                    _isProgressVisible.postValue(false);
                    _isMessageSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();


    }

    public void updateBimbingan(String bimbingan_id, String dsn_nip, String bimbingan_review, String bimbingan_tanggal, int plotting_id, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    bimbinganRepository.updateBimbingan(bimbingan_id, dsn_nip, bimbingan_review, bimbingan_tanggal, plotting_id);
                    _isProgressVisible.postValue(false);
                    _isMessageSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();

    }

    public void updateBimbinganStatus(String bimbingan_id, String bimbingan_status, Context context){

        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    bimbinganRepository.updateBimbinganStatus(bimbingan_id, bimbingan_status);
                    _isProgressVisible.postValue(false);
                    _isMessageSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void deleteBimbingan(String bimbingan_id, Context context){

        if (connectionHelper.isConnected(context)){
            _isProgressVisible.setValue(true);
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.deleteBimbingan(bimbingan_id);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    _isProgressVisible.setValue(false);
                    _isMessageSuccess.setValue(true);
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    _isProgressVisible.setValue(false);
                    _messageFailedEditor.setValue(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }
    public void searchSiapSidang(int jumlah_bimbingan, Context context){

        if (connectionHelper.isConnected(context)){
            _isProgressVisible.setValue(true);
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<SiapSidang>> call = apiInterfaceBimbingan.searchSiapSidang(jumlah_bimbingan);
            call.enqueue(new Callback<List<SiapSidang>>() {
                @Override
                public void onResponse(Call<List<SiapSidang>> call, Response<List<SiapSidang>> response) {
                    _isProgressVisible.setValue(false);
                    if (response.body() != null && response.isSuccessful()) {
                        _getListSiapSidang.setValue(response.body());
                    } else {
                        _isEmptyListSiapSidang.setValue(true);
                    }
                }

                @Override
                public void onFailure(Call<List<SiapSidang>> call, Throwable t) {
                    _isProgressVisible.setValue(false);
                    _messageFailedEditor.setValue(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }
}
