package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BimbinganManager {

    private BimbinganContract.ViewModel viewModel;
    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public BimbinganManager(BimbinganContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initContext(Context context){
        this.context = context;
    }

    public void getAllBimbingan(String token){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceBimbingan.getAllBimbingan("Bearer "+token);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            Mahasiswa[] mahasiswas = new Gson().fromJson(jsonObject.getJSONArray("mahasiswa").toString(),Mahasiswa[].class);
                            List<Mahasiswa> mahasiswaList = Arrays.asList(mahasiswas);
                            viewModel.onGetListMahasiswa(mahasiswaList);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        viewModel.onMessage("EmptyList");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getBimbinganByParameter(String token, String username){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceBimbingan.getBimbinganByParameter("Bearer "+token, username);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            Bimbingan[] bimbinganArr = new Gson().fromJson(jsonObject.getJSONArray("bimbingan").toString(),Bimbingan[].class);
                            List<Bimbingan> bimbinganList = Arrays.asList(bimbinganArr);

                            Dosen dosen = new Gson().fromJson(jsonObject.getJSONObject("dosen").toString(),Dosen.class);
                            Mahasiswa mahasiswa = new Gson().fromJson(jsonObject.getJSONObject("mahasiswa").toString(),Mahasiswa.class);
                            viewModel.onMessage("HideProgressDialog");
                            viewModel.onGetListBimbingan(bimbinganList,dosen,mahasiswa);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void createBimbingan(String token, String bimbingan_review, String bimbingan_tanggal, String username){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceBimbingan.createBimbingan("Bearer "+token,bimbingan_review, bimbingan_tanggal, username);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(response.message());
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getBimbingan(){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.getBimbingan();
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
//                        viewModel.onGetListBimbingan(response.body());
                    } else {
                        viewModel.onMessage("EmptyList");
                    }
                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateBimbingan(String token, int bimbingan_id, String bimbingan_review, String bimbingan_tanggal){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceBimbingan.updateBimbingan("Bearer "+token,bimbingan_id, bimbingan_review, bimbingan_tanggal);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteBimbingan(String token, int bimbingan_id){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.deleteBimbingan("Bearer "+token,bimbingan_id);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateBimbinganStatus(String token, int bimbingan_id, String bimbingan_status){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.updateBimbinganStatus("Bearer "+token,bimbingan_id, bimbingan_status);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onSuccess");
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    /*public void searchBimbinganAllBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.searchBimbinganAllBy(parameter, query);
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListBimbingan(response.body());
                    } else {
                        viewModel.isEmptyListBimbingan();
                    }

                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }*/

    /*public void searchBimbinganObjectBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.searchBimbinganBy(parameter, query);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage("onGetObjectBimbingan");
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }*/

    /*public void searchBimbinganAllByTwo(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.searchBimbinganAllByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListBimbinganSearch(response.body());
                    } else {
                        viewModel.isEmptyListBimbingan();
                    }

                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }*/
    
    /*public void searchSiapSidang(int jumlah_bimbingan){

        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<SiapSidang>> call = apiInterfaceBimbingan.searchSiapSidang(jumlah_bimbingan);
            call.enqueue(new Callback<List<SiapSidang>>() {
                @Override
                public void onResponse(Call<List<SiapSidang>> call, Response<List<SiapSidang>> response) {
                    viewModel.onMessage("HideProgressDialog");
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onGetListSiapSidang(response.body());
                    } else {
                        viewModel.isEmptyListSiapSidang();
                    }
                }

                @Override
                public void onFailure(Call<List<SiapSidang>> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }*/

}
