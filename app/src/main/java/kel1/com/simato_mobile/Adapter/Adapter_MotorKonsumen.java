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

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.CustomFilter_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.R;

public class Adapter_MotorKonsumen extends RecyclerView.Adapter<Adapter_MotorKonsumen.MyViewHolder> implements Filterable {
    public List<Model_MotorKonsumen> motorkonsumenFilter;
    public List<Model_MotorKonsumen> motorkonsumen = new ArrayList<>();
    private Context context;
    private Adapter_MotorKonsumen.RecyclerViewClickListener mListener;
    CustomFilter_MotorKonsumen filter_motorkonsumen;

        public Adapter_MotorKonsumen(List<Model_MotorKonsumen> motorkonsumen, Context context, Adapter_MotorKonsumen.RecyclerViewClickListener mListener) {
            this.motorkonsumenFilter = motorkonsumen;
            this.motorkonsumen = motorkonsumen;
            this.context = context;
            this.mListener = mListener;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_motor_konsumen, viewGroup, false);
            return new MyViewHolder(v, mListener);
        }

    @Override
        public void onBindViewHolder(@NonNull Adapter_MotorKonsumen.MyViewHolder myViewHolder, int i) {
            final Model_MotorKonsumen mtr = motorkonsumen.get(i);
            Log.d("Tipe Motor : ",mtr.getTipe_motor());
            Log.d("Nama Konsumen : ",mtr.getNama_konsumen());
            Log.d("Plat Motor : ",mtr.getPlat_motorKonsumen());

            myViewHolder.nama_konsumen.setText  ("  Nama Konsumen   : "+ mtr.getNama_konsumen());
            myViewHolder.tipe_motor.setText     ("  Tipe Motor      :  "+ mtr.getTipe_motor());
            myViewHolder.plat_motor.setText     ("  Plat Motor      : "+ mtr.getPlat_motorKonsumen());
        }

        @Override
        public int getItemCount() {
            return motorkonsumen.size();
        }

        @Override
        public Filter getFilter() {

            if (filter_motorkonsumen==null) {
                filter_motorkonsumen=new CustomFilter_MotorKonsumen((ArrayList<Model_MotorKonsumen>) motorkonsumenFilter, this);
            }
            return filter_motorkonsumen;
        }

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Adapter_MotorKonsumen.RecyclerViewClickListener mListener;
    protected TextView nama_konsumen, tipe_motor, plat_motor;
    private RelativeLayout mRowContainer;
    public MyViewHolder(@NonNull View itemView, Adapter_MotorKonsumen.RecyclerViewClickListener listener) {
        super(itemView);
        nama_konsumen = itemView.findViewById(R.id.nama_konsumen);
        tipe_motor = itemView.findViewById(R.id.tipe_motor);
        plat_motor = itemView.findViewById(R.id.plat_motor);
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
