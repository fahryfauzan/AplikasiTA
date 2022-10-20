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
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Plotting;
import org.d3ifcool.finpro.repository.PlottingRepository;

import java.util.List;

public class PlottingViewModel extends ViewModel {

    private ConnectionHelper connectionHelper = new ConnectionHelper();

    private final MutableLiveData<List<Plotting>> _getListPlotting = new MutableLiveData<List<Plotting>>();
    public LiveData<List<Plotting>> getListPlotting() { return _getListPlotting; }

    private final MutableLiveData<Boolean> _isEmptyListPlotting = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isEmptyListPlotting() { return _isEmptyListPlotting; }

    private final MutableLiveData<Boolean> _isProgressVisible = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isProgressVisible() { return _isProgressVisible; }

    private final MutableLiveData<Boolean> _isMessageSuccess = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isMessageSuccess() { return _isMessageSuccess; }

    private final MutableLiveData<String> _messageFailedEditor = new MutableLiveData<String>();
    public LiveData<String> messageFailedEditor() { return _messageFailedEditor; }


    private PlottingRepository plottingRepository;

    public PlottingViewModel() {
        ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
        plottingRepository = new PlottingRepository(apiInterface);
    }

    public void createPlotting(String dsn_nip, int tugas_akhir_id, String plotting_status, String plotting_tanggal, String plotting_waktu, String plotting_ruang, Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    plottingRepository.createPlotting(dsn_nip, tugas_akhir_id, plotting_status, plotting_tanggal, plotting_waktu, plotting_ruang);
                    _isProgressVisible.postValue(false);
                    _isMessageSuccess.postValue(true);
                } catch (Exception e) {
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void searchDosenBimbinganAllBy(String parameter, String query, Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListPlotting.postValue((List<Plotting>) plottingRepository.searchDosenBimbinganAllBy(parameter,query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _isEmptyListPlotting.postValue(true);
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }

    public void searchDosenPengujiAllBy(String parameter, String query, Context context){
        new Thread(() -> {
            if (connectionHelper.isConnected(context)){
                try {
                    _isProgressVisible.postValue(true);
                    _getListPlotting.postValue((List<Plotting>) plottingRepository.searchDosenPengujiAllBy(parameter,query));
                    _isProgressVisible.postValue(false);
                } catch (Exception e) {
                    _isEmptyListPlotting.postValue(true);
                    _messageFailedEditor.postValue(e.getLocalizedMessage());
                    _isProgressVisible.postValue(false);
                }
            } else {
                Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
            }
        }).start();
    }
}
