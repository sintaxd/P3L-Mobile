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
//        nama_supp = findViewById(R.id.editText_kodeSparepart);
//        notelp_supp = findViewById(R.id.editText_merkSparepart);
//        alamat_supp = findViewById(R.id.editText_tipeSparepart);
//        nama_sales = findViewById(R.id.editText_namaSparepart);
//        notelp_sales = findViewById(R.id.editText_noTelpSales);
      btnBatal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              nama_supp.getText().clear();
              notelp_supp.getText().clear();
              alamat_supp.getText().clear();
              nama_sales.getText().clear();
              notelp_sales.getText().clear();
          }
      });
    }
}
