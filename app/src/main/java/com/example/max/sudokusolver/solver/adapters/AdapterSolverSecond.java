package com.example.max.sudokusolver.solver.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.max.sudokusolver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by leonidtiskevic on 24.11.2017.
 */

public class AdapterSolverSecond extends BaseAdapter {

    private Context mContext;
    private final int mRows = 3, mCols = 9;
    private ArrayList<Integer> baseMass;
    private String number = " ";
    private LayoutInflater mLayoutInflater;
    private Set<Integer> rightSideNumbers;
    private Set<Integer> cetnrSideNumbers;
    private Map<Integer, Integer> mNubersMap;

    public AdapterSolverSecond(Context mContext, Integer[] massSolved) {
        this.mContext = mContext;
        baseMass = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(mContext);
        rightSideNumbers = new HashSet<>();
        cetnrSideNumbers = new HashSet<>();
        mNubersMap = new HashMap<>();
        initNumbersMap();
        fillRightSet();
        fillCenterSet();
        baseMass = new ArrayList<>();
        initArray(massSolved);
    }

    private void initNumbersMap(){
        mNubersMap.put(1, R.drawable.o1);
        mNubersMap.put(2, R.drawable.o2);
        mNubersMap.put(3, R.drawable.o3);
        mNubersMap.put(4, R.drawable.o4);
        mNubersMap.put(5, R.drawable.o5);
        mNubersMap.put(6, R.drawable.o6);
        mNubersMap.put(7, R.drawable.o7);
        mNubersMap.put(8, R.drawable.o8);
        mNubersMap.put(9, R.drawable.o9);
    }

    private void fillRightSet(){
        rightSideNumbers.add(6);
        rightSideNumbers.add(7);
        rightSideNumbers.add(8);
        rightSideNumbers.add(15);
        rightSideNumbers.add(16);
        rightSideNumbers.add(17);
        rightSideNumbers.add(24);
        rightSideNumbers.add(25);
        rightSideNumbers.add(26);
    }

    private void fillCenterSet(){
        cetnrSideNumbers.add(3);
        cetnrSideNumbers.add(4);
        cetnrSideNumbers.add(5);
        cetnrSideNumbers.add(12);
        cetnrSideNumbers.add(13);
        cetnrSideNumbers.add(14);
        cetnrSideNumbers.add(21);
        cetnrSideNumbers.add(22);
        cetnrSideNumbers.add(23);
    }

    private void initArray(Integer[] mass){
        int j = 0;
        for (int i = 27; i < 54; i++){
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
        for (int i = 27; i < 54; i++) {
            this.baseMass.set(i, baseMass[i]);
        }
        notifyDataSetChanged();
    }

    public void cleanMassInt() {
        for (int i = 0; i < 27; i++) {
            this.baseMass.set(i, 0);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(117, 117);

        if (view == null){
            view = mLayoutInflater.inflate(R.layout.grid_item_view, null);
            holder = new ViewHolder();
            holder.mNumberView = (ImageView) view.findViewById(R.id.imageView_number);
            params = new RelativeLayout.LayoutParams(117, 117);
            holder.mNumberView.setLayoutParams(params);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (rightSideNumbers.contains(position)){
            params.setMargins(9, 1, 0, 0);
            holder.mNumberView.setLayoutParams(params);
        }

        if (cetnrSideNumbers.contains(position)){
            params.setMargins(4, 0, 2, 0);
            holder.mNumberView.setLayoutParams(params);
        }

        if (mNubersMap.containsKey(baseMass.get(position))){
            holder.mNumberView.setImageResource(mNubersMap.get(baseMass.get(position)));
        }

        if (baseMass.get(position) == 0){
            holder.mNumberView.setImageResource(R.drawable.o0);
        }
        return view;

    }


    static class ViewHolder{
        ImageView mNumberView;
    }
}
