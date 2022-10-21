package org.d3ifcool.finpro.core.mediators;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.AuthActivity;
import org.d3ifcool.finpro.adapters.InformasiViewAdapter;
import org.d3ifcool.finpro.core.adapters.KegiatanAdapter;
import org.d3ifcool.finpro.core.helpers.FileHelper;
import org.d3ifcool.finpro.core.helpers.Message;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.AuthContract;
import org.d3ifcool.finpro.core.interfaces.BimbinganContract;
import org.d3ifcool.finpro.core.interfaces.DosenContract;
import org.d3ifcool.finpro.core.interfaces.InformasiContract;
import org.d3ifcool.finpro.core.interfaces.JadwalContract;
import org.d3ifcool.finpro.core.interfaces.MahasiswaContract;
import org.d3ifcool.finpro.core.interfaces.PlottingContract;
import org.d3ifcool.finpro.core.interfaces.ProdiContract;
import org.d3ifcool.finpro.core.interfaces.SidangContract;
import org.d3ifcool.finpro.core.models.JadwalKegiatan;
import org.d3ifcool.finpro.core.models.manager.AuthManager;
import org.d3ifcool.finpro.core.presenters.AuthPresenter;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.core.presenters.JadwalPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.core.presenters.ProdiPresenter;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;
import org.d3ifcool.finpro.dosen.activities.DosenProfilActivity;
import org.d3ifcool.finpro.dosen.activities.editor.DosenProfilUbahActivity;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenBimbinganViewAdapter;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenMahasiswaBimbinganViewAdapter;
import org.d3ifcool.finpro.dosen.fragments.DosenMahasiswaBimbinganFragment;
import org.d3ifcool.finpro.dosen.fragments.DosenMahasiswaSidangFragment;
import org.d3ifcool.finpro.fragments.InformasiFragment;
import org.d3ifcool.finpro.lak.LAKJadwalKegiatanFragment;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJadwalKegiatanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaPemberitahuanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaProfilActivity;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaProfilUbahActivity;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaBimbinganViewAdapter;
import org.d3ifcool.finpro.mahasiswa.fragments.MahasiswaTugasAkhirFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.SidangFragment;
import org.d3ifcool.finpro.prodi.activities.KoorProfilActivity;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiMahasiswaEditorActivity;
import org.d3ifcool.finpro.prodi.activities.editor.ProdiProfilUbahActivity;
import org.d3ifcool.finpro.prodi.adapters.ProdiDosenViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiMahasiswaViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlotPembimbingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiPlottingViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiSKTAViewAdapter;
import org.d3ifcool.finpro.prodi.adapters.ProdiSidangViewAdapter;
import org.d3ifcool.finpro.prodi.fragments.ProdiDosenFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiMahasiswaFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiPlottingFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSKTAFragment;
import org.d3ifcool.finpro.prodi.fragments.ProdiSidangFragment;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DEFAULT;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_DOSEN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_KOORDINATOR;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_MAHASISWA;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EXTRA_SECOND;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_PDF;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.FILE_TYPE_XLSX;

public class ConcreteMediator implements Mediator {

    private AppCompatActivity activity;
    private AuthManager authManager;
    private MethodHelper methodHelper;
    private FileHelper fileHelper;
    private String dosenUsername;

    private PlottingPresenter plottingPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private DosenPresenter dosenPresenter;
    private AuthPresenter authPresenter;
    private ProdiPresenter prodiPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private JadwalPresenter jadwalPresenter;
    private InformasiPresenter informasiPresenter;
    private SidangPresenter sidangPresenter;

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private TextView textView;
    private Spinner spinner;
    private CircleImageView circleImageView;
    private CardView cardView;
    private Button button;
    private FloatingActionButton floatingActionButton;
    private CompactCalendarView compactCalendarView;

    private ProdiDosenViewAdapter dosenViewAdapter;
    private ProdiMahasiswaViewAdapter mahasiswaViewAdapter;
    private ProdiPlottingViewAdapter plottingViewAdapter;
    private InformasiViewAdapter informasiViewAdapter;
    private ProdiSKTAViewAdapter prodiSKTAViewAdapter;
    private ProdiPlotPembimbingViewAdapter prodiPlotPembimbingViewAdapter;
    private MahasiswaBimbinganViewAdapter mahasiswaBimbinganViewAdapter;
    private DosenMahasiswaBimbinganViewAdapter dosenMahasiswaBimbinganViewAdapter;
    private DosenBimbinganViewAdapter dosenBimbinganViewAdapter;
    private ProdiSidangViewAdapter prodiSidangViewAdapter;

    private SessionManager sessionManager;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private BottomNavigationView bottomNavigationView;

    public ConcreteMediator(AppCompatActivity activity) {
        this.activity = activity;
        authManager  = new AuthManager();
        methodHelper = new MethodHelper(activity);
    }

    private static final String SET = "set";

