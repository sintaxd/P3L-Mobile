package kel1.com.simato_mobile.View.Owner.SparepartCabang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kel1.com.simato_mobile.R;

public class tambah_data_sparepart_cabang extends AppCompatActivity {

    private Button btnBatal, btnSimpan;
//    private TextInputEditText nama_supp, notelp_supp, alamat_supp, nama_sales, notelp_sales;
//    private TextInputLayout TIL_nama_supplier,TIL_alamat_supplier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_sparepart_cabang);

//        nama_supp = findViewById(R.id.text_input_namaSupplier);
//        notelp_supp = findViewById(R.id.text_input_noTelpSupplier);
//        alamat_supp = findViewById(R.id.text_input_alamatSupplier);
//        nama_sales = findViewById(R.id.text_input_namaSales);
//        notelp_sales = findViewById(R.id.text_input_noTelpSales);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onClickRegister();
            }
        });
//        btnBatal = findViewById(R.id.button_Batal);
//        btnBatal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                nama_supp.getText().clear();
//                notelp_supp.getText().clear();
//                alamat_supp.getText().clear();
//                nama_sales.getText().clear();
//                notelp_sales.getText().clear();
//            }
//        });
    }
    //    private boolean validateNamaSupplier() {
//        String namaSupplierInput = TIL_nama_supplier.getEditText().getText().toString().trim();
//
//        if (namaSupplierInput.isEmpty()) {
//            TIL_nama_supplier.setError("Field tidak boleh kosong!");
//            return false;
//        } else if (namaSupplierInput.length() > 30) {
//            TIL_nama_supplier.setError("Nama Supplier maksimal 30 karakter!");
//            return false;
//        } else {
//            TIL_nama_supplier.setError(null);
//            return true;
//        }
//    }
//    private boolean validateAlamatSupplier() {
//        String alamatSupplierInput = TIL_alamat_supplier.getEditText().getText().toString().trim();
//
//        if (alamatSupplierInput.isEmpty()) {
//            TIL_alamat_supplier.setError("Field tidak boleh kosong!");
//            return false;
//        } else if (alamatSupplierInput.length() > 150) {
//            TIL_alamat_supplier.setError("Alamat Supplier maksimal 150 karakter!");
//            return false;
//        } else {
//            TIL_nama_supplier.setError(null);
//            return true;
//        }
//    }
//    public void confirmInput(View v) {
//        if (!validateNamaSupplier() | !validateAlamatSupplier()) {
//            return;
//        }
//        else
//        {
//            btnSimpan = findViewById(R.id.button_Simpan);
//            btnSimpan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onClickRegister();
//                }
//            });
//        }
//    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_sparepart_cabang.class);
        startActivity(intent);
    }
//    private void onClickRegister() {
//        if (nama_supp.getText().toString().isEmpty() ||
//                notelp_supp.getText().toString().isEmpty() ||
//                alamat_supp.getText().toString().isEmpty() ||
//                nama_sales.getText().toString().isEmpty() ||
//                notelp_sales.getText().toString().isEmpty())
//        {
//            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
//        } else {
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            Retrofit.Builder builder = new Retrofit
//                    .Builder()
//                    .baseUrl("http://simato.jasonfw.com/")  //http://10.53.0.204:8000/
//                    .addConverterFactory(GsonConverterFactory.create(gson));
//            Retrofit retrofit = builder.build();
//            ApiClient_Supplier apiClientSupplier = retrofit.create(ApiClient_Supplier.class);
//            Call<SupplierModel> supplierDAOCall = apiClientSupplier.create(nama_supp.getText().toString(),
//                    notelp_supp.getText().toString(),
//                    alamat_supp.getText().toString(),
//                    nama_sales.getText().toString(),
//                    notelp_sales.getText().toString());
//            supplierDAOCall.enqueue(new Callback<SupplierModel>() {
//                @Override
//                public void onResponse(Call<SupplierModel> call, Response<SupplierModel> response) {
//                    Toast.makeText(tambah_data_supplier.this, "Success", Toast.LENGTH_SHORT).show();
//                    startIntent();
//                }
//                @Override
//                public void onFailure(Call<SupplierModel> call, Throwable t) {
//                    Toast.makeText(tambah_data_supplier.this, "Network connection failed ", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }

}
