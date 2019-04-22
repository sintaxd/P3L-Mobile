package kel1.com.simato_mobile.View.CustomerService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Motor.tampil_data_motor;

public class cs_pengelolaan_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView img_dataMotor, img_dataKonsumen;
        TextView txt_dataMotor, txt_dataKonsumen;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs_pengelolaan_data);
        img_dataMotor=(ImageView)findViewById(R.id.img_dataMotor);
        img_dataMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_motor.class);
                startActivity(i);
            }
        });
        txt_dataMotor=(TextView)findViewById(R.id.txt_dataMotor);
        txt_dataMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_motor.class);
                startActivity(i);
            }
        });
        img_dataKonsumen=(ImageView)findViewById(R.id.img_dataKonsumen);
        img_dataKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_konsumen.class);
                startActivity(i);
            }
        });
        txt_dataKonsumen=(TextView)findViewById(R.id.txt_dataMotor);
        txt_dataKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cs_pengelolaan_data.this, tampil_data_konsumen.class);
                startActivity(i);
            }
        });
    }
}
