package com.example.fitlifepro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FitnessLvlAdapter extends BaseAdapter {

    //data
    List<FitnessLvl> adapterFitnessLvl;
    int SelectedInd = -1;

    //contructor
    public FitnessLvlAdapter(List<FitnessLvl> adapterFitnessLvl, int selectedInd) {
        this.adapterFitnessLvl = adapterFitnessLvl;
        SelectedInd = selectedInd;
    }

    public FitnessLvlAdapter(List<FitnessLvl> adapterFitnessLvl) {
        this.adapterFitnessLvl = adapterFitnessLvl;
    }

    public List<FitnessLvl> getAdapterFitnessLvl() {
        return adapterFitnessLvl;
    }

    public void setAdapterFitnessLvl(List<FitnessLvl> adapterFitnessLvl) {
        this.adapterFitnessLvl = adapterFitnessLvl;
        notifyDataSetChanged();
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged(); //data has changed, call getViews again
    }

    @Override
    public int getCount() {
        return adapterFitnessLvl.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterFitnessLvl.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //inflate
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_fitnesslevel,viewGroup,false);
        }

        //populate lvl name
        TextView txtViewLvl = view.findViewById(R.id.txtViewLvl);
        txtViewLvl.setText(adapterFitnessLvl.get(i).getLvlName());


        //populate lvl desc
        TextView txtViewLvlDesc = view.findViewById(R.id.textViewLvlDesc);
        txtViewLvlDesc.setText(adapterFitnessLvl.get(i).getLvlDesc());

        ImageView imgViewCheck = view.findViewById(R.id.imageViewCheck);


       if (i == SelectedInd) {
           imgViewCheck.setImageResource(R.drawable.baseline_check_orange);
        } else {
            imgViewCheck.setImageResource(R.drawable.baseline_check_black);
       }

        return view;
    }
}
