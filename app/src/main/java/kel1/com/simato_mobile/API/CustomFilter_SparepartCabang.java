package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Sparepart;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;

public class CustomFilter_SparepartCabang extends Filter {
    Adapter_SparepartCabang adapterSparepartCabang;
    ArrayList<Model_SparepartCabang> filterList;

    public CustomFilter_SparepartCabang(ArrayList<Model_SparepartCabang> filterList, Adapter_SparepartCabang adapterSparepartCabang)
    {
        this.adapterSparepartCabang = adapterSparepartCabang;
        this.filterList=filterList;
    }//FILTERING OCCURS
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results=new Filter.FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Model_SparepartCabang> filteredSpare=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getKode_sparepart_fk().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSpare.add(filterList.get(i));
                }
            }

            results.count=filteredSpare.size();
            results.values=filteredSpare;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterSparepartCabang.sparepartcabang= (ArrayList<Model_SparepartCabang>) results.values;

        //REFRESH
        adapterSparepartCabang.notifyDataSetChanged();

    }
}
