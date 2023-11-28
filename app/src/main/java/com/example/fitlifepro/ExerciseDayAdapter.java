package com.example.fitlifepro;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ExerciseDayAdapter extends BaseAdapter {

    List<ExerciseDay> adapterExerciseDay;
    int SelectedInd = -1;

    public ExerciseDayAdapter(List<ExerciseDay> adapterExerciseDay) {
        this.adapterExerciseDay = adapterExerciseDay;
    }

    public ExerciseDayAdapter(List<ExerciseDay> adapterExerciseDay, int selectedInd) {
        this.adapterExerciseDay = adapterExerciseDay;
        SelectedInd = selectedInd;
    }

    public List<ExerciseDay> getAdapterExerciseDay() {
        return adapterExerciseDay;
    }

    public void setAdapterExerciseDay(List<ExerciseDay> adapterExerciseDay) {
        this.adapterExerciseDay = adapterExerciseDay;
        notifyDataSetChanged();
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return adapterExerciseDay.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterExerciseDay.get(i);
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
                    .inflate(R.layout.layout_daily_exercise_activities,viewGroup,false);
        }

        //populate dynamic variables
        TextView txtExerciseName = view.findViewById(R.id.txtViewExerciseName);
        TextView txtFitnessLvl = view.findViewById(R.id.txtViewExerciseLvl);
        //TextView txtCountdown = view.findViewById(R.id.txtViewCountdown);
        ImageView imgExercise = view.findViewById(R.id.imgViewExercise);

        txtExerciseName.setText(adapterExerciseDay.get(i).getExerciseName());
        txtFitnessLvl.setText(adapterExerciseDay.get(i).getFitnessLvl());
        //txtCountdown.setText(adapterExerciseDay.get(i).getTimer());
        imgExercise.setImageResource(adapterExerciseDay.get(i).getExercisePic());

        return view;
    }
}
