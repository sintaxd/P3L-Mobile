package kel1.com.simato_mobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.MyViewHolder> {
    private Context context;
    private List<SupplierDAO> result;

    public SupplierAdapter(Context context, List<SupplierDAO> result) {
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
    public void onBindViewHolder(@NonNull SupplierAdapter.MyViewHolder myViewHolder, int i) {
        SupplierDAO sup = result.get(i);
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
}
