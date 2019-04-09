package kel1.com.simato_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_data_sparepart extends AppCompatActivity {

    private Button btnBatal;
    //private EditText nama_sparepart, merk_sparepart, kode_sparepart, tipe_sparepart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_sparepart);

        btnBatal = (Button)findViewById(R.id.button_Batal);
//        nama_sparepart = (EditText)findViewById(R.id.editText_namaSparepart);
//        merk_sparepart = (EditText)findViewById(R.id.editText_merkSparepart);
//        tipe_sparepart = (EditText)findViewById(R.id.editText_tipeSparepart);
//        kode_sparepart = (EditText)findViewById(R.id.editText_kodeSparepart);
//
//        btnBatal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                nama_sparepart.getText().clear();
//                merk_sparepart.getText().clear();
//                tipe_sparepart.getText().clear();
//                kode_sparepart.getText().clear();
//            }
//        });
    }
}
