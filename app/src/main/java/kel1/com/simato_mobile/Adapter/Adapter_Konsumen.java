package kel1.com.simato_mobile.Adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_Konsumen;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.R;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Adapter_Konsumen extends RecyclerView.Adapter<Adapter_Konsumen.MyViewHolder> implements Filterable {

    public List<Model_Konsumen> konFilter;
    public List<Model_Konsumen> konsumen = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_Konsumen filter_konsumen;

    public Adapter_Konsumen(List<Model_Konsumen> konsumen, Context context, RecyclerViewClickListener mListener) {
        this.konFilter = konsumen;
        this.konsumen = konsumen;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_konsumen, viewGroup, false);
        return new Adapter_Konsumen.MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Konsumen.MyViewHolder myViewHolder, int i) {
        final Model_Konsumen kon = konsumen.get(i);
        myViewHolder.nama_konsumen.setText  ("  Nama Konsumen     : "+ kon.getNama_konsumen());
        myViewHolder.alamat_konsumen.setText("  Alamat Konsumen   : "+ kon.getAlamat_konsumen());
        myViewHolder.noTelp_konsumen.setText("  No Telp Konsumen  : "+ kon.getNoTelp_konsumen());

    }

    @Override
    public int getItemCount() {
        return konsumen.size();
    }

    @Override
    public Filter getFilter() {

        if (filter_konsumen==null) {
            filter_konsumen=new CustomFilter_Konsumen((ArrayList<Model_Konsumen>) konFilter, this);
        }
        return filter_konsumen;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_Konsumen.RecyclerViewClickListener mListener;
        protected TextView nama_konsumen, alamat_konsumen, noTelp_konsumen;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_Konsumen.RecyclerViewClickListener listener) {
            super(itemView);
            nama_konsumen = (TextView) itemView.findViewById(R.id.nama_konsumen);
            alamat_konsumen = (TextView) itemView.findViewById(R.id.alamat_konsumen);
            noTelp_konsumen = (TextView) itemView.findViewById(R.id.noTelp_konsumen);
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
