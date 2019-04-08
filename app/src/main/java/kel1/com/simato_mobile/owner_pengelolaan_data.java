package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class owner_pengelolaan_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView img_dataSparepart, img_dataSupplier;
        TextView txt_dataSparepart, txt_dataSupplier;

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
    }
}
