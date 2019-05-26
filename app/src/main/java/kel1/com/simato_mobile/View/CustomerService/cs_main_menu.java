package kel1.com.simato_mobile.View.CustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kel1.com.simato_mobile.MainActivity;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.SessionManager.SessionManager;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tampil_transaksi_penjualan;

public class cs_main_menu extends AppCompatActivity {

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_main_menu);

        session = new SessionManager(getApplicationContext());

    }
    public void pengelolaan_data(View view) {
        Intent i= new Intent(cs_main_menu.this, cs_pengelolaan_data.class);
        startActivity(i);
    }
    public void transaksi_penjualan(View view) {
        Intent i= new Intent(cs_main_menu.this, cs_pengelolaan_transaksi_penjualan.class);
        startActivity(i);
    }
    public void history_transaksi_penjualan(View view) {
        Intent i= new Intent(cs_main_menu.this, tampil_transaksi_penjualan.class);
        startActivity(i);
    }
    public void logout(View view) {
        session.logoutUser();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
