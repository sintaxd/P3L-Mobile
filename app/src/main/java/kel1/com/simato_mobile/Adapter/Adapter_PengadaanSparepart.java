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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_Motor;
import kel1.com.simato_mobile.API.CustomFilter_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;

public class Adapter_PengadaanSparepart extends RecyclerView.Adapter<Adapter_PengadaanSparepart.MyViewHolder> implements Filterable {
    public List<Model_PengadaanSparepart> pengadaansparepartFilter;
    public List<Model_PengadaanSparepart> pengadaansparepart = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_PengadaanSparepart filter_pengadaan_sparepart;

    public Adapter_PengadaanSparepart(List<Model_PengadaanSparepart> pengadaansparepart, Context context, Adapter_PengadaanSparepart.RecyclerViewClickListener mListener) {
        this.pengadaansparepartFilter = pengadaansparepart;
        this.pengadaansparepart = pengadaansparepart;
        this.context = context;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_pengadaan_sparepart, viewGroup, false);
        return new Adapter_PengadaanSparepart.MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_PengadaanSparepart.MyViewHolder myViewHolder, int i) {
       final Model_PengadaanSparepart pengadaan = pengadaansparepart.get(i);
        Log.d("Nama Supplier : ",pengadaan.getNama_supplier());
        Log.d("Status Cetak : ",pengadaan.getStatusCetak_pengadaan());
        Log.d("Status Pengadaan : ",pengadaan.getStatus_pengadaan());
        Log.d("Total Harga : ",pengadaan.getTotalHarga_pengadaan().toString());
 //       Log.d("Tanggal Pengadaan : ",pengadaan.getTgl_pengadaan().toString());
//        Log.d("Tgl Barang Datang : ",pengadaan.getTgl_barangDatang().toString());
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        String inputDateStr=pengadaan.getTgl_pengadaan();
        Date date = null;
        try
        {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        myViewHolder.tanggal_pengadaan.setText          ("  Tanggal Pengadaan           : "+ outputDateStr);

        myViewHolder.nama_supplier.setText              ("  Nama Supplier               : "+ pengadaan.getNama_supplier());
        myViewHolder.status_cetak_pengadaan.setText     ("  Status Cetak Pengadaan      : "+ pengadaan.getStatusCetak_pengadaan());
        myViewHolder.status_pengadaan.setText           ("  Status Pengadaan            : "+ pengadaan.getStatus_pengadaan());
        myViewHolder.total_harga_pengadaan.setText      ("  Total Harga Pengadaan       : "+ pengadaan.getTotalHarga_pengadaan().toString());

        if(pengadaan.getTgl_barangDatang()==null)
        {
            myViewHolder.tanggal_barang_datang.setText  ("  Tanggal Barang Datang       : Belum Datang");
        }
        else
        {
            myViewHolder.tanggal_barang_datang.setText   ("  Tanggal Barang Datang       : "+ pengadaan.getTgl_barangDatang().toString());
        }

    }

    @Override
    public int getItemCount() {
        return pengadaansparepart.size();
    }

    @Override
    public Filter getFilter() {
        if (filter_pengadaan_sparepart==null) {
            filter_pengadaan_sparepart=new CustomFilter_PengadaanSparepart((ArrayList<Model_PengadaanSparepart>) pengadaansparepartFilter, this);
        }
        return filter_pengadaan_sparepart;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Adapter_PengadaanSparepart.RecyclerViewClickListener mListener;
        protected TextView nama_supplier, status_cetak_pengadaan, status_pengadaan, total_harga_pengadaan, tanggal_pengadaan, tanggal_barang_datang;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_PengadaanSparepart.RecyclerViewClickListener listener) {
            super(itemView);
            nama_supplier = (TextView) itemView.findViewById(R.id.nama_supplier);
            status_cetak_pengadaan = (TextView) itemView.findViewById(R.id.status_cetak_pengadaan);
            status_pengadaan = (TextView) itemView.findViewById(R.id.status_pengadaan);
            total_harga_pengadaan = (TextView) itemView.findViewById(R.id.total_harga_pengadaan);

            tanggal_pengadaan = (TextView) itemView.findViewById(R.id.tanggal_pengadaan);
            tanggal_barang_datang = (TextView) itemView.findViewById(R.id.tanggal_barang_datang);

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
