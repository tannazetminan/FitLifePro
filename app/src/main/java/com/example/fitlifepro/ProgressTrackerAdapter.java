package com.example.fitlifepro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProgressTrackerAdapter extends BaseAdapter {
    List<ProgressTrackerDays> adapterProgressTrackerDays;
    int SelectedInd = -1;

    public ProgressTrackerAdapter(List<ProgressTrackerDays> adapterProgressTrackerDays) {
        this.adapterProgressTrackerDays = adapterProgressTrackerDays;
    }

    public ProgressTrackerAdapter(List<ProgressTrackerDays> adapterProgressTrackerDays, int selectedInd) {
        this.adapterProgressTrackerDays = adapterProgressTrackerDays;
        SelectedInd = selectedInd;
    }

    public List<ProgressTrackerDays> getAdapterProgressTrackerDays() {
        return adapterProgressTrackerDays;
    }

    public void setAdapterProgressTrackerDays(List<ProgressTrackerDays> adapterProgressTrackerDays) {
        this.adapterProgressTrackerDays = adapterProgressTrackerDays;
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
    }

    @Override
    public int getCount() {
        return adapterProgressTrackerDays.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterProgressTrackerDays.get(i);
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
                    .inflate(R.layout.layout_progress_tracker,viewGroup,false);
        }

        //populate days
        TextView txtViewDay = view.findViewById(R.id.txtViewDay);
        txtViewDay.setText(adapterProgressTrackerDays.get(i).getDayCounter());

        return view;
    }
}
