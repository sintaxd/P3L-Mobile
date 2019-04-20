package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tampil_data_motor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton btn_tambahMotor;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_motor);

        btn_tambahMotor = (FloatingActionButton) findViewById(R.id.btn_tambahDataMotor);
        btn_tambahMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_motor.this, tambah_data_motor.class);
                startActivity(i);
            }
        });
    }
}
