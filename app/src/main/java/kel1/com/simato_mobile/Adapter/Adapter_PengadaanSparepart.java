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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.API.CustomFilter_Motor;
import kel1.com.simato_mobile.API.CustomFilter_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adapter_PengadaanSparepart extends RecyclerView.Adapter<Adapter_PengadaanSparepart.MyViewHolder> implements Filterable {
    public List<Model_PengadaanSparepart> pengadaansparepartFilter;
    public List<Model_PengadaanSparepart> pengadaansparepart = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter_PengadaanSparepart filter_pengadaan_sparepart;

    public Adapter_PengadaanSparepart(List<Model_PengadaanSparepart> pengadaansparepart, Context context, Adapter_PengadaanSparepart.RecyclerViewClickListener mListener) {
        this.pengadaansparepartFilter = pengadaansparepart;
        this.pengadaansparepart = pengadaansparepart;
        this.context = context;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_adapter_pengadaan_sparepart, viewGroup, false);
        return new Adapter_PengadaanSparepart.MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_PengadaanSparepart.MyViewHolder myViewHolder, int i) {
       final Model_PengadaanSparepart pengadaan = pengadaansparepart.get(i);
        Log.d("Nama Supplier : ",pengadaan.getNama_supplier());
        Log.d("Status Cetak : ",pengadaan.getStatusCetak_pengadaan());
        Log.d("Status Pengadaan : ",pengadaan.getStatus_pengadaan());
        Log.d("Total Harga : ",pengadaan.getTotalHarga_pengadaan().toString());
        final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        final String inputDateStr=pengadaan.getTgl_pengadaan();
        Date date = null;
        try
        {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String outputDateStr = outputFormat.format(date);
        myViewHolder.tanggal_pengadaan.setText          ("  Tanggal Pengadaan : "+ outputDateStr);

        myViewHolder.nama_supplier.setText              ("  Nama Supplier     : "+ pengadaan.getNama_supplier());
        myViewHolder.status_cetak_pengadaan.setText     ("  Status Cetak      : "+ pengadaan.getStatusCetak_pengadaan());
        myViewHolder.status_pengadaan.setText           ("  Status Pengadaan  : "+ pengadaan.getStatus_pengadaan());
        myViewHolder.total_harga_pengadaan.setText      ("  Total Harga       : "+ pengadaan.getTotalHarga_pengadaan().toString());

        if(pengadaan.getTgl_barangDatang()==null)
        {
            myViewHolder.tanggal_barang_datang.setText  ("  Tanggal Barang Datang : Belum Datang");
        }
        else
        {
            String inputDateStr2=pengadaan.getTgl_barangDatang();
            Date date2 = null;
            try
            {
                date2 = inputFormat.parse(inputDateStr2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String outputDateStr2 = outputFormat.format(date2);
            myViewHolder.tanggal_barang_datang.setText   ("  Tanggal Barang Datang : "+ outputDateStr2);
        }
        if(pengadaan.getStatus_pengadaan().equals("Belum Selesai"))
        {
            Picasso.get().load(R.drawable.icon_belum_selesai).fit().into(myViewHolder.imgStatus);
        }
        else if(pengadaan.getStatus_pengadaan().equals("Sudah Selesai"))
        {
            Picasso.get().load(R.drawable.icon_selesai).fit().into(myViewHolder.imgStatus);
        }

        myViewHolder.imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pengadaan.getStatus_pengadaan().equals("Belum Selesai") && pengadaan.getStatusCetak_pengadaan().equals("Sudah Cetak")) {
                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    // Set a title for alert dialog
                    builder.setTitle("Verifikasi transaksi supplier " + pengadaan.getNama_supplier());

                    // Ask the final question
                    builder.setMessage("Apakah anda yakin untuk verifikasi status pengadaan?");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            Retrofit.Builder builder = new Retrofit
                                    .Builder()
                                    .baseUrl(ApiClient_PengadaanSparepart.baseURL)
                                    .addConverterFactory(GsonConverterFactory.create());
                            Retrofit retrofit = builder.build();
                            ApiClient_PengadaanSparepart apiClientPengadaanSparepart = retrofit.create(ApiClient_PengadaanSparepart.class);
                            Log.d("ID Pengadaan : : ", pengadaan.getId_pengadaan().toString());
                            Call<ResponseBody> pengadaanSparepartDAOCall = apiClientPengadaanSparepart.verifikasi_pengadaan(pengadaan.getId_pengadaan());
                            pengadaanSparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.code() == 201) {
                                        pengadaan.setStatus_pengadaan("Sudah Selesai");
                                        myViewHolder.status_pengadaan.setText("  Status Pengadaan : " + pengadaan.getStatus_pengadaan());
                                        String inputDateStr3=pengadaan.getTgl_barangDatang();
                                        Date date3 = null;
                                        try
                                        {
                                            date3 = inputFormat.parse(inputDateStr3);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        String outputDateStr3 = outputFormat.format(date3);
                                        pengadaan.setTgl_barangDatang(outputDateStr3);
                                        myViewHolder.tanggal_barang_datang.setText   ("  Tanggal Barang Datang : "+ pengadaan.getTgl_barangDatang());
                                        Toast.makeText(context, "Success Update", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Failed Update", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                            Picasso.get().load(R.drawable.icon_selesai).fit().into(myViewHolder.imgStatus);
                        }
                    });
                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                }
                else if(pengadaan.getStatus_pengadaan().equals("Belum Selesai") && pengadaan.getStatusCetak_pengadaan().equals("Belum Cetak"))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Pengadaan dengan supplier "+ pengadaan.getNama_supplier());

                    // Setting Dialog Message
                    alertDialog.setMessage("Silahkan Cetak Surat Pemesanan Sparepart terlebih dahulu");

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
                else if(pengadaan.getStatus_pengadaan().equals("Sudah Selesai"))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Pengadaan dengan supplier "+ pengadaan.getNama_supplier());

                    // Setting Dialog Message
                    alertDialog.setMessage("Status Pengadaan Sudah Selesai");

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
        return pengadaansparepart.size();
    }

    @Override
    public Filter getFilter() {
        if (filter_pengadaan_sparepart==null) {
            filter_pengadaan_sparepart=new CustomFilter_PengadaanSparepart((ArrayList<Model_PengadaanSparepart>) pengadaansparepartFilter, this);
        }
        return filter_pengadaan_sparepart;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Adapter_PengadaanSparepart.RecyclerViewClickListener mListener;
        protected TextView nama_supplier, status_cetak_pengadaan, status_pengadaan, total_harga_pengadaan, tanggal_pengadaan, tanggal_barang_datang;
        protected ImageView imgStatus;
        private RelativeLayout mRowContainer;
        public MyViewHolder(@NonNull View itemView, Adapter_PengadaanSparepart.RecyclerViewClickListener listener) {
            super(itemView);
            nama_supplier = (TextView) itemView.findViewById(R.id.nama_supplier);
            status_cetak_pengadaan = (TextView) itemView.findViewById(R.id.status_cetak_pengadaan);
            status_pengadaan = (TextView) itemView.findViewById(R.id.status_pengadaan);
            total_harga_pengadaan = (TextView) itemView.findViewById(R.id.total_harga_pengadaan);

            tanggal_pengadaan = (TextView) itemView.findViewById(R.id.tanggal_pengadaan);
            tanggal_barang_datang = (TextView) itemView.findViewById(R.id.tanggal_barang_datang);
            imgStatus = itemView.findViewById(R.id.imageView_status_pengadaan);
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
