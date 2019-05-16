package kel1.com.simato_mobile.View.Konsumen;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import kel1.com.simato_mobile.R;

public class konsumen_tampil_riwayat_transaksi extends AppCompatActivity {

    TextInputEditText plat_motor;
    ImageView imgSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumen_tampil_riwayat_transaksi);

        plat_motor = findViewById(R.id.text_input_platMotor);
        imgSearch = findViewById(R.id.img_Search);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(konsumen_tampil_riwayat_transaksi.this, "miaaw", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
