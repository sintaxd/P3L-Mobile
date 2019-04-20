package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class cs_main_menu extends AppCompatActivity {

    ImageView pengelolaanData, pengelolaanTransaksiPenjualan;
    TextView pengelolaanData1, pengelolaanTransaksiPenjualan1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_main_menu);

        pengelolaanData=(ImageView)findViewById(R.id.img_pengelolaanData);
        pengelolaanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_main_menu.this, cs_pengelolaan_data.class);
                startActivity(i);
            }
        });
        pengelolaanData1=(TextView)findViewById(R.id.textView_pengelolaanData);
        pengelolaanData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_main_menu.this, cs_pengelolaan_data.class);
                startActivity(i);
            }
        });

        pengelolaanTransaksiPenjualan=(ImageView)findViewById(R.id.img_pengelolaanTransaksiPenjualan);
        pengelolaanTransaksiPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_main_menu.this, cs_pengelolaan_transaksi_penjualan.class);
                startActivity(i);
            }
        });
        pengelolaanTransaksiPenjualan1=(TextView)findViewById(R.id.textView_pengelolaanTransaksiPenjualan);
        pengelolaanTransaksiPenjualan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_main_menu.this, cs_pengelolaan_transaksi_penjualan.class);
                startActivity(i);
            }
        });
    }
}
