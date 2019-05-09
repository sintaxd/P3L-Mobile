package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_TransaksiPenjualan;
import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;

public class CustomFilter_TransaksiPenjualan extends Filter  {
    Adapter_TransaksiPenjualan adapterTransaksiPenjualan;
    ArrayList<Model_TransaksiPenjualan> filterList;

    public CustomFilter_TransaksiPenjualan(ArrayList<Model_TransaksiPenjualan> filterList, Adapter_TransaksiPenjualan adapterTransaksiPenjualan)
    {
        this.adapterTransaksiPenjualan = adapterTransaksiPenjualan;
        this.filterList=filterList;
    }

    //FILTERING OCCURS
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Model_TransaksiPenjualan> filteredTransaksiPenjualan=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getKode_transaksi().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredTransaksiPenjualan.add(filterList.get(i));
                }
            }

            results.count=filteredTransaksiPenjualan.size();
            results.values=filteredTransaksiPenjualan;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterTransaksiPenjualan.transaksipenjualan= (ArrayList<Model_TransaksiPenjualan>) results.values;

        //REFRESH
        adapterTransaksiPenjualan.notifyDataSetChanged();

    }
}

