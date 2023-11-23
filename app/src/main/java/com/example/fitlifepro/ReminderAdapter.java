package com.example.fitlifepro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReminderAdapter extends BaseAdapter
{
    List<Reminder> adapterReminder;
    int SelectedInd = -1;

    // Constructor
    public ReminderAdapter(List<Reminder> adapterReminder, int selectedInd) {
        this.adapterReminder = adapterReminder;
        SelectedInd = selectedInd;
    }

    // getter and setter for adapterReminder
    public List<Reminder> getAdapterReminder() {
        return adapterReminder;
    }

    public void setAdapterReminder(List<Reminder> adapterReminder) {
        this.adapterReminder = adapterReminder;
        notifyDataSetChanged();
    }

    // getter and setter for SelectedInd
    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return adapterReminder.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterReminder.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // We will use layout_reminder.xml layout
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.layout_reminder, viewGroup, false);
        }

        // Finding txtViewRemDate inside layout_reminder.xml
        TextView txtViewRemDate = view.findViewById(R.id.txtViewRemDate);
        // Dynamically setting
        txtViewRemDate.setText(adapterReminder.get(i).getRemDate());

        // Finding txtViewRemMemo inside layout_reminder.xml
        TextView txtViewRemMemo = view.findViewById(R.id.txtViewRemMemo);
        // Dynamically setting
        txtViewRemMemo.setText(adapterReminder.get(i).getRemMemo());

        return view;
    }
}
