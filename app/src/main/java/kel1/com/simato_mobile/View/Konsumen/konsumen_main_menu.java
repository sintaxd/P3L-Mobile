package kel1.com.simato_mobile.View.Konsumen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;

public class konsumen_main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumen_main_menu);
    }
    public void tampil_informasi_bengkel(View view) {
        Intent i= new Intent(konsumen_main_menu.this, tampil_informasi_bengkel.class);
        startActivity(i);
    }
    public void tampil_sparepart_bengkel(View view) {
        Intent i = new Intent(konsumen_main_menu.this, tampil_sparepart_bengkel.class);
        startActivity(i);
    }
}
