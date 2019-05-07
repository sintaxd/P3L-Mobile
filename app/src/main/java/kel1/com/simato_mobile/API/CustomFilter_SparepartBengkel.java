package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Sparepart;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartBengkel;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartBengkel;

public class CustomFilter_SparepartBengkel extends Filter{
    Adapter_SparepartBengkel adapter_sparepartBengkel;
    ArrayList<Model_SparepartBengkel> filterList;

    public CustomFilter_SparepartBengkel(ArrayList<Model_SparepartBengkel> filterList, Adapter_SparepartBengkel adapter_sparepartBengkel)
    {
        this.adapter_sparepartBengkel = adapter_sparepartBengkel;
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
            ArrayList<Model_SparepartBengkel> filteredSpare=new ArrayList<>();

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

        adapter_sparepartBengkel.sparepartbengkel= (ArrayList<Model_SparepartBengkel>) results.values;

        //REFRESH
        adapter_sparepartBengkel.notifyDataSetChanged();

    }
}
