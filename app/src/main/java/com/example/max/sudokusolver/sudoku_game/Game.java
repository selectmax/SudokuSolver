package com.example.max.sudokusolver.sudoku_game;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.max.sudokusolver.Algorithm;

import java.util.Random;

class Game extends BaseAdapter {

    private Integer[] baseMass = new Integer[81]; //Массив хранения актуальных игровых цифр
    public Integer[] userBaseMass = new Integer[81]; //Массив хранения пользовательской догадки
    private Boolean[] blockedElements = new Boolean[81]; //Массив хранения меток блокирования элементов. 1 - элемент задания, недоступен для редактирования, 0 - элемент доступный для изменения
    private Context mContext;
    private Algorithm mGameAlgorithm;
    private final int mRows = 9, mCols = 9;
    private String number = " ";
    public int HowManyTimesRunned = 0;


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
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        if (view == null) {
            textView = new TextView(mContext);
            textView.setPadding(12, 6, 6, 12);
            textView.setTextSize(22);
            textView.setTextScaleX((float) 1.4);
        } else {
            textView = (TextView) view;
        }

        if (baseMass[position] == 0) {
            number = " ";
        } else {
            number = String.valueOf(baseMass[position]);
        }

        textView.setText(number);

        return textView;
    }

    public void setItem(int positionSelected, int i) {
        if (!blockedElements[positionSelected]) {// изменить число
        } else {
            Log.i("MyTag", "Попытка изменить число, но найден blockedElement == true");
        }
        baseMass[positionSelected] = i;
        notifyDataSetChanged();
    }

    public void initArray() {
        final int COUNTER_FIRST_RANDOM_FILL = 30; //Показатель степени рандомности исходного поля. 0-80
        int FilledCounter = 0;
        HowManyTimesRunned++;
        mGameAlgorithm = new Algorithm();
        for (int i = 0; i < baseMass.length; i++) {
            baseMass[i] = 0;
        }

        while (FilledCounter <= COUNTER_FIRST_RANDOM_FILL) {
            int randomField;
            int randomValue;
            Random random = new Random();
            randomField = random.nextInt(81);
            randomValue = (random.nextInt(9) + 1);
            if (baseMass[randomField] == 0) baseMass[randomField] = randomValue;
            if (!mGameAlgorithm.IsElementValid(baseMass, randomField)) {
                baseMass[randomField] = 0;
            } else FilledCounter++;
        }
        if (!mGameAlgorithm.solve(baseMass)) {initArray();
         }

        notifyDataSetChanged();
    }
    public void initUserBaseMass(byte levelOfDifficult) {
        byte HowManyElementsNeedToOpen = 60;
        switch(levelOfDifficult) {
            case 0:
                HowManyElementsNeedToOpen = 70;
                break;
            case 1:
                HowManyElementsNeedToOpen = 60;
                break;
            case 2:
                HowManyElementsNeedToOpen = 50;
                break;
        }
        for (int i = 0; i < 81; i++) {
            userBaseMass[i] = 0;
        }
        while (HowManyElementsNeedToOpen != 0) {
            int randomField;
            Random random = new Random();
            randomField = random.nextInt(81);
            if (userBaseMass[randomField] == 0) {
                userBaseMass[randomField] = baseMass[randomField];
                blockedElements[randomField] = true;
                HowManyElementsNeedToOpen--;
            }
        }
    }
}
