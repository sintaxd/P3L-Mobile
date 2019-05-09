package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_TransaksiPenjualan;
import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;
import kel1.com.simato_mobile.R;

public class Adapter_TransaksiPenjualan extends RecyclerView.Adapter<Adapter_TransaksiPenjualan.MyViewHolder> implements Filterable {
    public List<Model_TransaksiPenjualan> transaksipenjualanFilter;
    public List<Model_TransaksiPenjualan> transaksipenjualan = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_TransaksiPenjualan filter_transaksipenjualan;
    public Adapter_TransaksiPenjualan(List<Model_TransaksiPenjualan> transaksipenjualan, Context context, Adapter_TransaksiPenjualan.RecyclerViewClickListener mListener) {
        this.transaksipenjualanFilter = transaksipenjualan;
        this.transaksipenjualan = transaksipenjualan;
        this.context = context;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_transaksi_penjualan, viewGroup, false);
        return new Adapter_TransaksiPenjualan.MyViewHolder(v, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_TransaksiPenjualan.MyViewHolder myViewHolder, int i) {
        final Model_TransaksiPenjualan tp = transaksipenjualan.get(i);

        Log.d("Nama Cabang : ",tp.getNama_cabang());
        Log.d("Kode Transaksi : ",tp.getKode_transaksi());
        Log.d("Tanggal Transaksi : ",tp.getTgl_transaksi());
        Log.d("Diskon : ",tp.getDiskon().toString());
        Log.d("Total Transaksi : ",tp.getTotal_transaksi().toString());
        Log.d("Status Transaksi : ",tp.getStatus_transaksi());

        myViewHolder.nama_cabang.setText        ("  Nama Cabang : "+ tp.getNama_cabang());
        myViewHolder.kode_transaksi.setText     ("  Kode Transaksi : "+ tp.getKode_transaksi());
        myViewHolder.tanggal_transaksi.setText  ("  Tanggal Transaksi : "+ tp.getTgl_transaksi());
        myViewHolder.diskon.setText             ("  Diskon : "+ tp.getDiskon());
        myViewHolder.total_transaksi.setText    ("  Total Transaksi : "+ tp.getTotal_transaksi());
        myViewHolder.status_transaksi.setText   ("  Status Transaksi : "+ tp.getStatus_transaksi());
    }

    @Override
    public int getItemCount() {
        return transaksipenjualan.size();
    }

    @Override
    public Filter getFilter() {
        if (filter_transaksipenjualan==null) {
            filter_transaksipenjualan=new CustomFilter_TransaksiPenjualan((ArrayList<Model_TransaksiPenjualan>) transaksipenjualanFilter, this);
        }
        return filter_transaksipenjualan;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_TransaksiPenjualan.RecyclerViewClickListener mListener;
        protected TextView nama_cabang, kode_transaksi, tanggal_transaksi, diskon, total_transaksi,status_transaksi;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_TransaksiPenjualan.RecyclerViewClickListener listener) {
            super(itemView);
            nama_cabang = (TextView) itemView.findViewById(R.id.nama_cabang);
            kode_transaksi = (TextView) itemView.findViewById(R.id.kode_transaksi);
            tanggal_transaksi = (TextView) itemView.findViewById(R.id.tanggal_transaksi);
            diskon = (TextView) itemView.findViewById(R.id.diskon);
            total_transaksi = (TextView) itemView.findViewById(R.id.total_transaksi);
            status_transaksi = (TextView) itemView.findViewById(R.id.status_transaksi);
            mRowContainer = itemView.findViewById(R.id.row_container);
            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
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
