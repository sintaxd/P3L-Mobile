package kel1.com.simato_mobile.View.CustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Motor.tampil_data_motor;
import kel1.com.simato_mobile.View.CustomerService.MotorKonsumen.tampil_data_motor_konsumen;


public class cs_pengelolaan_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_pengelolaan_data);
    }

    public void data_motor(View view) {
        Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_motor.class);
        startActivity(i);
    }
    public void data_konsumen(View view) {
        Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_konsumen.class);
        startActivity(i);
    }
    public void data_motor_konsumen(View view) {
        Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_motor_konsumen.class);
        startActivity(i);
    }

}
