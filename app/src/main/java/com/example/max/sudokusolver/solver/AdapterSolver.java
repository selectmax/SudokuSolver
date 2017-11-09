package com.example.max.sudokusolver.solver;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by max on 19.10.2017.
 */

class AdapterSolver extends BaseAdapter {

    private Context mContext;
    private final int mRows=9, mCols=9;
    private int numberArray[][] = new int[mRows][mCols];
    private Resources mRes;
    private ArrayList<Integer> baseMass;
    private ArrayList<String> arrPict;
    private String number = " ";

    public AdapterSolver(Context mContext, Integer[] massSolved) {
        this.mContext = mContext;
        arrPict = new ArrayList<>(mCols*mRows);
        baseMass = new ArrayList<Integer>(Arrays.asList(massSolved));
    }


    @Override
    public int getCount() {
        return mRows*mCols;
    }

    @Override
    public Integer getItem(int i) {
        return baseMass.get(i);
    }

    public void setItem(int index, Integer value){
        baseMass.set(index, value);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public ArrayList<Integer> getBaseMass() {
        return baseMass;
    }

    public void setBaseMass(Integer[] baseMass) {
        for (int i = 0; i < baseMass.length; i++){
            this.baseMass.set(i, baseMass[i]);
        }
        notifyDataSetChanged();
    }

    public void cleanMassInt() {
        for (int i = 0; i < 81; i++){
            this.baseMass.set(i, 0);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        if (view == null){
            textView = new TextView(mContext);
            textView.setPadding(12,6, 6, 12); // настройка отступов между ячейками
            textView.setTextSize(25); // величина шрифта
            textView.setTextScaleX((float) 1.4); // верил что сделает текст по центру, но нет( надо эту проблему решить
        }
        else {
            textView = (TextView) view;
        }

        if (baseMass.get(position) == 0){
            number = " ";
        } else {
            number = String.valueOf(baseMass.get(position));
        }

        textView.setText(number);

        return textView;
    }

   public int getRow(int position){
       return position / 9;
   }
   public int getCell(int position){
        return position % 9;
   }

}
