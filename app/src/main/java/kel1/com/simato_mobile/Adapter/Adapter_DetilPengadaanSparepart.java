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

import kel1.com.simato_mobile.API.CustomFilter_DetilPengadaanSparepart;
import kel1.com.simato_mobile.API.CustomFilter_Motor;
import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tambah_pengadaan_sparepart;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;


public class Adapter_DetilPengadaanSparepart extends RecyclerView.Adapter<Adapter_DetilPengadaanSparepart.MyViewHolder> implements Filterable {
    public List<Model_DetilPengadaanSparepart> detilFilter;
    public List<Model_DetilPengadaanSparepart> detil = new ArrayList<>();
    public int value=1;
    private Context context;

    public Adapter_DetilPengadaanSparepart(List<Model_DetilPengadaanSparepart> detil, Context context) {
        this.detil = detil;
        this.context = context;
    }

    public Adapter_DetilPengadaanSparepart(List<Model_DetilPengadaanSparepart> detil, int value){
        this.detil=detil;
        this.detilFilter=detil;
        this.value=value;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycle_adapter_detil_pengadaan_sparepart, viewGroup, false);
        return new Adapter_DetilPengadaanSparepart.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Model_DetilPengadaanSparepart detilPengadaan = detilFilter.get(i);

        myViewHolder.nama_sparepart.setText     ("   Nama Sparepart :"+ detilPengadaan.getNama_sparepart());
        myViewHolder.satuan_pengadaan.setText   ("   Satuan :"+ detilPengadaan.getSatuan_pengadaan());
        myViewHolder.harga_beli.setText         ("   Harga Satuan :"+ detilPengadaan.getHargaBeli_sparepart());
        myViewHolder.sub_total.setText          ("   Subtotal :"+ detilPengadaan.getSub_total_sparepart());

        if(value==1)
        {
            myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detilFilter.remove(i);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i,getItemCount());
                    Intent intent = new Intent("updateTotal");
                    intent.putExtra("sub_total",detilPengadaan.getSub_total_sparepart().toString());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });
        }
        else if(value==0)
        {
            myViewHolder.btnDelete.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return detil.size();
    }

    @Override
    public Filter getFilter() {
//        if (filter_detil==null) {
//            filter_detil=new CustomFilter_DetilPengadaanSparepart((ArrayList<Model_DetilPengadaanSparepart>) detilFilter, this);
//        }
//        return filter_detil;
        return null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView nama_sparepart, satuan_pengadaan, sub_total, harga_beli;
        private RelativeLayout mRowContainer;
        Button btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_sparepart = (TextView) itemView.findViewById(R.id.nama_sparepart);
            satuan_pengadaan = (TextView) itemView.findViewById(R.id.satuan_pengadaan);
            harga_beli = (TextView) itemView.findViewById(R.id.harga_beli);
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
