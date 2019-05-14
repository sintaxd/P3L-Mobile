package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import kel1.com.simato_mobile.API.CustomFilter_SparepartBengkel;
import kel1.com.simato_mobile.Model.Model_SparepartBengkel;
import kel1.com.simato_mobile.R;

public class Adapter_SparepartBengkel extends RecyclerView.Adapter<Adapter_SparepartBengkel.MyViewHolder> implements Filterable {
    private List<Model_SparepartBengkel> sparepartbengkelFilter;
    public List<Model_SparepartBengkel> sparepartbengkel = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_SparepartBengkel filter_sparepartbengkel;
    private Bitmap bitmap;

    public Adapter_SparepartBengkel(List<Model_SparepartBengkel> sparepartbengkel, Context context, Adapter_SparepartBengkel.RecyclerViewClickListener mListener) {
        this.sparepartbengkelFilter = sparepartbengkel;
        this.sparepartbengkel = sparepartbengkel;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public Adapter_SparepartBengkel.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_sparepart_bengkel, viewGroup, false);
        return new Adapter_SparepartBengkel.MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_SparepartBengkel.MyViewHolder myViewHolder, int i) {
        final Model_SparepartBengkel spare = sparepartbengkel.get(i);
        String img;

        myViewHolder.tipe_sparepart.setText ("   Tipe Sparepart : "+ spare.getTipe_sparepart());
        myViewHolder.merk_sparepart.setText ("   Merk Sparepart : "+ spare.getMerk_sparepart());
        myViewHolder.nama_sparepart.setText ("   Nama Sparepart : "+ spare.getNama_sparepart());
        myViewHolder.cabang.setText         ("   Tersedia di Cabang : "+ spare.getNama_cabang());
        myViewHolder.harga_sparepart.setText("   Harga Sparepart : "+spare.getHargaJual_sparepart());
        myViewHolder.stok_sparepart.setText ("   Stok Sparepart : "+spare.getstokSisa_sparepart());

        img=spare.getGambar_sparepart();
        Picasso.get().load("http://192.168.94.52:8000/images/"+img).fit().into(myViewHolder.gambar_sparepart);
    }


    @Override
    public int getItemCount() {
        return sparepartbengkel.size();
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    @Override
    public Filter getFilter() {
        if (filter_sparepartbengkel==null) {
            filter_sparepartbengkel=new CustomFilter_SparepartBengkel((ArrayList<Model_SparepartBengkel>) sparepartbengkelFilter, this);
        }
        return filter_sparepartbengkel;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_SparepartBengkel.RecyclerViewClickListener mListener;
        protected TextView nama_sparepart, merk_sparepart, tipe_sparepart, cabang, harga_sparepart, stok_sparepart;
        protected ImageView gambar_sparepart;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_SparepartBengkel.RecyclerViewClickListener listener) {
            super(itemView);
            nama_sparepart  = itemView.findViewById(R.id.nama_sparepart);
            merk_sparepart  = itemView.findViewById(R.id.merk_sparepart);
            tipe_sparepart  = itemView.findViewById(R.id.tipe_sparepart);
            cabang          = itemView.findViewById(R.id.cabang);
            harga_sparepart = itemView.findViewById(R.id.harga_sparepart);
            stok_sparepart  = itemView.findViewById(R.id.stok_sparepart);

            gambar_sparepart= itemView.findViewById(R.id.gambar_sparepart);
            mRowContainer   = itemView.findViewById(R.id.row_container);
            mListener       = listener;
            mRowContainer.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.row_container:
//                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }
}

