package kel1.com.simato_mobile.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.API.CustomFilter_TransaksiPenjualan;
import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Adapter_RiwayatTransaksi extends RecyclerView.Adapter<Adapter_RiwayatTransaksi.MyViewHolder> implements Filterable {
    public List<Model_TransaksiPenjualan> transaksipenjualanFilter;
    public List<Model_TransaksiPenjualan> transaksipenjualan = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_TransaksiPenjualan filter_transaksipenjualan;
    public Adapter_RiwayatTransaksi(List<Model_TransaksiPenjualan> transaksipenjualan, Context context, Adapter_RiwayatTransaksi.RecyclerViewClickListener mListener) {
        this.transaksipenjualanFilter = transaksipenjualan;
        this.transaksipenjualan = transaksipenjualan;
        this.context = context;
        this.mListener = mListener;
    }
    public Adapter_RiwayatTransaksi(List<Model_TransaksiPenjualan> transaksipenjualan, Context context) {
        this.transaksipenjualan = transaksipenjualan;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_transaksi_penjualan, viewGroup, false);
        return new Adapter_RiwayatTransaksi.MyViewHolder(v, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull final Adapter_RiwayatTransaksi.MyViewHolder myViewHolder, int i) {
        final Model_TransaksiPenjualan tp = transaksipenjualan.get(i);

        Log.d("Nama Cabang : ",tp.getNama_cabang());
        Log.d("Kode Transaksi : ",tp.getKode_transaksi());
        Log.d("Tanggal Transaksi : ",tp.getTgl_transaksi());
        Log.d("Diskon : ",tp.getDiskon().toString());
        Log.d("Total Transaksi : ",tp.getTotal_transaksi().toString());
        Log.d("Status Transaksi : ",tp.getStatus_transaksi());

        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        final String inputDateStr=tp.getTgl_transaksi();
        Date date = null;
        try
        {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String outputDateStr = outputFormat.format(date);
        myViewHolder.tanggal_transaksi.setText  ("  Tanggal Transaksi : "+outputDateStr);
        myViewHolder.nama_cabang.setText        ("  Nama Cabang : "+ tp.getNama_cabang());
        myViewHolder.kode_transaksi.setText     ("  Kode Transaksi : "+ tp.getKode_transaksi());
        myViewHolder.diskon.setText             ("  Diskon : "+ tp.getDiskon());
        myViewHolder.total_transaksi.setText    ("  Total Transaksi : "+ tp.getTotal_transaksi());
        myViewHolder.status_transaksi.setText   ("  Status Transaksi : "+ tp.getStatus_transaksi());
        if(tp.getStatus_transaksi().equals("Belum Lunas"))
        {
            Picasso.get().load(R.drawable.icon_belum_selesai).fit().into(myViewHolder.imgStatus);
        }
        else if(tp.getStatus_transaksi().equals("Sudah Lunas"))
        {
            Picasso.get().load(R.drawable.icon_selesai).fit().into(myViewHolder.imgStatus);
        }

        myViewHolder.imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tp.getStatus_transaksi().equals("Belum Lunas")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Transaksi "+ tp.getKode_transaksi());

                    // Setting Dialog Message
                    alertDialog.setMessage("Status Transaksi Belum Lunas");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.logo_atma_auto);

                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                }
                else if(tp.getStatus_transaksi().equals("Sudah Lunas"))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Transaksi "+ tp.getKode_transaksi());

                    // Setting Dialog Message
                    alertDialog.setMessage("Status Transaksi Sudah Selesai");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.logo_atma_auto);

                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksipenjualan.size();
    }

    @Override
    public Filter getFilter() {

        return null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Adapter_RiwayatTransaksi.RecyclerViewClickListener mListener;
        protected TextView nama_cabang, kode_transaksi, tanggal_transaksi, diskon, total_transaksi,status_transaksi;
        protected ImageView imgStatus;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_RiwayatTransaksi.RecyclerViewClickListener listener) {
            super(itemView);
            nama_cabang = (TextView) itemView.findViewById(R.id.nama_cabang);
            kode_transaksi = (TextView) itemView.findViewById(R.id.kode_transaksi);
            tanggal_transaksi = (TextView) itemView.findViewById(R.id.tanggal_transaksi);
            diskon = (TextView) itemView.findViewById(R.id.diskon);
            total_transaksi = (TextView) itemView.findViewById(R.id.total_transaksi);
            status_transaksi = (TextView) itemView.findViewById(R.id.status_transaksi);
            imgStatus = itemView.findViewById(R.id.imageView_status_transaksi);
            mRowContainer = itemView.findViewById(R.id.row_container);
            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.row_container:
                   // mListener.onRowClick(mRowContainer, getAdapterPosition());
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
