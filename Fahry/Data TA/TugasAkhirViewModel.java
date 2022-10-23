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
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.models.TugasAkhir;
import org.d3ifcool.finpro.repository.TugasAkhirRepository;

import java.util.List;

public class TugasAkhirViewModel extends ViewModel {
    private ConnectionHelper connectionHelper = new ConnectionHelper();

    private final MutableLiveData<List<TugasAkhir>> _getListTugasAkhir = new MutableLiveData<List<TugasAkhir>>();
    public LiveData<List<TugasAkhir>> getListTugasAkhir() { return _getListTugasAkhir; }

    private final MutableLiveData<Boolean> _isEmptyListTugasAkhir = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isEmptyListTugasAkhir() { return _isEmptyListTugasAkhir; }

    private final MutableLiveData<Boolean> _isProgressVisible = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isProgressVisible() { return _isProgressVisible; }

    private final MutableLiveData<String> _messageFailedEditor = new MutableLiveData<String>();
    public LiveData<String> messageFailedEditor() { return _messageFailedEditor; }

    private final MutableLiveData<Boolean> _isSuccess = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isSuccess() { return _isSuccess; }

    private TugasAkhirRepository tugasAkhirRepository;

    public TugasAkhirViewModel() {
        ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
        tugasAkhirRepository = new TugasAkhirRepository(apiInterface);
    }

    public void getTugasAkhir(Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListTugasAkhir.postValue((List<TugasAkhir>) tugasAkhirRepository.getTugasAkhir());
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();

    }

    public void getSearchTugasAkhirBy(String parameter, String query, Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListTugasAkhir.postValue((List<TugasAkhir>) tugasAkhirRepository.getSearchTugasAkhirBy(parameter, query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();

    }

    public void getSearchPengujiBy(String parameter, String query, Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListTugasAkhir.postValue((List<TugasAkhir>) tugasAkhirRepository.getSearchPengujiBy(parameter, query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();

    }
}
