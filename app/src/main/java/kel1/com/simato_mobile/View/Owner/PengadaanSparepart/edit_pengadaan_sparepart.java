package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_DetilPengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiSparepart;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_DetilPengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.HistorySparepart.tampil_history_sparepart_masuk;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_pengadaan_sparepart extends AppCompatActivity {

    Spinner spinner_supplier, spinner_sparepartcabang,spinner_cabang;
    String editable;
    LinearLayout addDetilSparepart;
    //ini untuk load data
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_Supplier> spinnerSupplierArray =  new ArrayList<>();
    List<Model_SparepartCabang> spinnerNamaSparepartCabangArray = new ArrayList<>();

    List<String> spinner_IDSparepartCabang = new ArrayList<>();
    List<String> spinner_namaSparepartCabang = new ArrayList<>();
    List<String> spinner_hargaSparepart = new ArrayList<>();

    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();

    List<String> spinner_IDSupplier = new ArrayList<>();
    List<String> spinner_namaSupplier = new ArrayList<>();

    private List<Model_DetilPengadaanSparepart> detilPengadaanSparepartList = new ArrayList<Model_DetilPengadaanSparepart>();
    public Adapter_DetilPengadaanSparepart adapterDetilPengadaan;
    Integer id_cabang_fk, id_sparepartCabang_fk, id_supplier;
    TextView setTanggal, totalHarga_fix, setJudul;
    ImageView addDetilPengadaan;
    Button btnSimpan, btnBatal, btnDelete;
    private Intent i;
    private TextInputEditText input_satuanPengadaan;
    RecyclerView rview;
    private Adapter_DetilPengadaanSparepart adapter;
    private RecyclerView.LayoutManager layout;
    Double GrandTotal=0.0, tempTotal;
    String temp_date, status_pengadaan, status_cetak;
    Integer selectedIDCabang,idPengadaan;
    String selectedIDSparepartCabang, selectedHargaSparepartCabang, selectedNamaSparepartCabang, selectedIDSupplier;
    Adapter_DetilPengadaanSparepart.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengadaan_sparepart);

        spinner_supplier = findViewById(R.id.spinner_supplier);
        spinner_sparepartcabang = findViewById(R.id.spinner_sparepartcabang);
        spinner_cabang = findViewById(R.id.spinner_cabang);
        input_satuanPengadaan = findViewById(R.id.text_input_satuanSparepart);
        setJudul = findViewById(R.id.textView_Judul);
        addDetilSparepart = findViewById(R.id.addDetilSparepart);

        i = getIntent();
        totalHarga_fix = findViewById(R.id.textView_totalHargaFix);
        tempTotal = i.getDoubleExtra("total_harga",0.0);
        totalHarga_fix.setText(tempTotal.toString());
        Log.d( "onCreate: ",i.getStringExtra("tgl_pengadaan"));
        idPengadaan=i.getIntExtra("id_pengadaan",-1);
        Log.d( "ID Pengadaan: ",idPengadaan.toString());
        status_cetak=i.getStringExtra("status_cetak");
        status_pengadaan=i.getStringExtra("status_pengadaan");
        editable = i.getStringExtra("editable");

        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        final String temp_date=i.getStringExtra("tgl_pengadaan");
        Date date = null;
        try
        {
            date = inputFormat.parse(temp_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String outputDateStr = outputFormat.format(date);
        setTanggal = findViewById(R.id.textView_tanggalFix);
        setTanggal.setText(outputDateStr);

        rview = findViewById(R.id.recycler_view_detil_pengadaan);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);

        addDetilPengadaan = findViewById(R.id.addDetilPengadaan);
        addDetilPengadaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetilPengadaanFunction();
            }
        });

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPengadaanSparepart();
            }
        });

        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent();
            }
        });

        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePengadaanSparepart();
            }
        });

        loadSpinnerNamaSupplier();
        spinner_supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama supplier saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDSupplier = spinner_IDSupplier.get(position); //Mendapatkan id dari dropdown yang dipilih
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        loadSpinnerNamaCabang();
        spinner_cabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDCabang = Integer.parseInt(spinner_IDCabang.get(position)); //Mendapatkan id dari dropdown yang dipilih
                loadSpinnerNamaSparepartCabang();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedIDCabang=1;
                loadSpinnerNamaSparepartCabang();
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("updateTotal"));

        load_recycler_view_pengadaan();
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
    public void addDetilPengadaanFunction(){
        Double sub_total_sparepart;
        sub_total_sparepart=Double.parseDouble(input_satuanPengadaan.getText().toString())*Double.parseDouble(selectedHargaSparepartCabang);

        detilPengadaanSparepartList.add(new Model_DetilPengadaanSparepart(Integer.parseInt(input_satuanPengadaan.getText().toString()),
                sub_total_sparepart,
                Double.parseDouble(selectedHargaSparepartCabang),
                selectedNamaSparepartCabang,
                Integer.parseInt(selectedIDSparepartCabang)));

        adapter = new Adapter_DetilPengadaanSparepart(detilPengadaanSparepartList, 1);
        rview.setAdapter(adapter);
        GrandTotal=GrandTotal+sub_total_sparepart;
        totalHarga_fix.setText(GrandTotal.toString());
        input_satuanPengadaan.getText().clear();
    }
    void loadSpinnerNamaSupplier(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Supplier.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        //ngeload nama supplier dari database kedalam spinner
        ApiClient_Supplier apiclientSupplier =retrofit.create(ApiClient_Supplier.class);
        Call<LD_Supplier> callSupplier = apiclientSupplier.show();
        callSupplier.enqueue(new Callback<LD_Supplier>() {
            @Override
            public void onResponse(Call<LD_Supplier> callSupplier, Response<LD_Supplier> response) {

                spinnerSupplierArray=response.body().getData();
                for(int i=0; i<spinnerSupplierArray.size();i++){
                    spinner_namaSupplier.add(spinnerSupplierArray.get(i).getNama_supplier());
                    spinner_IDSupplier.add(spinnerSupplierArray.get(i).getId_supplier().toString());
                }
                ArrayAdapter<String> adapterNamaSupplier = new ArrayAdapter<>(edit_pengadaan_sparepart.this, R.layout.spinner_supplier_layout,R.id.txtNamaSupplier, spinner_namaSupplier);
                spinner_supplier.setAdapter(adapterNamaSupplier);
            }
            @Override
            public void onFailure(Call<LD_Supplier> call, Throwable t) {
                Toast.makeText(edit_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void loadSpinnerNamaCabang(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Cabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiclientCabang = retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerNamaCabangArray = response.body().getData();
                for (int i = 0; i < spinnerNamaCabangArray.size(); i++) {
                    spinner_namaCabang.add(spinnerNamaCabangArray.get(i).getNama_cabang());
                    spinner_IDCabang.add(spinnerNamaCabangArray.get(i).getId_cabang().toString());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(edit_pengadaan_sparepart.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(edit_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
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
                ArrayAdapter<String> adapterNamaSparepartCabang = new ArrayAdapter<>(edit_pengadaan_sparepart.this, R.layout.spinner_sparepart_layout, R.id.txtNamaSparepart, spinner_namaSparepartCabang);
                spinner_sparepartcabang.setAdapter(adapterNamaSparepartCabang);
                Toast.makeText(edit_pengadaan_sparepart.this, response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(edit_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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

   private void load_recycler_view_pengadaan(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_PengadaanSparepart.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ApiClient_DetilPengadaanSparepart apiClientPengadaanSparepart =retrofit.create(ApiClient_DetilPengadaanSparepart.class);

        Call<LD_DetilPengadaanSparepart> pengadaansparepartModelCall = apiClientPengadaanSparepart.showByIdPengadaan(idPengadaan);

        pengadaansparepartModelCall.enqueue(new Callback<LD_DetilPengadaanSparepart>() {
            @Override
            public void onResponse (Call<LD_DetilPengadaanSparepart> call, Response<LD_DetilPengadaanSparepart> response) {
                Log.d( "onResponse: ", response.body().getData().toString());

                detilPengadaanSparepartList = response.body().getData();
                Log.i(tampil_pengadaan_sparepart.class.getSimpleName(), response.body().toString());
                if(editable.equalsIgnoreCase("no"))
                {
                    //PERUBAHAN SINTA TERBARU
                    adapter = new Adapter_DetilPengadaanSparepart(detilPengadaanSparepartList,0);
                    setJudul.setText("Detil Pengadaan Sparepart");
                    spinner_cabang.setEnabled(false);
                    spinner_supplier.setEnabled(false);
                    addDetilSparepart.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.GONE);
                    btnSimpan.setVisibility(View.GONE);
                    btnBatal.setText("KEMBALI");
                    btnBatal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startIntentHistori();
                        }
                    });
                }
                else if(editable.equalsIgnoreCase("yes"))
                {
                    adapter = new Adapter_DetilPengadaanSparepart(detilPengadaanSparepartList,1);
                }
                rview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                Toast.makeText(edit_pengadaan_sparepart.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_DetilPengadaanSparepart> call, Throwable t) {
                Toast.makeText(edit_pengadaan_sparepart.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("kegagalan: ",t.getMessage());
            }
        });
    }
    private void EditPengadaanSparepart(){

        if(status_pengadaan.equalsIgnoreCase("Sudah Selesai") || status_cetak.equalsIgnoreCase("Sudah Cetak"))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Edit Pengadaan Sparepart");

            // Setting Dialog Message
            alertDialog.setMessage("Pengadaan tidak bisa diedit!");

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
        else
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_PengadaanSparepart.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiClient_PengadaanSparepart apiClientPengadaanSparepart = retrofit.create(ApiClient_PengadaanSparepart.class);

            Log.d( "ID Supp: ",selectedIDSupplier );
            Log.d( "ID Cbg : ",selectedIDCabang.toString() );
            Log.d( "GrandTotal: ",GrandTotal.toString() );

            Call<ResponseBody> pengadaansparepartDAOCall = apiClientPengadaanSparepart.update_mobile(Integer.parseInt(selectedIDSupplier),
                    selectedIDCabang,
                    GrandTotal,
                    idPengadaan);
            pengadaansparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response.body().string());
                            String idPengadaan = jsonresponse.getJSONObject("data").getString("id_pengadaan");
                            Log.d( "ID Pengadaan: ",idPengadaan);

                            //memasukkan data dari list local ke tabel detilPengadaan
                            for(int x=0;x<detilPengadaanSparepartList.size();x++) {
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();
                                Retrofit.Builder builder=new Retrofit.
                                        Builder().baseUrl(ApiClient_DetilPengadaanSparepart.baseURL).
                                        addConverterFactory(GsonConverterFactory.create(gson));
                                Retrofit retrofit=builder.build();
                                ApiClient_DetilPengadaanSparepart apiClientDetilPengadaanSparepart = retrofit.create(ApiClient_DetilPengadaanSparepart.class);

                                Log.d( "ID Spp Cbg: ",detilPengadaanSparepartList.get(x).getId_sparepartCabang_fk().toString());
                                Log.d( "Satuan Pengadaan : ",detilPengadaanSparepartList.get(x).getSatuan_pengadaan().toString() );
                                Log.d( "Get Sub Total: ",detilPengadaanSparepartList.get(x).getSub_total_sparepart().toString() );

                                Call<ResponseBody> detilpengadaansparepartDAOCall = apiClientDetilPengadaanSparepart.createDetilPengadaan(
                                        Integer.parseInt(idPengadaan),
                                        detilPengadaanSparepartList.get(x).getId_sparepartCabang_fk(),
                                        detilPengadaanSparepartList.get(x).getSatuan_pengadaan(),
                                        detilPengadaanSparepartList.get(x).getSub_total_sparepart());

                                detilpengadaansparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code() == 201) {
                                            Toast.makeText(edit_pengadaan_sparepart.this, "Edit Pengadaan Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

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
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(edit_pengadaan_sparepart.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void DeletePengadaanSparepart(){
        if(status_pengadaan.equalsIgnoreCase("Sudah Selesai") || status_cetak.equalsIgnoreCase("Sudah Cetak"))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Delete Pengadaan Sparepart");

            // Setting Dialog Message
            alertDialog.setMessage("Pengadaan tidak bisa dihapus!");

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
                    .baseUrl(ApiClient_PengadaanSparepart.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            ApiClient_PengadaanSparepart apiClientPengadaanSparepart = retrofit.create(ApiClient_PengadaanSparepart.class);
            Call<ResponseBody> pengadaansparepartDAOCall = apiClientPengadaanSparepart.delete(idPengadaan);
            pengadaansparepartDAOCall.enqueue(new Callback<ResponseBody>() {
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
        Intent intent = new Intent(getApplicationContext(), tampil_pengadaan_sparepart.class);
        startActivity(intent);
    }

    private void startIntentHistori() {
        Intent intent = new Intent(getApplicationContext(), tampil_history_sparepart_masuk.class);
        startActivity(intent);
    }

}
