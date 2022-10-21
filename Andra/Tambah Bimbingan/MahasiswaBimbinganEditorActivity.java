package org.d3ifcool.finpro.mahasiswa.activities.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.databinding.ActivityMahasiswaBimbinganEditorBinding;

import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_BIMBINGAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;

public class MahasiswaBimbinganEditorActivity extends AppCompatActivity implements BimbinganContract.ViewModel{

    private ActivityMahasiswaBimbinganEditorBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mahasiswa_bimbingan_editor);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setBimbinganPresenter(this);
        mBinding.setPresenter(mediator.getBimbinganPresenter());

        setTitle(getString(R.string.button_click_tambah_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mediator.message(message.setComponent("MethodHelper").setEvent("set"));
        mediator.setTextView(mBinding.actMhsBimbinganTextviewTanggal);
        mediator.message(message.setComponent("MethodHelper").setEvent("setDataPicker"));

        if (getIntent().getParcelableExtra(EXTRA_BIMBINGAN) != null){
            setTitle(getString(R.string.button_click_ubah_bimbingan));
            message.setBimbingan(getIntent().getParcelableExtra(EXTRA_BIMBINGAN));
            mediator.message(message.setComponent("BimbinganPresenter").setEvent("showData").setBimbingan(message.getBimbingan()));
            mBinding.actMhsInfoButtonSimpan.setText(getString(R.string.button_click_ubah_bimbingan));
            message.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()).setEvent("bimbingan"));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList, Dosen dosen, Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswaList) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "onCreate":
                if (message.isEnabled()){
                    mediator.message(message.setComponent("BimbinganPresenter").setEvent("updateBimbingan"));
                }else{
                    mediator.message(message.setComponent("BimbinganPresenter").setEvent("createBimbingan").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
                }
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil"));
                finish();
                break;
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "EmptyList":
                mediator.message(message.setComponent("RelativeLayout").setEvent("setVisibility").setVisibility(View.GONE));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
