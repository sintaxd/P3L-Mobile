package kel1.com.simato_mobile.View.CustomerService.Konsumen;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;

public class tampil_data_konsumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton btn_tambahKonsumen;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_konsumen);

        btn_tambahKonsumen = (FloatingActionButton) findViewById(R.id.btn_tambahDataKonsumen);
        btn_tambahKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_konsumen.this, tambah_data_konsumen.class);
                startActivity(i);
            }
        });
    }
}
