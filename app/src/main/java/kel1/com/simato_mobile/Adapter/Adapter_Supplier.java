package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_Supplier;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;

public class Adapter_Supplier extends RecyclerView.Adapter<Adapter_Supplier.MyViewHolder> implements Filterable{

    public List<Model_Supplier> supFilter;
    public List<Model_Supplier> supplier = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_Supplier filter_supplier;

    public Adapter_Supplier(List<Model_Supplier> supplier, Context context, RecyclerViewClickListener mListener) {
        this.supFilter = supplier;
        this.supplier = supplier;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_supplier, viewGroup, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Supplier.MyViewHolder myViewHolder, int i) {
        final Model_Supplier sup = supplier.get(i);
        myViewHolder.nama_supplier.setText  ("  Nama Supplier     : "+ sup.getNama_supplier());
        myViewHolder.alamat_supplier.setText("  Alamat Supplier   : "+ sup.getAlamat_supplier());
        myViewHolder.noTelp_supplier.setText("  No Telp Supplier  : "+ sup.getNoTelp_supplier());
        myViewHolder.nama_sales.setText     ("  Nama Sales          : "+ sup.getNama_sales());
        myViewHolder.noTelp_sales.setText   ("  No Telp Sales       : "+ sup.getNoTelp_sales());

    }

    @Override
    public int getItemCount() {
        return supplier.size();
    }

    @Override
    public Filter getFilter() {
        if (filter_supplier==null) {
            filter_supplier=new CustomFilter_Supplier((ArrayList<Model_Supplier>) supFilter, this);
        }
        return filter_supplier;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_Supplier.RecyclerViewClickListener mListener;
        protected TextView nama_supplier, alamat_supplier, noTelp_supplier, nama_sales, noTelp_sales;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_Supplier.RecyclerViewClickListener listener) {
            super(itemView);
            nama_supplier = (TextView) itemView.findViewById(R.id.nama_supplier);
            alamat_supplier = (TextView) itemView.findViewById(R.id.alamat_supplier);
            noTelp_supplier = (TextView) itemView.findViewById(R.id.noTelp_supplier);
            nama_sales = (TextView) itemView.findViewById(R.id.nama_Sales);
            noTelp_sales = (TextView) itemView.findViewById(R.id.noTelp_Sales);
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
