package com.example.max.sudokusolver;

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

class Game extends BaseAdapter {

    private Context mContext;
    private final int mRows=9, mCols=9;
    private int numberArray[][] = new int[mRows][mCols];
    private Resources mRes;
    private ArrayList<Integer> baseMass;
    private ArrayList<String> arrPict;

    public Game(Context mContext, Integer[] massSolved) {
        this.mContext = mContext;
        arrPict = new ArrayList<>(mCols*mRows);
        baseMass = new ArrayList<Integer>(Arrays.asList(massSolved));
    }


    @Override
    public int getCount() {
        return mRows*mCols;
    }

    @Override
    public Object getItem(int i) {
        return baseMass.get(i);
    }

    public void setItem(int index, Integer value){
        baseMass.set(index, value);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //ImageView imageView;
        TextView textView;
        if (view == null){
            textView = new TextView(mContext);
           // textView.setLayoutParams(new GridView.LayoutParams(200, 200));
            textView.setPadding(8,15, 8, 15);
            textView.setTextSize(25);
        }
        else {
            textView = (TextView) view;
        }

        textView.setText(baseMass.get(position).toString());
       // Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
       // imageView.setImageResource(drawableId);
       // Integer drawableId = mRes.getIdentifier(String.valueOf(baseMass.get(position)), "drawable", mContext.getPackageName());
       // imageView.setImageResource(drawableId);
        return textView;
    }

   public int getRow(int position){
       return position / 9;
   }
   public int getCell(int position){
        return position % 9;
   }

}
