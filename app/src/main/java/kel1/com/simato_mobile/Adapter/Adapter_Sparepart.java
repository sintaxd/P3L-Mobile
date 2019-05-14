package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_Sparepart;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.R;
public class Adapter_Sparepart extends RecyclerView.Adapter<Adapter_Sparepart.MyViewHolder> implements Filterable {

    private List<Model_Sparepart> sparepFilter;
    public List<Model_Sparepart> sparepart = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_Sparepart filter_sparepart;
    private Bitmap bitmap;

    public Adapter_Sparepart(List<Model_Sparepart> sparepart, Context context, RecyclerViewClickListener mListener) {
        this.sparepFilter = sparepart;
        this.sparepart = sparepart;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_sparepart, viewGroup, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Sparepart.MyViewHolder myViewHolder, int i) {
        final Model_Sparepart spare = sparepart.get(i);
        String img;

        myViewHolder.nama_sparepart.setText("   Nama Sparepart : "+ spare.getNama_sparepart());
        myViewHolder.kode_sparepart.setText("   Kode Sparepart : "+ spare.getKode_sparepart());
        myViewHolder.merk_sparepart.setText("   Merk Sparepart : "+ spare.getMerk_sparepart());
        myViewHolder.tipe_sparepart.setText("   Tipe Sparepart : "+ spare.getTipe_sparepart());
        img=spare.getGambar_sparepart();
        Picasso.get().load("http://192.168.94.52:8000/images/"+img).fit().into(myViewHolder.gambar_sparepart);
    }


    @Override
    public int getItemCount() {
        return sparepart.size();
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

    @Override
    public Filter getFilter() {
        if (filter_sparepart==null) {
            filter_sparepart=new CustomFilter_Sparepart((ArrayList<Model_Sparepart>) sparepFilter, this);
        }
        return filter_sparepart;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_Sparepart.RecyclerViewClickListener mListener;
        protected TextView nama_sparepart, kode_sparepart, merk_sparepart, tipe_sparepart;
        protected ImageView gambar_sparepart;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_Sparepart.RecyclerViewClickListener listener) {
            super(itemView);
            nama_sparepart = itemView.findViewById(R.id.nama_sparepart);
            kode_sparepart = itemView.findViewById(R.id.kode_sparepart);
            merk_sparepart = itemView.findViewById(R.id.merk_sparepart);
            tipe_sparepart = itemView.findViewById(R.id.tipe_sparepart);
            gambar_sparepart = itemView.findViewById(R.id.gambar_sparepart);
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
