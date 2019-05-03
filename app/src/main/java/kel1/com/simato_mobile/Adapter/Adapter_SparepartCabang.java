package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_Sparepart;
import kel1.com.simato_mobile.API.CustomFilter_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;

public class Adapter_SparepartCabang  extends RecyclerView.Adapter<Adapter_SparepartCabang.MyViewHolder> implements Filterable {

    private List<Model_SparepartCabang> sparepFilter;
    public List<Model_SparepartCabang> sparepartcabang = new ArrayList<>();
    private Context context;
    private Adapter_SparepartCabang.RecyclerViewClickListener mListener;
    CustomFilter_SparepartCabang filter_sparepartcabang;
    private Bitmap bitmap;

    public Adapter_SparepartCabang(List<Model_SparepartCabang> sparepartcabang, Context context, Adapter_SparepartCabang.RecyclerViewClickListener mListener) {
        this.sparepFilter = sparepartcabang;
        this.sparepartcabang = sparepartcabang;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_sparepart_cabang, viewGroup, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_SparepartCabang.MyViewHolder myViewHolder, int i) {
        final Model_SparepartCabang sparebang = sparepartcabang.get(i);

        myViewHolder.nama_cabang.setText        ("  Nama Cabang          : "+ String.valueOf(sparebang.getId_cabang_fk()));
        myViewHolder.nama_sparepart.setText     ("  Kode Sparepart        : "+ String.valueOf(sparebang.getKode_sparepart_fk()));
        myViewHolder.harga_beli.setText         ("  Harga Beli                  : "+ String.valueOf(sparebang.getHargaBeli_sparepart()));
        myViewHolder.harga_jual.setText         ("  Harga Jual                 : "+ String.valueOf(sparebang.getHargaJual_sparepart()));
        myViewHolder.letak_penempatan.setText   ("  Letak Penempatan  : "+ sparebang.getLetak_sparepart());
        myViewHolder.stok_minimal.setText       ("  Stok Minimal             : "+ sparebang.getStokMin_sparepart());
        myViewHolder.stok_sisa.setText          ("  Stok Sisa                    : "+ sparebang.getStokSisa_sparepart());

    }


    @Override
    public int getItemCount() {
        return sparepartcabang.size();
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    @Override
    public Filter getFilter() {
        if (filter_sparepartcabang==null) {
            filter_sparepartcabang=new CustomFilter_SparepartCabang((ArrayList<Model_SparepartCabang>) sparepFilter, this);
        }
        return filter_sparepartcabang;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_SparepartCabang.RecyclerViewClickListener mListener;
        protected TextView nama_cabang,nama_sparepart,harga_beli,harga_jual, letak_penempatan, stok_minimal, stok_sisa;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_SparepartCabang.RecyclerViewClickListener listener) {
            super(itemView);
            nama_cabang = itemView.findViewById(R.id.nama_cabang);
            nama_sparepart = itemView.findViewById(R.id.nama_sparepart);
            harga_beli = itemView.findViewById(R.id.harga_beli);
            harga_jual = itemView.findViewById(R.id.harga_jual);
            letak_penempatan = itemView.findViewById(R.id.letak_penempatan);
            stok_minimal = itemView.findViewById(R.id.stok_minimal);
            stok_sisa = itemView.findViewById(R.id.stok_sisa);
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
}
