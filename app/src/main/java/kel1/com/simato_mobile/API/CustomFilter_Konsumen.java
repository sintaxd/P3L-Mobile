package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Konsumen;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Supplier;

public class CustomFilter_Konsumen extends Filter {
    Adapter_Konsumen adapterKonsumen;
    ArrayList<Model_Konsumen> filterList;

    public CustomFilter_Konsumen(ArrayList<Model_Konsumen> filterList, Adapter_Konsumen adapterKonsumen)
    {
        this.adapterKonsumen = adapterKonsumen;
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
            ArrayList<Model_Konsumen> filteredKon=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_konsumen().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredKon.add(filterList.get(i));
                }
            }

            results.count=filteredKon.size();
            results.values=filteredKon;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterKonsumen.konsumen= (ArrayList<Model_Konsumen>) results.values;

        //REFRESH
        adapterKonsumen.notifyDataSetChanged();

    }
}

