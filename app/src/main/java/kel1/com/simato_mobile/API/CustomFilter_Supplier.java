package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.Model.Model_Supplier;

public class CustomFilter_Supplier extends Filter {

    Adapter_Supplier adapterSupplier;
    ArrayList<Model_Supplier> filterList;

    public CustomFilter_Supplier(ArrayList<Model_Supplier> filterList, Adapter_Supplier adapterSupplier)
    {
        this.adapterSupplier = adapterSupplier;
        this.filterList=filterList;
    }

    //FILTERING OCCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Model_Supplier> filteredSup=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_supplier().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredSup.add(filterList.get(i));
                }
            }

            results.count=filteredSup.size();
            results.values=filteredSup;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapterSupplier.supplier= (ArrayList<Model_Supplier>) results.values;

        //REFRESH
        adapterSupplier.notifyDataSetChanged();

    }
}
