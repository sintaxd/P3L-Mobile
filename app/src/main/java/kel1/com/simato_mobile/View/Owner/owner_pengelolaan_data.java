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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_pengelolaan_data);
    }
    public void data_supplier(View view) {
        Intent i= new Intent(owner_pengelolaan_data.this,tampil_data_supplier.class);
        startActivity(i);
    }
    public void data_sparepart(View view) {
        Intent i= new Intent(owner_pengelolaan_data.this,tampil_data_sparepart.class);
        startActivity(i);
    }
    public void data_sparepart_cabang(View view) {
        Intent i= new Intent(owner_pengelolaan_data.this,tampil_data_sparepart_cabang.class);
        startActivity(i);
    }
}

