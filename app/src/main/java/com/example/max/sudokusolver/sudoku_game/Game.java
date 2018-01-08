package com.example.max.sudokusolver.sudoku_game;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game extends BaseAdapter {

    private SudokuArray mSudokuArray;
    private Context mContext;
    private Algorithm mGameAlgorithm;
    private final int mRows = 9, mCols = 9;
    public int HowManyTimesRunned = 0;
    private Map<Integer, Integer> mNubersMap;
    private LayoutInflater mLayoutInflater;
    DBHelper dbHelper;
    private byte lvl = 1;


    public Game(Context mContext) {
        this.mContext = mContext;
        mNubersMap = new HashMap<>();
        mLayoutInflater = LayoutInflater.from(mContext);
        //initArray();
        initNumbersMap();
        dbHelper = new DBHelper(this.mContext);
    }

    public Game(){
        mSudokuArray = SudokuArray.getInstance();
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
                initArray();
                initUserBaseMass(lvl);
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

    public void initArray() {
        final int COUNTER_FIRST_RANDOM_FILL = 40; //Показатель степени рандомности исходного поля. 0-80
        int filledCounter = 0;
        HowManyTimesRunned++;
        mGameAlgorithm = new Algorithm();
        for (int i = 0; i < 81; i++){
            mSudokuArray.setByIndexBaseElement(i, 0);
        }

        while (filledCounter <= COUNTER_FIRST_RANDOM_FILL) {
            int randomField;
            int randomValue;
            Random random = new Random();
            randomField = random.nextInt(81);
            randomValue = (random.nextInt(9) + 1);
            if (mSudokuArray.getByIndexBaseElement(randomField) == 0) mSudokuArray.setByIndexBaseElement(randomField, randomValue);
            boolean a = mGameAlgorithm.IsElementValid(mSudokuArray.getBaseElementMass(), randomField);
            if (!a){
                mSudokuArray.setByIndexBaseElement(randomField, 0);
            } else filledCounter++;
        }
        if (!mGameAlgorithm.solve(mSudokuArray.getBaseElementMass())) {
            initArray();
        }
        notifyDataSetChanged();
    }

    public void initUserBaseMass(Byte levelOfDifficult) {
        lvl = levelOfDifficult;
        byte HowManyElementsNeedToOpen = 51;
        switch (levelOfDifficult) {
            case 0:
                HowManyElementsNeedToOpen = 60; //51
                break;
            case 1:
                HowManyElementsNeedToOpen = 36; //36
                break;
            case 2:
                HowManyElementsNeedToOpen = 31; //31
                break;
        }
        for (int i = 0; i < 81; i++) {
            mSudokuArray.setByIndexUserElement(i, 0);
            mSudokuArray.setByIndexBlockElement(i, false);
        }
        while (HowManyElementsNeedToOpen != 0) {
            int randomField;
            Random random = new Random();
            randomField = random.nextInt(81);
            if (mSudokuArray.getByIndexUserElement(randomField) == 0) {
                mSudokuArray.setByIndexUserElement(randomField, mSudokuArray.getByIndexBaseElement(randomField));
                mSudokuArray.setByIndexBlockElement(randomField, true);
                HowManyElementsNeedToOpen--;
            }
        }
    }

    public void saveDB(){
        long startTime = System.currentTimeMillis();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_DATA, null, null);
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < 81; i++) {
                contentValues.put(DBHelper.KEY_BM, mSudokuArray.getByIndexBaseElement(i));
                contentValues.put(DBHelper.KEY_USERBM, mSudokuArray.getByIndexUserElement(i));
                if (mSudokuArray.getByIndexBlockElement(i)) {
                    contentValues.put(DBHelper.KEY_BLOCKED, 1);
                } else contentValues.put(DBHelper.KEY_BLOCKED, 0);
                database.insert(DBHelper.TABLE_DATA, null, contentValues);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        dbHelper.close();
        long diff = System. currentTimeMillis() - startTime;
        Log.i("Time", "Time of save to DB = " + diff);
    }

    public void loadDB(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_DATA, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int bmIndex = cursor.getColumnIndex(DBHelper.KEY_BM);
            int ubmIndex = cursor.getColumnIndex(DBHelper.KEY_USERBM);
            int blockedIndex = cursor.getColumnIndex(DBHelper.KEY_BLOCKED);
            for (int i = 0; i < 81; i++) {
                mSudokuArray.setByIndexBaseElement(i, bmIndex);
                mSudokuArray.setByIndexUserElement(i, cursor.getInt(ubmIndex));
                if (cursor.getInt(blockedIndex) == 1) mSudokuArray.setByIndexBlockElement(i, true);
                cursor.moveToNext();
            }
        } else Log.e("DBError", "cursor.moveToFirst() == false");
        cursor.close();
        dbHelper.close();
    }
}
