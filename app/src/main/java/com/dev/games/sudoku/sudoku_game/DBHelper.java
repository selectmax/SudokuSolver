package com.dev.games.sudoku.sudoku_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by max on 12.12.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SudokuGame";
    public static final String TABLE_DATA = "data";

    public static final String KEY_ID = "_id";
    public static final String KEY_BM = "basemass";
    public static final String KEY_USERBM = "userbasemass";
    public static final String KEY_BLOCKED = "blockedelement";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_DATA + "(" + KEY_ID + " integer primary key," + KEY_BM + " integer," + KEY_USERBM + " integer," + KEY_BLOCKED + " integer)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_DATA);
        onCreate(db);
    }
}
