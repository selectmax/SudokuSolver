package com.example.max.sudokusolver.sudoku_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends BaseAdapter {

    private SudokuArray mSudokuArray;
    private Context mContext;
    private final int mRows = 9, mCols = 9;
    private Map<Integer, Integer> mNubersMap;
    private LayoutInflater mLayoutInflater;
    private Algorithm mAlgorithm;
    private List<ElementForCache> mCacheList;
    private byte lvl = 1;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Game(Context mContext) {
        this.mContext = mContext;
        mNubersMap = new HashMap<>();
        mCacheList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(mContext);
        mSudokuArray = SudokuArray.getInstance(mContext);
        mAlgorithm = new Algorithm(mContext);
        initNumbersMap();
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

    public void isCheckedElement(int index){
        mSudokuArray.getElements().get(index).setIsCheckedElement(true);
    }

    public void returnElement(){
        mSudokuArray.getElements().get(mCacheList.get(mCacheList.size() - 1).getIndex())
                .setUserElement(mCacheList.get(mCacheList.size() - 1).getValue());
        mCacheList.remove(mCacheList.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRows * mCols;
    }

    @Override
    public Object getItem(int position) {
        return mSudokuArray.getByIndexBaseElement(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView imageView;

        if (view == null){
            view = mLayoutInflater.inflate(R.layout.grid_item_view, null);
            imageView = (ImageView) view.findViewById(R.id.imageView_number);
            view.setTag(imageView);
        } else {
            imageView = (ImageView) view.getTag();
        }

        Integer a = mNubersMap.get(mSudokuArray.getByIndexUserElement(position));
        if (a == null){
            initNumbersMap();
            a = mNubersMap.get(mSudokuArray.getByIndexUserElement(position));
        }
        imageView.setImageResource(a);

        if (mSudokuArray.getElements().get(position).getIsCheckedElement() &&
                (mSudokuArray.getByIndexUserElement(position) == 0)){
            imageView.setImageResource(R.drawable.podsvet);
        }


        return view;
    }

    /**
     * При клике на клавиатуру из цифр вызывается setItem.
     * @param positionSelected - выбранная позиция в GridView - от 0 до 80
     * @param i - выбранная на клавиатуре цифра для установки
     *  Если число не заблокировано, то userBaseMass, отображаемый юзеру, изменяется. Причем если он верный - то все ок, а если
     *          не совпадает с загаданным элементом baseMass - он устанавливается, но должен быть подсвечен. Причем, скорее всего,
     *          вместе с элементами, которые имеют такое же значение. Тут же проверяется является ли судоку решенным - если да,
     *          то выдается поздравление!
     */
    public void setItem(int positionSelected, int i) {
        if (!mSudokuArray.getByIndexBlockElement(positionSelected)) {
            mSudokuArray.setByIndexUserElement(positionSelected, i);
            mCacheList.add(new ElementForCache(positionSelected, i));
            Log.i("MyTag", "Число изменено");
            if (mSudokuArray.getByIndexUserElement(positionSelected) != mSudokuArray.getByIndexBaseElement(positionSelected)) {
                Log.i("MyTag", "Элемент не валидный");
            }
            if (sudokuIsSolved()) {
               openCongratsDialog();
            }
        } else {
            Toast toast = Toast.makeText(mContext, R.string.error_blocked_element, Toast.LENGTH_SHORT);
            toast.show();
        }
        notifyDataSetChanged();
    }

    private void openCongratsDialog() {
        AlertDialog.Builder congratsDialog = new AlertDialog.Builder(mContext);
        congratsDialog.setTitle(R.string.title_congrats);
        congratsDialog.setPositiveButton(R.string.newgame, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlgorithm.initArray();
                mAlgorithm.initUserBaseMass(lvl);
            }
        });

        congratsDialog.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GameActivity) mContext).finish();
            }
        });
        congratsDialog.show();}

    private boolean sudokuIsSolved() {
        for (int i = 0; i < 81; i++) {
            if (! (mSudokuArray.getByIndexUserElement(i) == (mSudokuArray.getByIndexBaseElement(i))))
                return false;
        }
        return true;
    }
}
