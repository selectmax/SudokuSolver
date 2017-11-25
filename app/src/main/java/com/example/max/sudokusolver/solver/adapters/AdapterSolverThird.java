package com.example.max.sudokusolver.solver.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by leonidtiskevic on 24.11.2017.
 */

public class AdapterSolverThird extends BaseAdapter {

    private Context mContext;
    private final int mRows = 9, mCols = 9;
    private ArrayList<Integer> baseMass;
    private String number = " ";

    public AdapterSolverThird(Context mContext, Integer[] massSolved) {
        this.mContext = mContext;
        baseMass = new ArrayList<>();
        initArray(massSolved);
    }

    private void initArray(Integer[] mass){
        int j = 0;
        for (int i = 54; i < 81; i++){
            baseMass.add(j, mass[i]);
            j++;
        }
    }

    @Override
    public int getCount() {
        return mRows * mCols;
    }

    @Override
    public Integer getItem(int i) {
        return baseMass.get(i);
    }

    public void setItem(int index, Integer value) {
        baseMass.set(index, value);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ArrayList<Integer> getBaseMass() {
        return baseMass;
    }

    public void setBaseMass(Integer[] baseMass) {
        for (int i = 54; i < 81; i++) {
            this.baseMass.set(i, baseMass[i]);
        }
        notifyDataSetChanged();
    }

    public void cleanMassInt() {
        for (int i = 54; i < 81; i++) {
            this.baseMass.set(i, 0);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        if (view == null) {
            textView = new TextView(mContext);
            textView.setPadding(12, 6, 6, 12);
            textView.setTextSize(25);
            textView.setTextScaleX((float) 1.4);
        } else {
            textView = (TextView) view;
        }

        if (baseMass.get(position) == 0) {
            number = " ";
        } else {
            number = String.valueOf(baseMass.get(position));
        }

        textView.setText(number);

        return textView;
    }
}
