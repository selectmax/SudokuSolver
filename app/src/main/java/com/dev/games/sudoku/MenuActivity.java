package com.dev.games.sudoku;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dev.games.sudoku.sudoku_game.GameActivity;
import com.dev.games.sudoku.solver.SolverActivity;
import com.dev.games.sudoku.sudoku_game.SudokuArray;

public class MenuActivity extends AppCompatActivity {

    private Button mGameBtn, mContinueBtn;
    private Button mSolveBtn;
    private Spinner spinner;
    private byte levelOfDifficult;
    private SudokuArray mSudokuArray;
    private Algorithm mAlgorithm;
    private ProgressDialog mProgressDialog;
    private InitializeArray mInitializeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String[] diff = {getString(R.string.difficulty_level_easy), getString(R.string.difficulty_level_normal), getString(R.string.difficulty_level_hard)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.difficulty_level_spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.difficulty_level));
        spinner.setSelection(1);
        levelOfDifficult = 1;
        mInitializeArray = new InitializeArray();
        mAlgorithm = new Algorithm(this);

        mSudokuArray = SudokuArray.getInstance(this);
       // mProgressDialog = new ProgressDialog(this, R.style.AsyncTheme);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        mProgressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mInitializeArray.cancel(true);
                mInitializeArray = new InitializeArray();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelOfDifficult = (byte) position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGameBtn = (Button) findViewById(R.id.game_btn);
        mGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInitializeArray.execute();
            }
        });

        mContinueBtn = (Button) findViewById(R.id.continue_btn);
        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra("Continue", true);
                startActivity(intent);
            }
        });


        mSolveBtn = (Button) findViewById(R.id.solve_btn);
        mSolveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SolverActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mInitializeArray = new InitializeArray();
    }

    public class InitializeArray extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            mAlgorithm.initArray();
            mAlgorithm.initUserBaseMass(levelOfDifficult);
            return null;
        }

        /** метод перед вызовом второго потока
         * в данный момент, то есть перед вызовом второго потока
         * на экран вылетает окно с загрузкой
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setTitle(R.string.titleDialogAsync);
            mProgressDialog.show();
        }

        /**
         * метод после выполнения второго потока
         *
         * по завершению убирается диалоговое окно и запускается игра
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.cancel();
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(intent);
        }
    }
}
