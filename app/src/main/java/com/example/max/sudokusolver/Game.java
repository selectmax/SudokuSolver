package com.example.max.sudokusolver;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by max on 19.10.2017.
 */

class Game extends BaseAdapter {

    private Context mContext;
    private final Integer mRows=9, mCols=9;
    private int numberArray[][] = new int[mRows][mCols];
    private Resources mRes;
    private ArrayList<String> arrPict;

    public Game(Context mContext) {
        this.mContext = mContext;
        arrPict = new ArrayList<>(mCols*mRows);
    }


    @Override
    public int getCount() {
        return mRows*mCols;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;
        if (view == null){
            imageView = new ImageView(mContext);
        }
        else {
            imageView = (ImageView) view;
        }

        Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
        imageView.setImageResource(drawableId);
        return imageView;
    }

   public int getRow(int position){
       return position / 9;
   }
   public int getCell(int position){
        return position % 9;
   }

}
