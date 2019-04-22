package kel1.com.simato_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;

public class Adapter_Supplier extends RecyclerView.Adapter<Adapter_Supplier.MyViewHolder> {
    private Context context;
    private List<Model_Supplier> result;
    public Button btnDelete;

    public Adapter_Supplier(Context context, List<Model_Supplier> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter, viewGroup, false);
        final MyViewHolder holder=new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Supplier.MyViewHolder myViewHolder, int i) {
        final Model_Supplier sup = result.get(i);
        RecyclerView recycler_view_supplier;
        myViewHolder.nama_supplier.setText  ("  Nama Supplier     : "+ sup.getNama_supplier());
        myViewHolder.alamat_supplier.setText("  Alamat Supplier   : "+ sup.getAlamat_supplier());
        myViewHolder.noTelp_supplier.setText("  No Telp Supplier  : "+ sup.getNoTelp_supplier());
        myViewHolder.nama_sales.setText     ("  Nama Sales          : "+ sup.getNama_sales());
        myViewHolder.noTelp_sales.setText   ("  No Telp Sales       : "+ sup.getNoTelp_sales());
        myViewHolder.nama_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, edit_data_supplier.class);
                i.putExtra("nama_supplier", sup.getNama_supplier());
                i.putExtra("alamat_supplier", sup.getAlamat_supplier());
                i.putExtra("noTelp_supplier", sup.getNoTelp_supplier());
                i.putExtra("nama_sales", sup.getNama_sales());
                i.putExtra("noTelp_sales", sup.getNoTelp_sales());
                i.putExtra("id_supplier", sup.getId_supplier());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nama_supplier, alamat_supplier, noTelp_supplier, nama_sales, noTelp_sales;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_supplier = (TextView) itemView.findViewById(R.id.nama_supplier);
            alamat_supplier = (TextView) itemView.findViewById(R.id.alamat_supplier);
            noTelp_supplier = (TextView) itemView.findViewById(R.id.noTelp_supplier);
            nama_sales = (TextView) itemView.findViewById(R.id.nama_Sales);
            noTelp_sales = (TextView) itemView.findViewById(R.id.noTelp_Sales);

        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context,"Hey You Clicked On Me", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSupplierList(List<Model_Supplier> newList)
    {
        result = new ArrayList<>();
        result.addAll(newList);
        notifyDataSetChanged();
    }
}
