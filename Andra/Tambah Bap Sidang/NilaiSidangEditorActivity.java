package org.d3ifcool.finpro.dosen.activities.editor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.mediators.Mediator;
import org.d3ifcool.finpro.core.mediators.ConcreteMediator;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.databinding.ActivityNilaiSidangEditorBinding;

import okhttp3.ResponseBody;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_SECOND;

public class NilaiSidangEditorActivity extends AppCompatActivity implements SidangContract.ViewModel {

    private ActivityNilaiSidangEditorBinding mBinding;
    private Message message = new Message();
    private Mediator mediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_nilai_sidang_editor);
        mBinding.setLifecycleOwner(this);
        mediator = new ConcreteMediator(this);
        mediator.setSidangPresenter(this);
        mBinding.setPresenter(mediator.getSidangPresenter());

        mediator.setTitleContextWithHomeAsUp("Nilai Sidang "+getIntent().getStringExtra(EXTRA_SECOND));

        mediator.message(message.setComponent("ProgressDialog").setEvent("set"));
        mediator.message(message.setComponent("SessionManager").setEvent("set"));
        mBinding.infoClo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("SweetAlert").setEvent("onInfo").setText("deskripsi_clo_1"));
            }
        });
        mBinding.infoClo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("SweetAlert").setEvent("onInfo").setText("deskripsi_clo_2"));
            }
        });
        mBinding.infoClo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediator.message(message.setComponent("SweetAlert").setEvent("onInfo").setText("deskripsi_clo_3"));
            }
        });

        mediator.message(message.setComponent("SidangPresenter").setEvent("getSidangByNIM").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mediator.message(message.setComponent("Toolbar").setVisibility(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetObjectSidang(Sidang sidang) {
        message.setSidang(sidang);
        mediator.message(message.setComponent("SidangPresenter").setEvent("showData").setText(getIntent().getStringExtra(EXTRA_SECOND)));
        if (mediator.getSessionPengguna().equalsIgnoreCase("mahasiswa")){
            if (TextUtils.isEmpty(mBinding.actDsnEdittextClo1.getText())){
                mBinding.actDsnEdittextClo1.setHint("Dosen Belum menginputkan nilai");
            }
            if (TextUtils.isEmpty(mBinding.actDsnEdittextClo2.getText())){
                mBinding.actDsnEdittextClo1.setHint("Dosen Belum menginputkan nilai");
            }
            if (TextUtils.isEmpty(mBinding.actDsnEdittextClo3.getText())){
                mBinding.actDsnEdittextClo1.setHint("Dosen Belum menginputkan nilai");
            }
            mBinding.actDsnEdittextClo1.setEnabled(false);
            mBinding.actDsnEdittextClo2.setEnabled(false);
            mBinding.actDsnEdittextClo3.setEnabled(false);
            mBinding.actDsnInfoButtonUbah.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {

    }

    @Override
    public void onGetBody(ResponseBody body, String filename) {

    }

    @Override
    public void onMessage(String messages) {
        switch (messages){
            case "ShowProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("show"));
                break;
            case "HideProgressDialog":
                mediator.message(message.setComponent("ProgressDialog").setEvent("dismiss"));
                break;
            case "onSuccess":
                mediator.message(message.setComponent("Toasty").setEvent("Success").setText("Berhasil menyimpan data nilai"));
                finish();
                break;
            case "saveNilai":
                mediator.message(message.setComponent("SidangPresenter").setEvent("saveNilai").setText(getIntent().getStringExtra(EXTRA_DEFAULT)));
                break;
            default:
                mediator.message(message.setComponent("Toasty").setEvent("Warning").setText(messages));
                break;
        }
    }
}
