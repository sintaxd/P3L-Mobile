package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Sparepart;
import kel1.com.simato_mobile.Model.Model_Sparepart;

public class CustomFilter_Sparepart extends Filter {
    Adapter_Sparepart adapterSparepart;
    ArrayList<Model_Sparepart> filterList;

    public CustomFilter_Sparepart(ArrayList<Model_Sparepart> filterList, Adapter_Sparepart adapterSparepart)
    {
        this.adapterSparepart = adapterSparepart;
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
            ArrayList<Model_Sparepart> filteredSpare=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_sparepart().toUpperCase().contains(constraint))
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

        adapterSparepart.sparepart= (ArrayList<Model_Sparepart>) results.values;

        //REFRESH
        adapterSparepart.notifyDataSetChanged();
    }
}
