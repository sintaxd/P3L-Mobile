package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_Motor;
import kel1.com.simato_mobile.Adapter.Adapter_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;

public class CustomFilter_MotorKonsumen extends Filter{
    Adapter_MotorKonsumen adapterMotorKonsumen;
    ArrayList<Model_MotorKonsumen> filterList;

    public CustomFilter_MotorKonsumen(ArrayList<Model_MotorKonsumen> filterList, Adapter_MotorKonsumen adapterMotorKonsumen)
    {
        this.adapterMotorKonsumen = adapterMotorKonsumen;
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
            ArrayList<Model_MotorKonsumen> filteredMtrKns=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getPlat_motorKonsumen().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredMtrKns.add(filterList.get(i));
                }
            }

            results.count=filteredMtrKns.size();
            results.values=filteredMtrKns;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterMotorKonsumen.motorkonsumen= (ArrayList<Model_MotorKonsumen>) results.values;

        //REFRESH
        adapterMotorKonsumen.notifyDataSetChanged();

    }
}