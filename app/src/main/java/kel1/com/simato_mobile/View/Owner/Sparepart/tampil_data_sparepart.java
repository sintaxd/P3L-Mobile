package kel1.com.simato_mobile.View.Owner.Sparepart;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;

public class tampil_data_sparepart extends AppCompatActivity {

    FloatingActionButton btn_tambahSparepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_sparepart);

        btn_tambahSparepart=(FloatingActionButton) findViewById(R.id.btn_tambahDataSparepart);
        btn_tambahSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_sparepart.this, tambah_data_sparepart.class);
                startActivity(i);
            }
        });
    }
}
