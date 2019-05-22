package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Motor.edit_data_motor;
import kel1.com.simato_mobile.View.CustomerService.Motor.tampil_data_motor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Path;

public class edit_transaksi_penjualan_sparepart extends AppCompatActivity {

    private Button btnBatal, btnSimpan, btnDelete;
    private TextView namacabang, kodetransaksi, totaltransaksi, tanggaltransaksi, statustransaksi;
    private TextInputEditText diskon;
    private Integer idTransaksi;
    private Intent i;
    private Double tempdiskon,temptotal,totalfix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaksi_penjualan_sparepart);

        namacabang= findViewById(R.id.textView_namacabangfix);
        kodetransaksi= findViewById(R.id.textView_kodetransaksifix);
        totaltransaksi= findViewById(R.id.textView_totaltransaksi);
        statustransaksi= findViewById(R.id.textView_statustransaksi);
        tanggaltransaksi = findViewById(R.id.textView_tanggaltransaksifix);
        diskon = findViewById(R.id.text_input_diskon);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTransaksiPenjualan();
                startIntent();
            }
        });
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntent();
            }
        });

        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteTransaksiPenjualan();
                startIntent();
            }
        });
        i = getIntent();
        namacabang.setText(i.getStringExtra("nama_cabang"));
        kodetransaksi.setText(i.getStringExtra("kode_transaksi"));
        tempdiskon=i.getDoubleExtra("diskon",-1);
        diskon.setText(tempdiskon.toString());
        temptotal=i.getDoubleExtra("total_transaksi",-1);
        idTransaksi=i.getIntExtra("id_transaksi",-1);
        totaltransaksi.setText(temptotal.toString());
        statustransaksi.setText(i.getStringExtra("status_transaksi"));
        tanggaltransaksi.setText(i.getStringExtra("tanggal_transaksi"));
    }
    private void UpdateTransaksiPenjualan()
    {
        if(diskon.getText().toString().isEmpty() || statustransaksi.getText().toString().isEmpty() )
        {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            totalfix=temptotal-Double.parseDouble(diskon.getText().toString());
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();
            tempdiskon=Double.parseDouble(diskon.getText().toString());
            ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan =retrofit.create(ApiClient_TransaksiPenjualan.class);
            Call<ResponseBody> transaksipenjualanDAOCall= apiClientTransaksiPenjualan.update_sinta(tempdiskon,
                    totalfix,idTransaksi);
            transaksipenjualanDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    private void DeleteTransaksiPenjualan(){
        if(statustransaksi.getText().toString().equalsIgnoreCase("Sudah Lunas"))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Delete Transaksi Penjualan");

            // Setting Dialog Message
            alertDialog.setMessage("Transaksi tidak bisa dihapus!");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.logo_atma_auto);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }
        else {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan = retrofit.create(ApiClient_TransaksiPenjualan.class);
            Call<ResponseBody> transaksipenjualanDAOCall = apiClientTransaksiPenjualan.delete(idTransaksi);
            transaksipenjualanDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Success Delete", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_transaksi_penjualan.class);
        startActivity(intent);
    }

}
