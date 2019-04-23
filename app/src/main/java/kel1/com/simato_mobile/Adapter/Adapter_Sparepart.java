package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.recycle_adapter_supplier;

public class Adapter_Sparepart extends RecyclerView.Adapter<Adapter_Sparepart.MyViewHolder> {
    private Context context;
    private List<Model_Sparepart> result;

    public Adapter_Sparepart(Context context, List<Model_Sparepart> result) {
        this.context = context;
        this.result = result;
    }


    @NonNull
    @Override
    public Adapter_Sparepart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Sparepart.MyViewHolder myViewHolder, int i) {
        final Model_Sparepart spare = result.get(i);
        myViewHolder.nama_sparepart.setText("Nama Sparepart : "+ spare.getNama_sparepart());
        myViewHolder.kode_sparepart.setText("Kode Sparepart : "+ spare.getKode_sparepart());
        myViewHolder.merk_sparepart.setText("Merk Sparepart : "+ spare.getMerk_sparepart());
        myViewHolder.tipe_sparepart.setText("Tipe Sparepart : "+ spare.getTipe_sparepart());

        myViewHolder.nama_sparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, recycle_adapter_supplier.class);
                i.putExtra("Nama Sparepart : ", spare.getNama_sparepart());
                i.putExtra("Kode Sparepart : ", spare.getKode_sparepart());
                i.putExtra("Merk Sparepart : ", spare.getMerk_sparepart());
                i.putExtra("Tipe Sparepart : ", spare.getMerk_sparepart());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nama_sparepart, kode_sparepart, merk_sparepart, tipe_sparepart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            nama_sparepart = (TextView) itemView.findViewById(R.id.);
//            kode_sparepart = (TextView) itemView.findViewById(R.id.);
//            merk_sparepart = (TextView) itemView.findViewById(R.id.);
//            tipe_sparepart = (TextView) itemView.findViewById(R.id.);

        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Hey You Clicked On Me", Toast.LENGTH_SHORT).show();
        }
    }
}
