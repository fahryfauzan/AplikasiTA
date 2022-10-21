package org.d3ifcool.finpro.core.models.manager;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SidangManager {

    private SidangContract.ViewModel viewModel;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public SidangManager(SidangContract.ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void getMahasiswaSidangByUsername(String token, String username){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Mahasiswa> call = apiInterfaceDosen.getMahasiswaSidangByUsername("Bearer "+token,username);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onGetObjectMahasiswa(response.body());
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void saveNilaiSidang(String token, String username, String clo1, String clo2, String clo3){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.saveNilaiSidang("Bearer "+token,username, clo1, clo2, clo3);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onMessage("onSuccess");
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void saveReviewSidang(String token, String username, String review, String status){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.saveReviewSidang("Bearer "+token,username,review,status);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onMessage("onSuccess");
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFormRevisi(String token, MultipartBody.Part part){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.uploadFormRevisi("Bearer "+token,part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onMessage("onSuccess");
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadDraftJurnal(String token, String mhs_nim, MultipartBody.Part part){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.uploadDraftJurnal("Bearer "+token,mhs_nim,part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        try {
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.has("success")){
                                viewModel.onMessage("onSuccess");
                            }else{
                                viewModel.onMessage(jsonObject.getString("message"));
                            }
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
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void checkFormRevisi (String token, String mhs_nim){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.checkFormRevisi("Bearer "+token,mhs_nim);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null){
                        if (response.isSuccessful()){
                            viewModel.onMessage("HideProgressDialog");
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                viewModel.onMessage(jsonObject.getString("message"));
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void downloadFormRevisi (String token, String mhs_nim){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.downloadFormRevisi("Bearer "+token,mhs_nim);
            call.enqueue(new Callback<ResponseBody>() {
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    viewModel.onMessage("HideProgressDialog");
                    String filename = response.headers().get("Content-Disposition").split("=")[1];
                    viewModel.onGetBody(response.body(),filename);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.clone().enqueue(this);
//                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getSidangByNIM(String token, String username){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<Sidang> call = apiInterfaceDosen.getSidangByNIM("Bearer "+token,username);
            call.enqueue(new Callback<Sidang>() {
                @Override
                public void onResponse(Call<Sidang> call, Response<Sidang> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        if (response.body() != null){
                            viewModel.onGetObjectSidang(response.body());
                        }else{
                            viewModel.onMessage("EmptyObject");
                        }
                    } else {
                        call.clone().enqueue(this);
                    }
                }

                @Override
                public void onFailure(Call<Sidang> call, Throwable t) {
                    viewModel.onMessage("HideProgressDialog");
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStatusSidang(String token, String username, String status){
        if (connectionHelper.isConnected(context)){
            viewModel.onMessage("ShowProgressDialog");
            ApiService apiInterfaceDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<ResponseBody> call = apiInterfaceDosen.updateStatusSidang("Bearer "+token,username,status);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        viewModel.onMessage("HideProgressDialog");
                        try {
                            String s = response.body().string();
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.has("success")){
                                viewModel.onMessage("onSuccess");
                            }else{
                                viewModel.onMessage(jsonObject.getString("message"));
                            }
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
                    viewModel.onMessage(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }
}
