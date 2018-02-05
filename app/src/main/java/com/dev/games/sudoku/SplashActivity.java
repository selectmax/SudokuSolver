package com.dev.games.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dev.games.sudoku.sudoku_game.SudokuArray;

public class SplashActivity extends AppCompatActivity {

    private SudokuArray mSudokuArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSudokuArray = SudokuArray.getInstance(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
