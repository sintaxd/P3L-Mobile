package kel1.com.simato_mobile.API;

import android.widget.Filter;

import java.util.ArrayList;

import kel1.com.simato_mobile.Adapter.Adapter_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;

public class CustomFilter_PengadaanSparepart extends Filter {

    Adapter_PengadaanSparepart adapterPengadaanSparepart;
    ArrayList<Model_PengadaanSparepart> filterList;

    public CustomFilter_PengadaanSparepart(ArrayList<Model_PengadaanSparepart> filterList, Adapter_PengadaanSparepart adapterPengadaanSparepart)
    {
        this.adapterPengadaanSparepart = adapterPengadaanSparepart;
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
            ArrayList<Model_PengadaanSparepart> filteredPengadaanSpp =new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_supplier().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPengadaanSpp.add(filterList.get(i));
                }
            }

            results.count=filteredPengadaanSpp.size();
            results.values=filteredPengadaanSpp;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

        adapterPengadaanSparepart.pengadaansparepart= (ArrayList<Model_PengadaanSparepart>) results.values;

        //REFRESH
        adapterPengadaanSparepart.notifyDataSetChanged();

    }
}