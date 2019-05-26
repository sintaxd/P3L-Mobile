package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_DetilTransaksi;
import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiSparepart;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_DetilTransaksiSparepart;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.SessionManager.SessionManager;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tambah_pengadaan_sparepart;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tambah_data_sparepart_cabang;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_transaksi_penjualan_sparepart extends AppCompatActivity {

    Spinner spinner_sparepartcabang;

    //ini untuk load data
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_SparepartCabang> spinnerNamaSparepartCabangArray = new ArrayList<>();

    List<String> spinner_namaSparepartCabang = new ArrayList<>();
    List<String> spinner_IDSparepartCabang = new ArrayList<>();
    List<String> spinner_hargaSparepart = new ArrayList<>();

    private List<Model_DetilTransaksiSparepart> detilTransaksiSparepartList = new ArrayList<Model_DetilTransaksiSparepart>();

    Integer tempID,selectedIDCabang;
    String selectedIDSparepartCabang,  selectedHargaSparepartCabang, selectedNamaSparepartCabang, selectedIDKonsumen;
    TextView setTanggal, totalHarga_fix, namaKonsumen_fix, namaCabang_fix;
    ImageView addDetilTransaksiSparepart, imgSearch;
    Button btnSimpan, btnBatal;
    private TextInputEditText input_satuan;
    RecyclerView rview;
    private Adapter_DetilTransaksiSparepart adapter;
    private RecyclerView.LayoutManager layout;
    Double GrandTotal=0.0;
    String date_now, temp_nama;
    TextInputEditText nama_konsumen;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi_penjualan_sparepart);
        sessionManager = new SessionManager(getApplicationContext());
        selectedIDCabang = Integer.parseInt(sessionManager.getIdCabang());
        spinner_sparepartcabang = findViewById(R.id.spinner_sparepart_cabang);
        input_satuan = findViewById(R.id.text_input_satuan);
        totalHarga_fix = findViewById(R.id.textView_totalHargaFix);
        setTanggal = findViewById(R.id.textView_tanggalFix);
        imgSearch = findViewById(R.id.img_Search);
        nama_konsumen = findViewById(R.id.text_input_namaKonsumen);
        namaKonsumen_fix = findViewById(R.id.textView_namaKonsumen);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddTransaksiSparepart();
            }
        });
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent();
            }
        });
        //create a date string.
        date_now = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(new Date());
        //set it as current date.
        setTanggal.setText(date_now);
        namaCabang_fix = findViewById(R.id.textView_namaCabang_fix);
        loadNamaCabang();
        loadSpinnerNamaSparepartCabang();
        rview = findViewById(R.id.recycler_view_detil_transaksi_sparepart);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        addDetilTransaksiSparepart = findViewById(R.id.addDetilTransaksiSparepart);
        addDetilTransaksiSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetilTransaksiSparepartFunction();
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("updateTotal"));

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKonsumenFunction();
            }
        });
    }
    void loadNamaCabang(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Cabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama cabang dari database
        ApiClient_Cabang apiclientCabang = retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerNamaCabangArray = response.body().getData();
                for (int i = 0; i < spinnerNamaCabangArray.size(); i++) {
                    if(spinnerNamaCabangArray.get(i).getId_cabang()==Integer.parseInt(sessionManager.getIdCabang()))
                    {
                        namaCabang_fix.setText(spinnerNamaCabangArray.get(i).getNama_cabang());
                    }
                }
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    public void addKonsumenFunction(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Konsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        ApiClient_Konsumen apiClientKonsumen = retrofit.create(ApiClient_Konsumen.class);
        Call<ResponseBody> konsumenDAOCall = apiClientKonsumen.showByName(nama_konsumen.getText().toString());
        konsumenDAOCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    try {
                        JSONObject jsonresponse = new JSONObject(response.body().string());
                        Log.d("onResponse: ",response.body().toString());
                        String temp_nama = jsonresponse.getJSONObject("data").getString("nama_konsumen");
                        selectedIDKonsumen = jsonresponse.getJSONObject("data").getString("id_konsumen");
                        Log.d("Nama: ",temp_nama);
//                        AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
//                        alertDialog.setTitle("Tambah Transaksi Penjualan Sparepart");
//                        alertDialog.setMessage("Data Konsumen Ditemukan !");
//                        alertDialog.setIcon(R.drawable.logo_atma_auto);
//                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Write your code here to execute after dialog closed
//                            }
//                        });
//                        alertDialog.show();
                        namaKonsumen_fix.setText(temp_nama);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (IOException i)
                    {
                        i.printStackTrace();
                    }
                }
                else if(selectedIDKonsumen==null)
                {
                    Toast.makeText(getApplicationContext(),"Data konsumen tidak ditemukan, anda akan diarahkan ke Pengelolaan Konsumen", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), tampil_data_konsumen.class);
                    startActivity(intent);
                }
                Log.d("on respon : ",String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_sparepart.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String subtotal = intent.getStringExtra("sub_total");
            Double subTotalFix = Double.parseDouble(subtotal);
            GrandTotal=GrandTotal-subTotalFix;
            totalHarga_fix.setText(GrandTotal.toString());
        }
    };
    public void addDetilTransaksiSparepartFunction(){
        if(input_satuan.getText().toString().isEmpty())
        {
            Toast.makeText(tambah_transaksi_penjualan_sparepart.this,"Masukan jumlah satuan!", Toast.LENGTH_SHORT).show();
        }
        else
            {
            Double sub_total_sparepart;
            sub_total_sparepart = Double.parseDouble(input_satuan.getText().toString()) * Double.parseDouble(selectedHargaSparepartCabang);

            detilTransaksiSparepartList.add(new Model_DetilTransaksiSparepart(
                    Integer.parseInt(selectedIDSparepartCabang),
                    Integer.parseInt(selectedIDKonsumen),
                    Integer.parseInt(input_satuan.getText().toString()),
                    sub_total_sparepart,
                    Double.parseDouble(selectedHargaSparepartCabang),
                    selectedNamaSparepartCabang));

            adapter = new Adapter_DetilTransaksiSparepart(detilTransaksiSparepartList);
            rview.setAdapter(adapter);
            GrandTotal = GrandTotal + sub_total_sparepart;
            totalHarga_fix.setText(GrandTotal.toString());
            input_satuan.getText().clear();
        }
    }
    void loadSpinnerNamaSparepartCabang()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //ngeload nama sparepart cabang dari database kedalam spinner
        ApiClient_SparepartCabang apiClientSparepartCabang = retrofit.create(ApiClient_SparepartCabang.class);
        Call<LD_SparepartCabang> callSparepartCabang = apiClientSparepartCabang.showByCabang(selectedIDCabang);

        callSparepartCabang.enqueue(new Callback<LD_SparepartCabang>() {
            @Override
            public void onResponse(Call<LD_SparepartCabang> callSparepartCabang, Response<LD_SparepartCabang> response) {
                spinner_namaSparepartCabang.clear();
                spinnerNamaSparepartCabangArray =response.body().getData();
                for (int i = 0; i < spinnerNamaSparepartCabangArray.size(); i++) {
                    spinner_namaSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getNama_sparepart());
                    spinner_IDSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getId_sparepartCabang().toString());
                    spinner_hargaSparepart.add(spinnerNamaSparepartCabangArray.get(i).getHargaBeli_sparepart().toString());
                }
                ArrayAdapter<String> adapterNamaSparepartCabang = new ArrayAdapter<>(tambah_transaksi_penjualan_sparepart.this, R.layout.spinner_sparepart_layout, R.id.txtNamaSparepart, spinner_namaSparepartCabang);
                spinner_sparepartcabang.setAdapter(adapterNamaSparepartCabang);
                Toast.makeText(tambah_transaksi_penjualan_sparepart.this,"Data berhasil di load!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        spinner_sparepartcabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown tipe sparepart saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDSparepartCabang = spinner_IDSparepartCabang.get(position); //Mendapatkan id dari dropdown yang dipilih
                selectedHargaSparepartCabang = spinner_hargaSparepart.get(position);
                selectedNamaSparepartCabang = spinner_namaSparepartCabang.get(position);
                Log.d("ID SparepartCabang : ", selectedIDSparepartCabang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_transaksi_penjualan.class);
        startActivity(intent);
    }
    private void onClickAddTransaksiSparepart() {
        if (detilTransaksiSparepartList.isEmpty())
        {
            Toast.makeText(this, "Tambahkan detil sparepart!", Toast.LENGTH_SHORT).show();
        }
        else if(namaKonsumen_fix.getText().toString().equalsIgnoreCase("-"))
        {
            Toast.makeText(this, "Masukan nama konsumen!", Toast.LENGTH_SHORT).show();
        }
        else
            {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan = retrofit.create(ApiClient_TransaksiPenjualan.class);

            Log.d( "ID Cbg : ",selectedIDCabang.toString() );
            Log.d( "GrandTotal: ",GrandTotal.toString() );

            Call<ResponseBody> transaksisparepartDAOCall = apiClientTransaksiPenjualan.create("SP", selectedIDCabang, GrandTotal, null,3);
            transaksisparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response.body().string());
                            String idTransaksi = jsonresponse.getJSONObject("data").getString("id_transaksi");
                            Log.d( "ID Pengadaan: ",idTransaksi);

                            //memasukkan data dari list local ke tabel detilTransaksiSparepart
                            for(int x=0;x<detilTransaksiSparepartList.size();x++) {
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();
                                Retrofit.Builder builder=new Retrofit.
                                        Builder().baseUrl(ApiClient_DetilTransaksi.baseURL).
                                        addConverterFactory(GsonConverterFactory.create(gson));
                                Retrofit retrofit=builder.build();
                                ApiClient_DetilTransaksi apiClientDetilTransaksi = retrofit.create(ApiClient_DetilTransaksi.class);

                                Log.d( "ID Sparepart Cabang: ",detilTransaksiSparepartList.get(x).getId_sparepartCabang_fk().toString());
                                Log.d( "ID Konsumen : ",detilTransaksiSparepartList.get(x).getId_konsumen_fk().toString());
                                Log.d( "Jumlah Beli Spp : ",detilTransaksiSparepartList.get(x).getJumlahBeli_sparepart().toString());
                                Log.d( "Get Sub Total: ",detilTransaksiSparepartList.get(x).getSubTotal_sparepart().toString() );

                                Call<ResponseBody> detiltransaksisparepartDAOCall = apiClientDetilTransaksi.createDetilTransaksiSparepart(
                                        Integer.parseInt(idTransaksi),
                                        detilTransaksiSparepartList.get(x).getId_sparepartCabang_fk(),
                                        detilTransaksiSparepartList.get(x).getId_konsumen_fk(),
                                        detilTransaksiSparepartList.get(x).getJumlahBeli_sparepart(),
                                        detilTransaksiSparepartList.get(x).getSubTotal_sparepart());

                                detiltransaksisparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code() == 201) {
                                            Toast.makeText(tambah_transaksi_penjualan_sparepart.this, "Tambah Transaksi Penjualan Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("on respon : ",String.valueOf(response.code()));
                    startIntent();
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(tambah_transaksi_penjualan_sparepart.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
