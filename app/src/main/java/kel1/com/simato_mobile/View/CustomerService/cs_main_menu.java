package kel1.com.simato_mobile.View.CustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kel1.com.simato_mobile.MainActivity;
import kel1.com.simato_mobile.R;

public class cs_main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_main_menu);
    }
    public void pengelolaan_data(View view) {
        Intent i= new Intent(cs_main_menu.this, cs_pengelolaan_data.class);
        startActivity(i);
    }
    public void transaksi_penjualan(View view) {
        Intent i= new Intent(cs_main_menu.this, cs_pengelolaan_transaksi_penjualan.class);
        startActivity(i);
    }
    public void history_transaksi_penjualan(View view) {
//        Intent i= new Intent(cs_main_menu.this, cs_pengelolaan_transaksi_penjualan.class);
//        startActivity(i);
    }
    public void logout(View view) {
        Intent i = new Intent(cs_main_menu.this, MainActivity.class);
        startActivity(i);
    }

}
