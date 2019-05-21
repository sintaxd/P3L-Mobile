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

import kel1.com.simato_mobile.Model.Model_DetilTransaksiService;
import kel1.com.simato_mobile.R;

public class Adapter_DetilTransaksiService extends RecyclerView.Adapter<Adapter_DetilTransaksiService.MyViewHolder> implements Filterable {
    public List<Model_DetilTransaksiService> detilFilter;
    public List<Model_DetilTransaksiService> detil = new ArrayList<>();
    private Context context;

    public Adapter_DetilTransaksiService(List<Model_DetilTransaksiService> detil, Context context) {
        this.detil = detil;
        this.context = context;
    }

    public Adapter_DetilTransaksiService(List<Model_DetilTransaksiService> detil){
        this.detil=detil;
        this.detilFilter=detil;
    }
    @NonNull
    @Override
    public Adapter_DetilTransaksiService.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycle_adapter_detil_transaksi_penjualan_service, viewGroup, false);
        return new Adapter_DetilTransaksiService.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_DetilTransaksiService.MyViewHolder myViewHolder, final int i) {
        final Model_DetilTransaksiService detilTransaksiService = detilFilter.get(i);

        myViewHolder.nama_service.setText     ("   Jasa Service :"+ detilTransaksiService.getNama_jasaService());
        myViewHolder.harga_satuan.setText     ("   Harga        :"+ detilTransaksiService.getHarga_jasaService());


        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detilFilter.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i,getItemCount());
                Intent intent = new Intent("updateTotal");
                intent.putExtra("sub_total",detilTransaksiService.getHarga_jasaService().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detil.size();
    }

    @Override
    public Filter getFilter() {
//        if (filter_detil==null) {
//            filter_detil=new CustomFilter_DetilPengadaanSparepart((ArrayList<Model_DetilTransaksiService>) detilFilter, this);
//        }
//        return filter_detil;
        return null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView nama_service, harga_satuan;
        private RelativeLayout mRowContainer;
        Button btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_service = (TextView) itemView.findViewById(R.id.nama_service);
            harga_satuan = (TextView) itemView.findViewById(R.id.harga_satuan);
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
