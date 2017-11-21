package com.example.max.sudokusolver.sudoku_game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.max.sudokusolver.Algorithm;

class Game extends BaseAdapter {

    private Integer[] baseMass; //Массив хранения актуальных игровых цифр
    private Integer[] hiddenElements; //Массив хранения меток сокрытия элементов на данный момент. 1 - элемент скрыт, 0 - элемент открыт
    private Integer[] blockedElements; //Массив хранения меток блокирования элементов. 1 - элемент задания, недоступен для редактирования, 0 - элемент доступный для изменения
    private Context mContext;
    private Algorithm mAlgorithm;
    private final int mRows = 9, mCols = 9;


    public Game(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mRows * mCols;
    }

    @Override
    public Object getItem(int position) {
        return baseMass[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    public void setItem(int positionSelected, int i) {
        baseMass[positionSelected] = i;
        notifyDataSetChanged();
    }

    public void initArray() {
        baseMass = new Integer[81];
        for (int i = 0; i < baseMass.length; i++) {
            baseMass[i] = 0;
        }
        mAlgorithm = new Algorithm();
        mAlgorithm.solve(baseMass);
        notifyDataSetChanged();
    }
}
