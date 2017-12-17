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

    private Integer[] baseMass = new Integer[81]; //Массив хранения актуальных игровых цифр
    public Integer[] userBaseMass = new Integer[81]; //Массив хранения пользовательской догадки
    private Boolean[] blockedElements = new Boolean[81]; //Массив хранения меток блокирования элементов. 1 - элемент задания, недоступен для редактирования, 0 - элемент доступный для изменения
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
        mSudokuArray = SudokuArray.getInstance();
        mNubersMap = new HashMap<>();
        mLayoutInflater = LayoutInflater.from(mContext);
        initArray();
        initNumbersMap();
        dbHelper = new DBHelper(this.mContext);
    }

    public Game(){
        initArray();
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
       // return baseMass[position];
        return mSudokuArray.getByIndexBaseElement(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
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

       // Integer a = mNubersMap.get(userBaseMass[position]);
        Integer a = mNubersMap.get(mSudokuArray.getByIndexUserElement(position));
        if (a == null){
            initNumbersMap();
          //  a = mNubersMap.get(userBaseMass[position]);
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
        //if (!blockedElements[positionSelected]) {
        if (!mSudokuArray.getByIndexBlockElement(positionSelected)) {
            //userBaseMass[positionSelected] = i;
            mSudokuArray.setByIndexUserElement(positionSelected, i);
            Log.i("MyTag", "Число изменено");
           // if (userBaseMass[positionSelected] != baseMass[positionSelected]) {
            if (mSudokuArray.getByIndexUserElement(positionSelected) != mSudokuArray.getByIndexBaseElement(positionSelected)) {
                //показывать или не показывать подсказку, что элемент неверный? Цветом, выделением?
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
           // if (!userBaseMass[i].equals(baseMass[i]))
            if (! (mSudokuArray.getByIndexUserElement(i) == (mSudokuArray.getByIndexBaseElement(i))))
                return false;
        }
        return true;
    }

    public void initArray() {
        final int COUNTER_FIRST_RANDOM_FILL = 1; //Показатель степени рандомности исходного поля. 0-80
        int FilledCounter = 0;
        HowManyTimesRunned++;
        mGameAlgorithm = new Algorithm();
       // for (int i = 0; i < baseMass.length; i++) {
       //     baseMass[i] = 0;
       // }
        for (int i = 0; i < 81; i++){
            mSudokuArray.setByIndexBaseElement(i, 0);
        }

        while (FilledCounter <= COUNTER_FIRST_RANDOM_FILL) {
            int randomField;
            int randomValue;
            Random random = new Random();
            randomField = random.nextInt(81);
            randomValue = (random.nextInt(9) + 1);
           // if (baseMass[randomField] == 0) baseMass[randomField] = randomValue;
            if (mSudokuArray.getByIndexBaseElement(randomField) == 0) mSudokuArray.setByIndexBaseElement(randomField, randomValue);
         //   if (!mGameAlgorithm.IsElementValid(baseMass, randomField)) {
         //       baseMass[randomField] = 0;
         //   } else FilledCounter++;
            if (!mGameAlgorithm.IsElementValid(mSudokuArray.getBaseElementMass(), randomField)){
                mSudokuArray.setByIndexBaseElement(randomField, 0);
            } else FilledCounter++;
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
                HowManyElementsNeedToOpen = 51; //51
                break;
            case 1:
                HowManyElementsNeedToOpen = 36; //36
                break;
            case 2:
                HowManyElementsNeedToOpen = 31; //31
                break;
        }
        for (int i = 0; i < 81; i++) {
            //userBaseMass[i] = 0;
            mSudokuArray.setByIndexUserElement(i, 0);
            //blockedElements[i] = false;
            mSudokuArray.setByIndexBlockElement(i, false);
        }
        while (HowManyElementsNeedToOpen != 0) {
            int randomField;
            Random random = new Random();
            randomField = random.nextInt(81);
           // if (userBaseMass[randomField] == 0) {
            if (mSudokuArray.getByIndexUserElement(randomField) == 0) {
               // userBaseMass[randomField] = baseMass[randomField];
                mSudokuArray.setByIndexUserElement(randomField, mSudokuArray.getByIndexBaseElement(randomField));
                //blockedElements[randomField] = true;
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
               // contentValues.put(DBHelper.KEY_BM, baseMass[i]);
                contentValues.put(DBHelper.KEY_BM, mSudokuArray.getByIndexBaseElement(i));
                //contentValues.put(DBHelper.KEY_USERBM, userBaseMass[i]);
                contentValues.put(DBHelper.KEY_USERBM, mSudokuArray.getByIndexUserElement(i));
               // if (blockedElements[i]) {
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
                //baseMass[i] = cursor.getInt(bmIndex);
                mSudokuArray.setByIndexBaseElement(i, bmIndex);
                //userBaseMass[i] = cursor.getInt(ubmIndex);
                mSudokuArray.setByIndexUserElement(i, cursor.getInt(ubmIndex));
               // if (cursor.getInt(blockedIndex) == 1) blockedElements[i] = true;
                if (cursor.getInt(blockedIndex) == 1) mSudokuArray.setByIndexBlockElement(i, true);
              //  else blockedElements[i] = false;
                else mSudokuArray.setByIndexBlockElement(i, false);
                cursor.moveToNext();
            }
        } else Log.i("DBError", "cursor.moveToFirst() == false");
        cursor.close();
        dbHelper.close();
    }
}
