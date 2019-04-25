package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Motor;
import kel1.com.simato_mobile.Model.Model_Motor;

public class CustomFilter_Motor extends Filter {
    Adapter_Motor adapterMotor;
    ArrayList<Model_Motor> filterList;

    public CustomFilter_Motor(ArrayList<Model_Motor> filterList, Adapter_Motor adapterMotor)
    {
        this.adapterMotor = adapterMotor;
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
            ArrayList<Model_Motor> filteredMtr=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getMerk_motor().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredMtr.add(filterList.get(i));
                }
            }

            results.count=filteredMtr.size();
            results.values=filteredMtr;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterMotor.motor= (ArrayList<Model_Motor>) results.values;

        //REFRESH
        adapterMotor.notifyDataSetChanged();

    }
}

