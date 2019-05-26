package kel1.com.simato_mobile.View.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.HistorySparepart.tampil_history_sparepart_masuk;

public class owner_history_sparepart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_history_sparepart);
    }
    public void sparepart_masuk(View view) {
        Intent i= new Intent(owner_history_sparepart.this, tampil_history_sparepart_masuk.class);
        startActivity(i);
    }
    public void sparepart_keluar(View view) {
//        Intent i= new Intent(owner_history_sparepart.this,owner_pengelolaan_data.class);
//        startActivity(i);
    }
}
