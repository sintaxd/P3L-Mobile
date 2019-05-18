package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_DetilTransaksiSparepart;
import kel1.com.simato_mobile.R;

public class Adapter_DetilTransaksiSparepart extends RecyclerView.Adapter<Adapter_DetilTransaksiSparepart.MyViewHolder> {

    public List<Model_DetilTransaksiSparepart> detilFilter;
    public List<Model_DetilTransaksiSparepart> detil = new ArrayList<>();
    private Context context;

    public Adapter_DetilTransaksiSparepart(List<Model_DetilTransaksiSparepart> detil){
        this.detil=detil;
        this.detilFilter=detil;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycle_adapter_detil_transaksi_penjualan_sparepart, viewGroup, false);
        return new Adapter_DetilTransaksiSparepart.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Model_DetilTransaksiSparepart detilTransaksiSparepart = detilFilter.get(i);

        myViewHolder.nama_sparepart.setText     ("   Nama Sparepart :"+ detilTransaksiSparepart.getNama_sparepart());
        myViewHolder.harga_satuan.setText       ("   Harga Satuan   :"+ detilTransaksiSparepart.getHarga_satuan());
        myViewHolder.satuan.setText             ("   Satuan Beli    :"+ detilTransaksiSparepart.getJumlahBeli_sparepart());
        myViewHolder.sub_total.setText          ("   Subtotal       :"+ detilTransaksiSparepart.getSubTotal_sparepart());


        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detilFilter.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i,getItemCount());
                Intent intent = new Intent("updateTotal");
                intent.putExtra("sub_total",detilTransaksiSparepart.getSubTotal_sparepart().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detil.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView nama_sparepart, harga_satuan, satuan, sub_total;
        private RelativeLayout mRowContainer;
        Button btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_sparepart = (TextView) itemView.findViewById(R.id.nama_sparepart);
            harga_satuan = (TextView) itemView.findViewById(R.id.harga_satuan);
            satuan = (TextView) itemView.findViewById(R.id.satuan);
            sub_total = (TextView) itemView.findViewById(R.id.sub_total);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.row_container:
                    //mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
}
