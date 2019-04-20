package kel1.com.simato_mobile;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tambah_data_motor extends AppCompatActivity {
    private Button btnBatal, btnSimpan;
    private TextInputEditText merk_motor,tipe_motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_motor);

        merk_motor = findViewById(R.id.text_input_merkMotor);
        tipe_motor = findViewById(R.id.text_input_tipeMotor);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                merk_motor.getText().clear();
                tipe_motor.getText().clear();
            }
        });
    }
}
