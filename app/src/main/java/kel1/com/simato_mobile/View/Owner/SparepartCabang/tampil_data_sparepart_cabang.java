package kel1.com.simato_mobile.View.Owner.SparepartCabang;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;

public class tampil_data_sparepart_cabang extends AppCompatActivity {

    FloatingActionButton btn_tambahSparepartCabang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_sparepart_cabang);
        btn_tambahSparepartCabang = (FloatingActionButton) findViewById(R.id.btn_tambahDataSparepartCabang);
        btn_tambahSparepartCabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_sparepart_cabang.this, tambah_data_sparepart_cabang.class);
                startActivity(i);
            }
        });
    }
}
