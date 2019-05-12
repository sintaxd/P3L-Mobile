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

import kel1.com.simato_mobile.API.CustomFilter_Motor;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.R;

public class Adapter_Motor extends RecyclerView.Adapter<Adapter_Motor.MyViewHolder> implements Filterable {
    public List<Model_Motor> motFilter;
    public List<Model_Motor> motor = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_Motor filter_motor;

    public Adapter_Motor(List<Model_Motor> motor, Context context, Adapter_Motor.RecyclerViewClickListener mListener) {
        this.motFilter = motor;
        this.motor = motor;
        this.context = context;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_motor, viewGroup, false);
        return new Adapter_Motor.MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Motor.MyViewHolder myViewHolder, int i) {
        final Model_Motor mtr = motor.get(i);
        Log.d("ID Motor : ",mtr.getId_motor().toString());
        Log.d("Merk Motor : ",mtr.getMerk_motor());
        Log.d("Tipe Motor : ",mtr.getTipe_motor());
        myViewHolder.merk_motor.setText  ("  Merk Motor     : "+ mtr.getMerk_motor());
        myViewHolder.tipe_motor.setText  ("  Tipe Motor      : "+ mtr.getTipe_motor());

    }

    @Override
    public int getItemCount() {
        return motor.size();
    }

    @Override
    public Filter getFilter() {
        if (filter_motor==null) {
            filter_motor=new CustomFilter_Motor((ArrayList<Model_Motor>) motFilter, this);
        }
        return filter_motor;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_Motor.RecyclerViewClickListener mListener;
        protected TextView merk_motor, tipe_motor;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_Motor.RecyclerViewClickListener listener) {
            super(itemView);
            merk_motor = (TextView) itemView.findViewById(R.id.merk_motor);
            tipe_motor = (TextView) itemView.findViewById(R.id.tipe_motor);
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
