package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kel1.com.simato_mobile.R;

public class tambah_transaksi_penjualan_service extends AppCompatActivity {

    TextView setTanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi_penjualan_service);

        setTanggal = findViewById(R.id.textView_tanggalFix);
        //create a date string.
        String date_now = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(new Date());
        //set it as current date.
        setTanggal.setText(date_now);
    }
}
