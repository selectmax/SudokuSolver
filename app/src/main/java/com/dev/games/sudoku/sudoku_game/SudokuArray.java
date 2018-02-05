package com.dev.games.sudoku.sudoku_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * singleton который хранит массив на протяжении всей работы приложения в единственном экземпляре
 * TODO: подумать как можно улучшить методы
 */

public class SudokuArray {

    private static SudokuArray sSudokuArray;

    private List<Element> mElements;
    private Context mContext;
    private DBHelper dbHelper;

    /**
     * метод для получения объекта класса, через new нельзя объект получить
     * только через SudokuArray array = SudokuArray.getInstance();
     * @return экземпляр класса SudokuArray
     */
    public static SudokuArray getInstance(Context context){
        if (sSudokuArray == null){
            sSudokuArray = new SudokuArray(context);
        }
        return sSudokuArray;
    }

    /**
     * конструкторе при первой инициализации собирается массив
     */
    private SudokuArray(Context context){
        mContext = context.getApplicationContext();
        mElements = new ArrayList<>(81);
        dbHelper = new DBHelper(this.mContext);
        Element element = new Element(0, 0, false, false);
        for (int i = 0; i < 81; i++){
            mElements.add(i, element);
        }
    }

    public List<Element> getElements() {
        return mElements;
    }

    public void setElements(List<Element> elements) {
        mElements = elements;
    }

    public Element getByIndex(int index){
        return mElements.get(index);
    }

    /**
     *
     * @return базовый массив, достает из объекта Element из поля baseElement
     */
    public Integer[] getBaseElementMass(){
        Integer[] baseMass = new Integer[81];
        for (int i = 0; i < mElements.size(); i++){
            baseMass[i] = mElements.get(i).getBaseElement();
        }
        return baseMass;
    }

    /**
     *
     * @param mass - принимает массив который запишется в базовый, пока нужен для алгоритма
     *            TODO: надо подумать как улучшить
     */
    public void setBaseElementMass(Integer[] mass){
        for (int i = 0; i < mass.length; i++) {
            mElements.get(i).setBaseElement(mass[i]);
        }
    }


    /**
     * добавляет эелемент в базовый массив по индексу
     * @param index - индекс для вставки эелемента
     * @param value - значение которое вставляется в базовый массив
     * TODO: подумать как сделать проще и улучшить
     */
    public void setByIndexBaseElement(int index, int value){
       // mElements.get(index).setBaseElement(value);
        Element element = new Element(0, 0, false, false);
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setBaseElement(value);
        element.setIsCheckedElement(mElements.get(index).getIsCheckedElement());
        mElements.set(index, element);
    }

    /**
     * устанавливает заблокирован элемент или нет
     * @param index индекс для вставки эелемента
     * @param value значение которое вставляется в массив блокировок
     * TODO: подумать как сделать проще и улучшить
     */
    public void setByIndexBlockElement(int index, boolean value){
        //mElements.get(index).setBlockedElement(value);
        Element element = new Element(0, 0, false, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(value);
        element.setIsCheckedElement(mElements.get(index).getIsCheckedElement());
        mElements.set(index, element);
    }

    /**
     * добавляет эелемент в пользовательский массив по индексу
     * @param index - индекс для вставки эелемента
     * @param value - значение которое вставляется в пользовательский массив
     * TODO: подумать как сделать проще и улучшить
     */
    public void setByIndexUserElement(int index, int value){
        //mElements.get(index).setUserElement(value);
        Element element = new Element(0, 0, false, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setUserElement(value);
        element.setIsCheckedElement(mElements.get(index).getIsCheckedElement());
        mElements.set(index, element);
    }

    public void setIsCheckedElement(int index, boolean isChecked){
        Element element = new Element(0, 0, false, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setUserElement(mElements.get(index).getUserElement());
        element.setIsCheckedElement(isChecked);
        mElements.set(index, element);
    }

    public int getByIndexBaseElement(int index){
        return mElements.get(index).getBaseElement();
    }

    /**
     * Убирает метку выбранного элемента
     */
    public void clearCheckedElement(){
        for (Element element: mElements) {
            if (element.getIsCheckedElement()){
                element.setIsCheckedElement(false);
            }
        }
    }

    public boolean getByIndexBlockElement(int index){
        return mElements.get(index).getBlockedElement();
    }

    public int getByIndexUserElement(int index){
        return mElements.get(index).getUserElement();
    }

    public void saveDB(){
        long startTime = System.currentTimeMillis();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_DATA, null, null);
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < 81; i++) {
                contentValues.put(DBHelper.KEY_BM, this.getByIndexBaseElement(i));
                contentValues.put(DBHelper.KEY_USERBM, this.getByIndexUserElement(i));
                if (this.getByIndexBlockElement(i)) {
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
                this.setByIndexBaseElement(i, bmIndex);
                this.setByIndexUserElement(i, cursor.getInt(ubmIndex));
                if (cursor.getInt(blockedIndex) == 1) this.setByIndexBlockElement(i, true);
                cursor.moveToNext();
            }
        } else Log.e("DBError", "cursor.moveToFirst() == false");
        cursor.close();
        dbHelper.close();
    }
}
