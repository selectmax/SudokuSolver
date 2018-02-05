package com.dev.games.sudoku.solver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.max.sudoku.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSolver extends BaseAdapter {

    private Context mContext;
    private final int mRows = 9, mCols = 9;
    private ArrayList<Integer> baseMass;
    private String number = " ";
    private LayoutInflater mLayoutInflater;
    private Map<Integer, Integer> mNubersMap;

    public AdapterSolver(Context mContext, Integer[] massSolved) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        mNubersMap = new HashMap<>();
        initNumbersMap();
        baseMass = new ArrayList<>();
        initArray(massSolved);
    }

    private void initArray(Integer[] mass){
        for (int i = 0; i < 81; i++){
            baseMass.add(i, mass[i]);
        }
    }

    private void initNumbersMap(){
        mNubersMap.put(0, R.drawable.o0);
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

    @Override
    public int getCount() {
        return mRows * mCols;
    }

    @Override
    public Integer getItem(int i) {
        return baseMass.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItem(int index, Integer value) {
        baseMass.set(index, value);
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getBaseMass() {
        return baseMass;
    }

    public void setBaseMass(Integer[] baseMass) {
        for (int i = 0; i < 81; i++) {
            this.baseMass.set(i, baseMass[i]);
        }
        notifyDataSetChanged();
    }

    public void cleanMassInt() {
        for (int i = 0; i < 81; i++) {
            this.baseMass.set(i, 0);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

       /* ImageView imageView;
        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setMaxWidth(105);
            imageView.setMaxHeight(105);
        } else {
            imageView = (ImageView) view;
        } */

        ImageView imageView;
       // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(117, 117);

        if (view == null){
            view = mLayoutInflater.inflate(R.layout.grid_item_view, null);
            imageView = (ImageView) view.findViewById(R.id.imageView_number);
          //  params = new RelativeLayout.LayoutParams(75, 75);
          //  holder.mNumberView.setLayoutParams(params);

            view.setTag(imageView);
        } else {
            imageView = (ImageView) view.getTag();
        }

        imageView.setImageResource(mNubersMap.get(baseMass.get(position)));

        return view;

       /* imageView.setImageResource(mNubersMap.get(baseMass.get(position)));

        return imageView; */
    }

}
