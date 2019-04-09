package kel1.com.simato_mobile;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tambah_data_supplier extends AppCompatActivity {

    private Button btnBatal;
    private TextInputEditText nama_supp, notelp_supp, alamat_supp, nama_sales, notelp_sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_supplier);
        btnBatal = (Button)findViewById(R.id.button_Batal);
//        nama_supp = findViewById(R.id.text_input_namaSupplier);
//        notelp_supp = findViewById(R.id.text_input_noTelpSupplier);
//        alamat_supp = findViewById(R.id.text_input_alamatSupplier);
//        nama_sales = findViewById(R.id.text_input_namaSales);
//        notelp_sales = findViewById(R.id.text_input_noTelpSales);
//      btnBatal.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//          }
//      });
    }
}
