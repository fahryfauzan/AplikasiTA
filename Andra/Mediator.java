package org.d3ifcool.finpro.core.mediators;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.adapters.InformasiViewAdapter;
import org.d3ifcool.finpro.core.helpers.Message;
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
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.JadwalPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.PlottingPresenter;
import org.d3ifcool.finpro.core.presenters.ProdiPresenter;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public interface Mediator {

    void message(Message message);
    void selectIntent(Message message);
    void setTitleContextWithHomeAsUp(String title);

    void setTextView(TextView textView);
    void setRecyclerView(RecyclerView recyclerView);
    void setRefreshLayout(SwipeRefreshLayout refreshLayout);
    void setRelativeLayout(RelativeLayout relativeLayout);
    void setBottomNavigationView(BottomNavigationView bottomNavigationView);
    void setActionBarDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar);
    void setCardView(CardView cardView);
    void setFloatingActionButton(FloatingActionButton floatingActionButton);
    void setCompactCalendarView(CompactCalendarView compactCalendarView);
    void setCircleImageView(CircleImageView circleImageView);
    void setLinearLayout(LinearLayout linearLayout);
    void setSpinner(Spinner spinner);
    void setButton(Button button);

    void setProdiPresenter(ProdiContract.ViewModel viewModel);
    void setPlottingPresenter(PlottingContract.ViewModel viewModel);
    void setMahasiswaPresenter(MahasiswaContract.ViewModel viewModel);
    void setBimbinganPresenter(BimbinganContract.ViewModel viewModel);
    void setJadwalPresenter(JadwalContract.ViewModel viewModel);
    void setAuthPresenter(AuthContract.ViewModel viewModel);
    void setDosenPresenter(DosenContract.ViewModel viewModel);
    void setInformasiPresenter(InformasiContract.ViewModel viewModel);
    void setSidangPresenter(SidangContract.ViewModel viewModel);

    BimbinganPresenter getBimbinganPresenter();
    JadwalPresenter getJadwalPresenter();
    MahasiswaPresenter getMahasiswaPresenter();
    DosenPresenter getDosenPresenter();
    PlottingPresenter getPlottingPresenter();
    ProdiPresenter getProdiPresenter();
    SidangPresenter getSidangPresenter();

    String getSessionUsername();
    String getSessionToken();
    String getSessionPengguna();
    String getUsernameDosen();

    Intent findFileIntent(String format);
    boolean getPermissionFile();
    SessionManager getSessionManager();
    ActionBarDrawerToggle getActionBarDrawerToggle();
    AlertDialog.Builder getAlertDialog();
    InformasiViewAdapter getInformasiViewAdapter();
}
