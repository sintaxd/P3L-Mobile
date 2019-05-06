package kel1.com.simato_mobile.View.CustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Motor.tampil_data_motor;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tambah_transaksi_penjualan_service;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tambah_transaksi_penjualan_service_sparepart;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tambah_transaksi_penjualan_sparepart;

public class cs_pengelolaan_transaksi_penjualan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_pengelolaan_transaksi_penjualan);
    }
    public void transaksi_sparepart(View view) {
        Intent i = new Intent(cs_pengelolaan_transaksi_penjualan.this, tambah_transaksi_penjualan_sparepart.class);
        startActivity(i);
    }
    public void transaksi_service(View view) {
        Intent i = new Intent(cs_pengelolaan_transaksi_penjualan.this, tambah_transaksi_penjualan_service.class);
        startActivity(i);
    }
    public void transaksi_service_sparepart(View view) {
        Intent i = new Intent(cs_pengelolaan_transaksi_penjualan.this, tambah_transaksi_penjualan_service_sparepart.class);
        startActivity(i);
    }

}