    @Override
    public void message(Message message){
        switch (message.getComponent()){
            case "JadwalPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        jadwalPresenter.getAllJadwal(getSessionToken());
                        break;
                    case "createJadwal":
                        jadwalPresenter.createJadwal(getSessionToken());
                        break;
                    case "getJadwalByLike":
                        jadwalPresenter.getJadwalByParameter(getSessionToken(),message.getText());
                        break;
                    case "showKegiatanList":
                        switch (message.getText()){
                            case "ProdiMahasiswaFragment":
                                if (message.getKegiatanList().size() > 0){
                                    JadwalKegiatan jadwalKegiatan = message.getKegiatanList().get(0);
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    try {
                                        Date start = format.parse(jadwalKegiatan.getTanggal_mulai());
                                        Date end = format.parse(jadwalKegiatan.getTanggal_akhir());
                                        Date now = new Date();
                                        if (now.after(start) && now.before(end)){
                                            relativeLayout.setVisibility(View.VISIBLE);
                                        }else{
                                            relativeLayout.setVisibility(View.GONE);
                                        }
                                        button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                message(message.setComponent("AlertDialog").setEvent("set"));
                                                message(message.setComponent("AlertDialog").setEvent("validasiSidang"));
                                            }
                                        });
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        break;
                }
                break;
            case "BimbinganPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        bimbinganPresenter.getAllBimbingan(getSessionToken());
                        break;
                    case "getBimbinganByUsername":
                        bimbinganPresenter.getBimbinganByParameter(getSessionToken(),message.getText());
                        break;
                    case "showData":
                        Date date;
                        Locale locale = new Locale("in", "ID");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                        try {
                            date = format.parse(message.getBimbingan().getBimbingan_tanggal());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            String month_name = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(calendar.getTime());
                            bimbinganPresenter.tglBimbingan.set(calendar.get(Calendar.DATE)+" "+month_name+" "+calendar.get(Calendar.YEAR));
                            bimbinganPresenter.reviewBimbingan.set(message.getBimbingan().getBimbingan_review());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "createBimbingan":
                        bimbinganPresenter.createBimbingan(getSessionToken(),message.getText());
                        break;
                    case "updateBimbingan":
                        bimbinganPresenter.updateBimbingan(getSessionToken(),message.getBimbingan().getBimbingan_id());
                        break;
                    case "acceptBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"approve");
                        break;
                    case "rejectBimbingan":
                        bimbinganPresenter.ubahStatus(getSessionToken(),message.getBimbingan().getBimbingan_id(),"decline");
                        break;
                }
                break;
            case "ProdiPresenter":
                switch (message.getEvent()){
                    case "getProdiByNIP":
                        prodiPresenter.getProdiByNIP(getSessionToken(),getSessionUsername());
                        break;
                    case "showData":
                        prodiPresenter.nama.set(message.getKoordinator().getKoor_nama());
                        prodiPresenter.nip.set(message.getKoordinator().getKoor_nip());
                        prodiPresenter.kode.set(message.getKoordinator().getKoor_kode());
                        prodiPresenter.kontak.set(message.getKoordinator().getKoor_kontak());
                        prodiPresenter.email.set(message.getKoordinator().getKoor_email());
                        break;
                }
                break;
            case "AuthPresenter":
                switch (message.getEvent()){
                    case "getCurrentUser":
                        authPresenter.getUser(getSessionToken());
                        break;
                }
                break;
            case "SidangPresenter":
                switch (message.getEvent()){
                    case "getMahasiswaSidangByUsername":
                        sidangPresenter.getMahasiswaSidangByUsername(getSessionToken(),message.getText());
                        break;
                    case "saveNilai":
                        sidangPresenter.saveNilai(getSessionToken(),message.getText());
                        break;
                    case "saveReview":
                        sidangPresenter.saveReview(getSessionToken(),message.getText(),message.getUrl());
                        break;
                    case "getSidangByNIM":
                        sidangPresenter.getSidangByNIM(getSessionToken(),message.getText());
                        break;
                    case "showData":
                        switch (message.getText()){
                            case "Pembimbing 1":
                                sidangPresenter.clo1.set(message.getSidang().getNilai_pembimbing_1_clo1());
                                sidangPresenter.clo2.set(message.getSidang().getNilai_pembimbing_1_clo2());
                                sidangPresenter.clo3.set(message.getSidang().getNilai_pembimbing_1_clo3());
                                break;
                            case "Pembimbing 2":
                                sidangPresenter.clo1.set(message.getSidang().getNilai_pembimbing_2_clo1());
                                sidangPresenter.clo2.set(message.getSidang().getNilai_pembimbing_2_clo2());
                                sidangPresenter.clo3.set(message.getSidang().getNilai_pembimbing_2_clo3());
                                break;
                            case "Penguji 1":
                                sidangPresenter.clo1.set(message.getSidang().getNilai_penguji_1_clo1());
                                sidangPresenter.clo2.set(message.getSidang().getNilai_penguji_1_clo2());
                                sidangPresenter.clo3.set(message.getSidang().getNilai_penguji_1_clo3());
                                break;
                            case "Penguji 2":
                                sidangPresenter.clo1.set(message.getSidang().getNilai_penguji_2_clo1());
                                sidangPresenter.clo2.set(message.getSidang().getNilai_penguji_2_clo2());
                                sidangPresenter.clo3.set(message.getSidang().getNilai_penguji_2_clo3());
                                break;
                            case "review_sidang":
                                sidangPresenter.review.set(message.getSidang().getSidang_review());
                                break;
                        }
                        break;
                    case "uploadDraftJurnal":
                        sidangPresenter.uploadDraftJurnal(getSessionToken(),getSessionUsername(),fileHelper.getFile(message.getUri()));
                        break;
                    case "uploadFormRevisi":
                        sidangPresenter.uploadRevisi(getSessionToken(),fileHelper.getFile(message.getUri()));
                        break;
                    case "checkFormRevisi":
                        sidangPresenter.checkFormRevisi(getSessionToken(),message.getText());
                        break;
                    case "downloadFormRevisi":
                        sidangPresenter.downloadRevisi(getSessionToken(),message.getText());
                        break;
                    case "updateStatusSidang":
                        sidangPresenter.updateStatusSidang(getSessionToken(),message.getText(),message.getUrl());
                        break;
                }
                break;
            case "DosenPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        dosenPresenter.getAllDosen(getSessionToken());
                        break;
                    case "showData":
                        dosenPresenter.nama.set(message.getDosen().getDsn_nama());
                        dosenPresenter.nip.set(message.getDosen().getDsn_nip());
                        dosenPresenter.kode.set(message.getDosen().getDsn_kode());
                        dosenPresenter.kontak.set(message.getDosen().getDsn_kontak());
                        dosenPresenter.email.set(message.getDosen().getDsn_email());
                        dosenPresenter.kuota_bimbingan.set(String.valueOf(message.getDosen().getKuota_bimbingan()));
                        dosenPresenter.kuota_reviewer.set(String.valueOf(message.getDosen().getKuota_reviewer()));
                        break;
                    case "createDosen":
                        dosenPresenter.createDosen(getSessionToken());
                        break;
                    case "updateDosen":
                        dosenPresenter.updateDosen(getSessionToken());
                        break;
                    case "getCurrentDosen":
                        dosenPresenter.getCurrentDosen(getSessionToken());
                        break;
                    case "getDosenByNIP":
                        dosenPresenter.getDosenByNIP(getSessionToken(),message.getDosen().getDsn_nip());
                        break;
                    case "getMahasiswaSidang":
                        dosenPresenter.getMahasiswaSidang(getSessionToken());
                        break;
                    case "getMahasiswaSidangByUsername":
                        dosenPresenter.getMahasiswaSidangByUsername(getSessionToken(),message.getText());
                        break;
                }
                break;
            case "MahasiswaPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        mahasiswaPresenter.getAllMahasiswa(getSessionToken());
                        break;
                    case "getMahasiswaByNIM":
                        mahasiswaPresenter.getMahasiswaByNIM(getSessionToken(),message.getMahasiswa().getMhs_nim());
                        break;
                    case "getMahasiswaBySession":
                        mahasiswaPresenter.getMahasiswaByNIM(getSessionToken(),getSessionUsername());
                        break;
                    case "getPembimbing":
                        mahasiswaPresenter.getPembimbing(getSessionToken(),message.getMahasiswa().getPlot_pembimbing());
                        break;
                    case "deletePembimbing":
                        mahasiswaPresenter.deletePembimbing(getSessionToken(),message.getMahasiswa().getMhs_nim());
                        break;
                    case "uploadFormPengajuanPerpanjangSK":
                        mahasiswaPresenter.uploadFormPengajuanPerpanjangSK(getSessionToken(),getSessionUsername(),fileHelper.getFile(message.getUri()));
                        break;
                    case "updateSKTA":
                        message(message.setComponent("AlertDialog").setEvent("set"));
                        message(message.setComponent("AlertDialog").setEvent("updateSKTA"));
                        break;
                    case "toolbarIntent":
                        activity.startActivity(mahasiswaPresenter.toolbarIntent(message));
                        break;
                    case "showData":
                        mahasiswaPresenter.nama.set(message.getMahasiswa().getMhs_nama());
                        mahasiswaPresenter.nim.set(message.getMahasiswa().getMhs_nim());
                        mahasiswaPresenter.angkatan.set(message.getMahasiswa().getAngkatan());
                        mahasiswaPresenter.email.set(message.getMahasiswa().getMhs_email());
                        mahasiswaPresenter.telp.set(message.getMahasiswa().getMhs_kontak());
                        mahasiswaPresenter.judul.set(message.getMahasiswa().getJudul());
                        mahasiswaPresenter.judulInggris.set(message.getMahasiswa().getJudul_inggris());
                        break;
                    case "createMahasiswa":
                        mahasiswaPresenter.createMahasiswa(getSessionToken());
                        break;
                    case "updateMahasiswa":
                        mahasiswaPresenter.updateMahasiswa(getSessionToken());
                        break;
                    case "konfirmasiSidang":
                        message(message.setComponent("AlertDialog").setEvent(SET));
                        message(message.setComponent("AlertDialog").setEvent("konfirmasiSidang"));
                        break;
                    case "uploadFormSidang":
                        mahasiswaPresenter.uploadFormSidang(getSessionToken(),fileHelper.getFile(message.getUri()));
                        break;
                }
                break;
            case "PlottingPresenter":
                switch (message.getEvent()){
                    case "getAllData":
                        plottingPresenter.getAllPlotting(getSessionToken());
                        break;
                    case "uploadForm":
                        plottingPresenter.uploadForm(getSessionToken(),fileHelper.getFile(message.getUri()));
                        break;
                    case "updatePlotting":
                        plottingPresenter.updatePlotting(getSessionToken(),message.getPlotting().getId(),message.getText(),message.getUrl());
                        break;
                    case "createPlotting":
                        plottingPresenter.createPlotting(getSessionToken(),message.getText(),message.getUrl());
                        break;
                    case "checkForm":
                        plottingPresenter.checkFrom(getSessionToken());
                        break;
                }
                break;
            case "DosenBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenBimbinganViewAdapter = new DosenBimbinganViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenBimbinganViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenBimbinganViewAdapter);
                        break;
                }
                break;
            case "DosenMahasiswaBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenMahasiswaBimbinganViewAdapter = new DosenMahasiswaBimbinganViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenMahasiswaBimbinganViewAdapter.addItem(message.getItem());
                        dosenMahasiswaBimbinganViewAdapter.setPengguna(message.getText());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenMahasiswaBimbinganViewAdapter);
                        break;
                }
                break;
            case "MahasiswaBimbinganViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        mahasiswaBimbinganViewAdapter = new MahasiswaBimbinganViewAdapter(activity);
                        mahasiswaBimbinganViewAdapter.setBimbinganPresenter(bimbinganPresenter);
                        mahasiswaBimbinganViewAdapter.setToken(getSessionToken());
                        break;
                    case "addItem":
                        mahasiswaBimbinganViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(mahasiswaBimbinganViewAdapter);
                        break;
                }
                break;
            case "ProdiPlotPembimbingViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        prodiPlotPembimbingViewAdapter = new ProdiPlotPembimbingViewAdapter(activity);
                        break;
                    case "addItem":
                        prodiPlotPembimbingViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(prodiPlotPembimbingViewAdapter);
                        break;
                    case "setToken":
                        prodiPlotPembimbingViewAdapter.setToken(getSessionToken());
                        break;
                    case "setPresenter":
                        prodiPlotPembimbingViewAdapter.setMahasiswaPresenter(mahasiswaPresenter);
                        prodiPlotPembimbingViewAdapter.setMhs_nim(message.getMahasiswa().getMhs_nim());
                        break;
                }
                break;
            case "ProdiPlottingAdapter":
                switch (message.getEvent()){
                    case SET:
                        plottingViewAdapter = new ProdiPlottingViewAdapter(activity);
                        plottingViewAdapter.setToken(getSessionToken());
                        plottingViewAdapter.setPlottingPresenter(this.plottingPresenter);
                        break;
                    case "addItem":
                        plottingViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(plottingViewAdapter);
                        break;
                }
                break;
            case "ProdiSidangViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        prodiSidangViewAdapter = new ProdiSidangViewAdapter(activity);
                        break;
                    case "addItem":
                        prodiSidangViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(prodiSidangViewAdapter);
                        break;
                }
                break;
            case "ProdiSKTAViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        prodiSKTAViewAdapter = new ProdiSKTAViewAdapter(activity);
                        break;
                    case "addItem":
                        prodiSKTAViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(prodiSKTAViewAdapter);
                        break;
                }
                break;
            case "InformasiViewAdapter":
                switch (message.getEvent()){
                    case SET:
                        informasiViewAdapter = new InformasiViewAdapter(activity);
                        break;
                }
                break;
            case "ProdiMahasiswaAdapter":
                switch (message.getEvent()){
                    case SET:
                        mahasiswaViewAdapter = new ProdiMahasiswaViewAdapter(activity);
                        break;
                    case "addItem":
                        mahasiswaViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(mahasiswaViewAdapter);
                        mahasiswaViewAdapter.setPengguna(getSessionPengguna());
                        break;
                }
                break;
            case "ProdiDosenAdapter":
                switch (message.getEvent()){
                    case SET:
                        dosenViewAdapter = new ProdiDosenViewAdapter(activity);
                        break;
                    case "addItem":
                        dosenViewAdapter.addItem(message.getItem());
                        break;
                    case "setAdapter":
                        recyclerView.setAdapter(dosenViewAdapter);
                        break;
                }
                break;
            case "FileHelper":
                switch (message.getEvent()){
                    case SET:
                        fileHelper = new FileHelper(activity.getApplicationContext(),activity);
                        break;
                    case "openFileXLS":
                        fileHelper.openFileXLS(message.getBody(),message.getText());
                        break;
                    case "openFilePDF":
                        fileHelper.openFilePDF(message.getBody(),message.getText());
                        break;
                }
                break;
            case "MethodHelper":
                switch (message.getEvent()){
                    case SET:
                        methodHelper = new MethodHelper(activity);
                        break;
                    case "setDataPicker":
                        methodHelper.setDatePicker(activity, textView);
                        break;
                    case "secondDataPicker":
                        methodHelper.secondDatePicker(activity, textView);
                        break;
                }
                break;
            case "Spinner":
                switch (message.getEvent()){
                    case "setAdapter":
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(activity,android.R.layout.simple_spinner_dropdown_item,message.getItem());
                        this.spinner.setAdapter(spinnerAdapter);
                        break;
                    case "setSelection1":
                        this.spinner.setSelection(message.getItem().indexOf(message.getPlotting().getNip_dosen_1()));
                        break;
                    case "setSelection2":
                        this.spinner.setSelection(message.getItem().indexOf(message.getPlotting().getNip_dosen_2()));
                        break;
                }
                break;
            case "CircleImageView":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.circleImageView.setVisibility(message.getVisibility());
                        break;
                    case "setImage":
                        Picasso.get().load(message.getUrl()).into(circleImageView);
                        break;
                }
                break;
            case "CompactCalendar":
                switch (message.getEvent()){
                    case "setListener":
                        SimpleDateFormat formatter = new SimpleDateFormat("MMMM, yyyy",new Locale("in","ID"));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",new Locale("in","ID"));
                        textView.setText(formatter.format(new Date()));
                        compactCalendarView.removeAllEvents();
                        for (int i = 0; i < message.getKegiatanList().size(); i++) {
                            JadwalKegiatan jadwalKegiatan = message.getKegiatanList().get(i);
                            try {
                                Calendar start = Calendar.getInstance();
                                Calendar end = Calendar.getInstance();
                                start.setTime(dateFormat.parse(jadwalKegiatan.getTanggal_mulai()));
                                end.setTime(dateFormat.parse(jadwalKegiatan.getTanggal_akhir()));
                                end.add(Calendar.DATE,1);
                                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                                    compactCalendarView.addEvent(new Event(Color.RED,date.getTime(),jadwalKegiatan.getNama_kegiatan()));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        this.compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                            @Override
                            public void onDayClick(Date dateClicked) {
                                List<Event> events = compactCalendarView.getEvents(dateClicked);
                                ArrayList<String> dataList = new ArrayList<>();
                                for (int i = 0; i < events.size(); i++) {
                                    dataList.add(events.get(i).getData().toString());
                                }
                                KegiatanAdapter kegiatanAdapter = new KegiatanAdapter(activity);
                                kegiatanAdapter.setDosens(dataList);
                                recyclerView.setAdapter(kegiatanAdapter);
                            }

                            @Override
                            public void onMonthScroll(Date firstDayOfNewMonth) {
                                textView.setText(formatter.format(firstDayOfNewMonth));
                            }
                        });
                        break;
                    case "setTextColor":
                        this.textView.setTextColor(Color.parseColor(message.getColor()));
                        break;
                    case "setVisibility":
                        this.textView.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.textView.setEnabled(message.isEnabled());
                        break;
                }
                break;
            case "TextView":
                switch (message.getEvent()){
                    case "setText":
                        this.textView.setText(message.getText());
                        break;
                    case "setTextColor":
                        this.textView.setTextColor(Color.parseColor(message.getColor()));
                        break;
                    case "setVisibility":
                        this.textView.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.textView.setEnabled(message.isEnabled());
                        break;
                }
                break;
            case "Button":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.button.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.button.setEnabled(message.isEnabled());
                        break;
                    case "setText":
                        this.button.setText(message.getText());
                        break;
                    case "setOnClick":
                        switch (message.getText()){
                            case "perpanjangSK":
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mahasiswaPresenter.onPerpanjang();
                                    }
                                });
                                break;
                            case "downloadSK":
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mahasiswaPresenter.downloadSKTA(getSessionToken(),getSessionUsername());
                                    }
                                });
                                break;
                            case "uploadRevisi":
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        sidangPresenter.onUploadRevisi();
                                    }
                                });
                                break;
                        }
                        break;
                }
                break;
            case "FloatButton":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.floatingActionButton.setVisibility(message.getVisibility());
                        break;
                    case "setEnabled":
                        this.floatingActionButton.setEnabled(message.isEnabled());
                        break;
                    case "setBackground":
                        switch (message.getText()){
                            case "ic_upload":
                                this.floatingActionButton.setImageDrawable(activity.getDrawable(R.drawable.ic_fab_pa_pengajuan));
                                break;
                        }
                        break;
                    case "setOnClick":
                        switch (message.getText()){
                            case "ProdiSidangFragment":
                                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (message.getKegiatanList().size() < 1){
                                            message(message.setComponent("Toasty").setEvent("Info").setText("Jadwal Pendaftaran Belum Tersedia"));
                                        }else{
                                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            try {
                                                Date end = format.parse(message.getKegiatanList().get(0).getTanggal_akhir());
                                                Date now = new Date();
                                                if (now.before(end)){
                                                    message(message.setComponent("Toasty").setEvent("Info").setText("Periode Pendaftaran Sidang Masih Berlangsung"));
                                                }else{
                                                    mahasiswaPresenter.onUpload();
                                                }
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                break;
                        }
                        break;
                }
                break;
            case "CardView":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.cardView.setVisibility(message.getVisibility());
                        break;
                    case "setOnClick":
                        this.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectIntent(message);
                            }
                        });
                        break;
                }
                break;
            case "LinearLayout":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.linearLayout.setVisibility(message.getVisibility());
                        break;
                }
                break;
            case "RelativeLayout":
                switch (message.getEvent()){
                    case "setVisibility":
                        this.relativeLayout.setVisibility(message.getVisibility());
                        break;
                }
                break;
            case "RefreshLayout":
                switch (message.getEvent()){
                    case "setRefreshing":
                        this.refreshLayout.setRefreshing(message.isEnabled());
                        break;
                }
                break;
            case "ProgressDialog":
                switch (message.getEvent()){
                    case SET:
                        progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage(activity.getString(R.string.text_progress_dialog));
                        break;
                    case "show":
                        progressDialog.show();
                        break;
                    case "dismiss":
                        progressDialog.dismiss();
                        break;
                }
                break;
            case "SessionManager":
                switch (message.getEvent()){
                    case SET:
                        sessionManager = new SessionManager(this.activity);
                        break;
                    case "updateKoor":
                        sessionManager.updateSessionKoor(message.getKoordinator().getKoor_nip(),message.getKoordinator().getKoor_nama(),message.getKoordinator().getKoor_kode(),
                                message.getKoordinator().getKoor_kontak(),message.getKoordinator().getKoor_email());
                        break;
                    case "updateDosen":
                        sessionManager.updateSessionDosen(message.getDosen().getDsn_nama(),message.getDosen().getDsn_kode(),
                                message.getDosen().getDsn_kontak(),message.getDosen().getDsn_email());
                        break;
                    case "updateMahasiswa":
                        sessionManager.updateSessionMahasiswa(message.getMahasiswa().getMhs_nama(),message.getMahasiswa().getAngkatan(),
                                message.getMahasiswa().getMhs_kontak(),message.getMahasiswa().getMhs_email());
                        break;
                    case "createMahasiswa":
                        sessionManager.createSessionDataMahasiswa(message.getMahasiswa());
                        break;
                }
                break;
            case "SweetAlert":
                switch (message.getEvent()){
                    case "onInfo":
                        switch (message.getText()){
                            case "deskripsi_clo_1":
                                new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                        .setTitleText("Komponen Penilaian CLO 1")
                                        .setContentText(activity.getString(R.string.title_komponen_clo_1))
                                        .setConfirmText("OK")
                                        .show();
                                break;

                            case "deskripsi_clo_2":
                                new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                        .setTitleText("Komponen Penilaian CLO 2")
                                        .setContentText(activity.getString(R.string.title_komponen_clo_2))
                                        .setConfirmText("OK")
                                        .show();
                                break;

                            case "deskripsi_clo_3":
                                new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                        .setTitleText("Komponen Penilaian CLO 3")
                                        .setContentText(activity.getString(R.string.title_komponen_clo_3))
                                        .setConfirmText("OK")
                                        .show();
                                break;
                        }
                        break;
                }
                break;
            case "AlertDialog":
                switch (message.getEvent()){
                    case SET:
                        alertDialog = new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setNegativeButton("Batal",null);
                        break;

                    case "logout":
                        alertDialog
                                .setTitle(R.string.dialog_keluar_title)
                                .setMessage(R.string.dialog_keluar_text)
                                .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentKeluar = new Intent(activity, AuthActivity.class);
                                        activity.startActivity(intentKeluar);
                                        authManager.logout(sessionManager.getSessionToken());
                                        sessionManager.removeSession();
                                        authManager.initContext(activity);
                                        activity.finish();
                                    }
                                })
                                .show();
                        break;

                    case "hapus":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text);
                        break;

                    case "ubah":
                        alertDialog
                                .setTitle(R.string.dialog_ubah_title)
                                .setMessage(R.string.dialog_ubah_text);
                        break;

                    case "hapusForm":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_form_plot)
                                .setMessage(R.string.dialog_hapus_form_plot_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        plottingPresenter.showProgress();
                                        plottingPresenter.deleteForm(getSessionToken());
                                    }
                                }).show();
                        break;

                    case "hapusDataMahasiswa":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.deleteMahasiswa(getSessionToken(),message.getMahasiswa().getMhs_nim());
                                    }
                                }).show();
                        break;
                    case "hapusDataDosen":
                        alertDialog
                                .setTitle(R.string.dialog_hapus_title)
                                .setMessage(R.string.dialog_hapus_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dosenPresenter.deleteDosen(getSessionToken(),message.getDosen().getDsn_nip());
                                    }
                                }).show();
                        break;
                    case "hapusDataInformasi":
                        message(message.setComponent("AlertDialog").setEvent("hapus"));
                        alertDialog
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        informasiPresenter.deleteInformasi(getSessionToken(),message.getInformasi().getId());
                                    }
                                }).show();
                        break;

                    case "uploadForm":
                        alertDialog
                                .setTitle(R.string.dialog_upload_form_plot)
                                .setMessage(R.string.dialog_upload_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        plottingPresenter.showProgress();
                                        plottingPresenter.onIntent();
                                    }
                                }).show();
                        break;

                    case "updateSKTA":
                        alertDialog
                                .setTitle(R.string.dialog_update_skta)
                                .setMessage(R.string.dialog_update_skta_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.updateSKTA(getSessionToken(),message.getMahasiswa().getMhs_nim());
                                    }
                                }).show();
                        break;
                    case "validasiSidang":
                        alertDialog
                                .setTitle(R.string.dialog_validasi_sidang)
                                .setMessage(R.string.dialog_validasi_sidang_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.askSidang(getSessionToken());
                                    }
                                }).show();
                        break;
                    case "konfirmasiSidang":
                        alertDialog
                                .setTitle(R.string.dialog_konfirmasi_sidang)
                                .setMessage(R.string.dialog_konfirmasi_sidang_text)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mahasiswaPresenter.konfirmasiSidang(getSessionToken(),message.getText());
                                    }
                                }).show();
                        break;
                }
                break;
            case "BottomNavigationView":
                switch (message.getEvent()){
                    case "setOnNavigationItemSelectedListener":
                        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                                message(message.setComponent("BottomNavigation").setVisibility(item.getItemId()).setEvent("mahasiswa"));
                                return true;
                            }
                        });
                        break;
                    case "setFirstOpen":
                        switch (message.getText()){
                            case "mahasiswa":
                                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                                bottomNavigationView.setSelectedItemId(R.id.bottom_menu_mhs_informasi);
                                break;
                        }
                        break;
                }
                break;
            case "Toasty":
                switch (message.getEvent()){
                    case "Warning":
                        Toasty.warning(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Info":
                        Toasty.info(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Success":
                        Toasty.success(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Normal":
                        Toasty.normal(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                    case "Error":
                        Toasty.error(activity,message.getText(),Toasty.LENGTH_LONG).show();
                        break;
                }
                break;
            case "Handler":
                switch (message.getEvent()){
                    case "onSuccess":
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                plottingPresenter.onRefresh(getSessionToken());
                            }
                        },300);
                        break;
                }
                break;
            case "Toolbar":
                switch (message.getVisibility()){
                    case R.id.toolbar_menu_hapus:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                message(message.setComponent("AlertDialog").setEvent("set"));
                                message(message.setComponent("AlertDialog").setEvent("hapusDataMahasiswa"));
                                break;
                            case "dosen":
                                message(message.setComponent("AlertDialog").setEvent("set"));
                                message(message.setComponent("AlertDialog").setEvent("hapusDataDosen"));
                                break;
                            case "informasi":
                                message(message.setComponent("AlertDialog").setEvent("set"));
                                message(message.setComponent("AlertDialog").setEvent("hapusDataInformasi"));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_ubah:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                activity.startActivity(mahasiswaPresenter.toolbarIntent(message.setaClass(ProdiMahasiswaEditorActivity.class)));
                                break;
                            case "dosen":
                                activity.startActivity(dosenPresenter.toolbarIntent(message.getDosen()));
                                break;
                            case "informasi":
                                activity.startActivity(informasiPresenter.toolbarIntent(message.getInformasi()));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_ubah_yes:
                        switch (message.getEvent()){
                            case "koordinator":
                                prodiPresenter.updateProdi(getSessionToken(),message.getKoordinator().getUsername());
                                break;
                            case "dosen":
                                dosenPresenter.updateDosen(getSessionToken());
                                break;
                            case "mahasiswa":
                                mahasiswaPresenter.updateMahasiswa(getSessionToken());
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_hanya_ubah:
                        switch (message.getEvent()){
                            case "koordinator":
                                selectIntent(message.setaClass(ProdiProfilUbahActivity.class).setText("withKoor"));
                                break;
                            case "dosen":
                                selectIntent(message.setaClass(DosenProfilUbahActivity.class).setText("withDosen"));
                                break;
                            case "mahasiswa":
                                selectIntent(message.setaClass(MahasiswaProfilUbahActivity.class).setText("withMahasiswa"));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_pemberitahuan:
                        selectIntent(message.setaClass(MahasiswaPemberitahuanActivity.class));
                        break;
                    case R.id.toolbar_menu_profil:
                        switch (message.getEvent()){
                            case "mahasiswa":
                                selectIntent(message.setaClass(MahasiswaProfilActivity.class));
                                break;
                            case "dosen":
                                selectIntent(message.setaClass(DosenProfilActivity.class));
                                break;
                            default:
                                selectIntent(message.setaClass(KoorProfilActivity.class));
                                break;
                        }
                        break;
                    case R.id.toolbar_menu_jadwal_kegiatan:
                        selectIntent(message.setaClass(MahasiswaJadwalKegiatanActivity.class));
                        break;
                    case R.id.toolbar_menu_keluar:
                        message(message.setComponent("AlertDialog").setEvent(SET));
                        message(message.setComponent("AlertDialog").setEvent("logout"));
                        break;
                    case R.id.nav_menu_informasi:
                        message(message.setComponent("BottomNavigation").setVisibility(R.id.bottom_menu_dsn_informasi));
                        break;
                    case R.id.nav_menu_mahasiswa:
                        this.activity.setTitle(R.string.title_mahasiswa);
                        methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                        break;
                    case R.id.nav_menu_dosen:
                        this.activity.setTitle(R.string.title_dosen);
                        methodHelper.applyFragment(new ProdiDosenFragment(),"ProdiDosenFragment");
                        break;
                    case R.id.nav_menu_plotting:
                        this.activity.setTitle(R.string.menu_plotting_dosen);
                        methodHelper.applyFragment(new ProdiPlottingFragment(),"ProdiPlottingFragment");
                        break;
                    case R.id.nav_menu_skta:
                        this.activity.setTitle(R.string.menu_sk_ta);
                        methodHelper.applyFragment(new ProdiSKTAFragment(),"ProdiSKTAFragment");
                        break;
                    case R.id.nav_menu_sidang:
                        this.activity.setTitle(R.string.title_sidang);
                        methodHelper.applyFragment(new ProdiSidangFragment(),"ProdiSidangFragment");
                        break;
                    case android.R.id.home:
                        activity.finish();
                        break;
                }
                break;
            case "BottomNavigation":
                switch (message.getVisibility()){
                    case R.id.bottom_menu_mhs_informasi:
                        methodHelper.applyFragment(new InformasiFragment(),"InformasiFragment");
                        activity.setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_mhs_ta:
                        methodHelper.applyFragment(new MahasiswaTugasAkhirFragment(),"TugasAkhirFragment");
                        activity.setTitle(R.string.title_ta);
                        break;
                    case R.id.bottom_menu_mhs_sidang:
                        methodHelper.applyFragment(new SidangFragment(), "SidangFragment");
                        activity.setTitle(R.string.title_sidang);
                        break;
                    case R.id.bottom_menu_mahasiswa:
                        methodHelper.applyFragment(new ProdiMahasiswaFragment(),"ProdiMahasiswaFragment");
                        activity.setTitle(R.string.title_mahasiswa_ta);
                        break;
                    case R.id.bottom_menu_mhs_jadwal_kegiatan:
                        methodHelper.applyFragment(new LAKJadwalKegiatanFragment(), "LAKJadwalKegiatanFragment");
                        activity.setTitle(R.string.title_jadwal_kegiatan);
                        break;
                    case R.id.bottom_menu_dsn_informasi:
                        methodHelper.applyFragment(new InformasiFragment(), "DosenInformasiFragment");
                        this.activity.setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_dsn_bimbingan:
                        methodHelper.applyFragment(new DosenMahasiswaBimbinganFragment(),"DosenMahasiswaBimbinganFragment");
                        this.activity.setTitle(R.string.title_bimbingan);
                        break;
                    case R.id.bottom_menu_dsn_sidang:
                        methodHelper.applyFragment(new DosenMahasiswaSidangFragment(),"DosenMahasiswaSidangFragment");
                        this.activity.setTitle(R.string.title_sidang);
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public void setCircleImageView(CircleImageView circleImageView) {
        this.circleImageView = circleImageView;
    }

    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }

    public void setCompactCalendarView(CompactCalendarView compactCalendarView) {
        this.compactCalendarView = compactCalendarView;
    }

    @Override
    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(activity,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
    }

    public void setPlottingPresenter(PlottingContract.ViewModel viewModel){
        this.plottingPresenter = new PlottingPresenter(viewModel);
    }

    public void setMahasiswaPresenter(MahasiswaContract.ViewModel viewModel){
        this.mahasiswaPresenter = new MahasiswaPresenter(viewModel);
    }

    public void setDosenPresenter(DosenContract.ViewModel viewModel) {
        this.dosenPresenter = new DosenPresenter(viewModel);
    }

    @Override
    public void setInformasiPresenter(InformasiContract.ViewModel viewModel) {
        this.informasiPresenter = new InformasiPresenter(viewModel);
    }

    @Override
    public void setSidangPresenter(SidangContract.ViewModel viewModel) {
        this.sidangPresenter = new SidangPresenter(viewModel);
    }

    public void setAuthPresenter(AuthContract.ViewModel viewModel) {
        this.authPresenter = new AuthPresenter(viewModel);
    }

    public void setProdiPresenter(ProdiContract.ViewModel viewModel) {
        this.prodiPresenter = new ProdiPresenter(viewModel);
    }

    public void setBimbinganPresenter(BimbinganContract.ViewModel viewModel) {
        this.bimbinganPresenter = new BimbinganPresenter(viewModel);
    }

    public void setJadwalPresenter(JadwalContract.ViewModel viewModel) {
        this.jadwalPresenter = new JadwalPresenter(viewModel);
    }

    @Override
    public String getSessionToken() {
        return this.sessionManager.getSessionToken();
    }

    public String getSessionUsername() {
        return this.sessionManager.getSessionUsername();
    }

    public String getSessionPengguna(){
        return this.sessionManager.getSessionPengguna();
    }

    @Override
    public boolean getPermissionFile() {
        return fileHelper.permissionCheck();
    }

    public void selectIntent(Message message){
        Intent intent = new Intent(activity, message.getaClass());
        if (!TextUtils.isEmpty(message.getExtraIntent())){
            intent.putExtra(EXTRA_DEFAULT,message.getExtraIntent());
        }
        if (!TextUtils.isEmpty(message.getSecondExtraIntent())){
            intent.putExtra(EXTRA_SECOND,message.getSecondExtraIntent());
        }
        if (!TextUtils.isEmpty(message.getText())){
            if (message.getText().equalsIgnoreCase("withMahasiswa")){
                intent.putExtra(EXTRA_MAHASISWA,message.getMahasiswa());
            }
            if (message.getText().equalsIgnoreCase("withDosen")){
                intent.putExtra(EXTRA_DOSEN,message.getDosen());
            }
            if (message.getText().equalsIgnoreCase("withKoor")){
                intent.putExtra(EXTRA_KOORDINATOR,message.getKoordinator());
            }
        }
        activity.startActivity(intent);
    }

    @Override
    public void setTitleContextWithHomeAsUp(String title) {
        activity.setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setElevation(0f);
    }

    public Intent findFileIntent(String format){
        Intent intent = new Intent();
        if (format.equalsIgnoreCase("xls")){
            intent.setType(FILE_TYPE_XLS);
            String[] mimetypes = {FILE_TYPE_XLS,FILE_TYPE_XLSX};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        }else if (format.equalsIgnoreCase("pdf")){
            intent.setType(FILE_TYPE_PDF);
            String[] mimetypes = {FILE_TYPE_PDF};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        }
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return intent;
    }

    public String getUsernameDosen(){
        return this.dosenUsername;
    }

    public FileHelper getFileHelper() {
        return fileHelper;
    }

    public AlertDialog.Builder getAlertDialog() {
        return alertDialog;
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return actionBarDrawerToggle;
    }

    public ProgressDialog getProgressDialog(){
        return this.progressDialog;
    }

    public SessionManager getSessionManager(){
        return this.sessionManager;
    }

    public ProdiDosenViewAdapter getDosenViewAdapter() {
        return dosenViewAdapter;
    }

    public ProdiMahasiswaViewAdapter getMahasiswaViewAdapter() {
        return mahasiswaViewAdapter;
    }

    public ProdiPlottingViewAdapter getPlottingViewAdapter(){ return plottingViewAdapter; }

    public InformasiViewAdapter getInformasiViewAdapter() {
        return informasiViewAdapter;
    }

    public PlottingPresenter getPlottingPresenter() {
        return plottingPresenter;
    }

    public MahasiswaPresenter getMahasiswaPresenter() {
        return mahasiswaPresenter;
    }

    public DosenPresenter getDosenPresenter() {
        return dosenPresenter;
    }

    public AuthPresenter getAuthPresenter() {
        return authPresenter;
    }

    public ProdiPresenter getProdiPresenter() {
        return prodiPresenter;
    }

    @Override
    public SidangPresenter getSidangPresenter() {
        return this.sidangPresenter;
    }

    public BimbinganPresenter getBimbinganPresenter() {
        return bimbinganPresenter;
    }

    public JadwalPresenter getJadwalPresenter() {
        return jadwalPresenter;
    }
}
