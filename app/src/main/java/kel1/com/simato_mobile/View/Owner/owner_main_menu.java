package kel1.com.simato_mobile.View.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.MainActivity;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.SessionManager.SessionManager;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tampil_sparepart_stok_kurang;

public class owner_main_menu extends AppCompatActivity {

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);

        session = new SessionManager(getApplicationContext());
    }

    public void pengelolaan_data(View view) {
        Intent i= new Intent(owner_main_menu.this,owner_pengelolaan_data.class);
        startActivity(i);
    }
    public void history_sparepart(View view) {
        Intent i= new Intent(owner_main_menu.this, owner_history_sparepart.class);
        startActivity(i);
    }
    public void pengadaan_sparepart(View view) {
        Intent i= new Intent(owner_main_menu.this, tampil_sparepart_stok_kurang.class);
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
