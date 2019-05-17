package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Adapter.Adapter_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;

public class edit_pengadaan_sparepart extends AppCompatActivity {

    Spinner spinner_supplier, spinner_sparepartcabang,spinner_cabang;

    //ini untuk load data
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_Supplier> spinnerSupplierArray =  new ArrayList<>();
    List<Model_SparepartCabang> spinnerNamaSparepartCabangArray = new ArrayList<>();
    private List<Model_DetilPengadaanSparepart> detilPengadaanSparepartList = new ArrayList<Model_DetilPengadaanSparepart>();

    Integer id_cabang_fk, id_sparepartCabang_fk, id_supplier;
    TextView setTanggal, totalHarga_fix;
    ImageView addDetilPengadaan;
    Button btnSimpan, btnBatal;
    private Intent i;
    private TextInputEditText input_satuanPengadaan;
    RecyclerView rview;
    private Adapter_DetilPengadaanSparepart adapter;
    private RecyclerView.LayoutManager layout;
    Double GrandTotal=0.0, tempTotal;
    String date_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengadaan_sparepart);

        i = getIntent();
        totalHarga_fix = findViewById(R.id.textView_totalHargaFix);
        tempTotal = i.getDoubleExtra("total_harga",0.0);
        totalHarga_fix.setText(tempTotal.toString());
        //setTanggal.setText(i.getStringExtra("tgl_pengadaan"));
    }


}
