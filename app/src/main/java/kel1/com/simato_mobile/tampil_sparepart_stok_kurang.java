package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tampil_pengadaan_sparepart;

public class tampil_sparepart_stok_kurang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_sparepart_stok_kurang);
    }
    public void kelola_pengadaan_sparepart(View view) {
        Intent i= new Intent(tampil_sparepart_stok_kurang.this, tampil_pengadaan_sparepart.class);
        startActivity(i);
    }

}
