package kel1.com.simato_mobile;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tambah_data_konsumen extends AppCompatActivity {

    private Button btnBatal, btnSimpan;
    private TextInputEditText nama_konsumen,noTelp_konsumen, alamat_konsumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_konsumen);

        nama_konsumen = findViewById(R.id.text_input_namaKonsumen);
        noTelp_konsumen = findViewById(R.id.text_input_noTelpKonsumen);
        alamat_konsumen = findViewById(R.id.text_input_alamatKonsumen);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_konsumen.getText().clear();
                noTelp_konsumen.getText().clear();
                alamat_konsumen.getText().clear();
            }
        });
    }
}
