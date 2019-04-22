package kel1.com.simato_mobile.View.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Sparepart.tampil_data_sparepart;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tampil_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;

public class owner_pengelolaan_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView img_dataSparepart, img_dataSupplier, img_dataSparepartCabang;
        TextView txt_dataSparepart, txt_dataSupplier, txt_dataSparepartCabang;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_pengelolaan_data);
//        getSupportActionBar().setTitle("Menu Utama");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_dataSparepart=(ImageView)findViewById(R.id.img_dataSparepart);
        img_dataSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_sparepart.class);
                startActivity(i);
            }
        });
        txt_dataSparepart=(TextView)findViewById(R.id.txt_dataSparepart);
        txt_dataSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_sparepart.class);
                startActivity(i);
            }
        });

        img_dataSupplier=(ImageView)findViewById(R.id.img_dataSupplier);
        img_dataSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_supplier.class);
                startActivity(i);
            }
        });
        txt_dataSupplier=(TextView)findViewById(R.id.txt_dataSupplier);
        txt_dataSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_supplier.class);
                startActivity(i);
            }
        });

        img_dataSparepartCabang=(ImageView)findViewById(R.id.img_dataSparepartCabang);
        img_dataSparepartCabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_sparepart_cabang.class);
                startActivity(i);
            }
        });
        txt_dataSparepartCabang=(TextView)findViewById(R.id.txt_dataSparepartCabang);
        txt_dataSparepartCabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_pengelolaan_data.this, tampil_data_sparepart_cabang.class);
                startActivity(i);
            }
        });
    }
}
